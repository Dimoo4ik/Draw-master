package com.example.DrawLoterya.controllers;

import com.example.DrawLoterya.model.User;
import com.example.DrawLoterya.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    User currentUser;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser/")
    public void addUser(User user) {
        if (user.getEmail().length() > 0) {
//            char randomChar = (char) (30 + (int) Math.round(Math.random() * 10));
//            String passwordCoding = user.getPassword().concat(String.valueOf(randomChar));
//            char[] listCharPassword = password.toCharArray();
//            StringBuilder builderPassword = new StringBuilder();
//            for (char c : listCharPassword) {
//                builderPassword.append(c);
//            }
//            builderPassword.append(randomChar);

//            System.out.println("Adding user '".concat(user.toString()).concat("'.").concat("\nLast char '")
//                    .concat(String.valueOf(listCharPassword[listCharPassword.length - 1])).concat("'."));
//            user.setPassword(listCharPassword.toString());
            currentUser = user;
            userRepository.save(user);
        }
    }

    @PostMapping("/updateInformationUser/")
    public void updateInformationUser(@RequestParam("fioUser") String fioUser, @RequestParam("birthday") String birthday,
                                      @RequestParam("updateEmail") String email, @RequestParam("phone") String phoneUser,
                                      @RequestParam("telegram") String telegram, @RequestParam("vk") String vk) throws Exception {
        User user = userRepository.findByEmail(email);
        currentUser = user;
        user.setFullName(fioUser);
        user.setBirthdayUser(birthday);
        user.setPhoneUser(phoneUser);
        user.setEmail(email);
        user.setTelegram(telegram);
        user.setVk(vk);
        userRepository.save(user);
    }


    @PostMapping("/addImage/")
    public void addImage(@RequestParam("file") MultipartFile image) throws IOException {
        User user = userRepository.findByEmail(currentUser.getEmail());
        user.setPhoto(image.getBytes());
        userRepository.save(user);
    }


    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> updateInformationUser(@PathVariable Integer userId) {
        User user = userRepository.findById(userId).get();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(user.getPhoto());
    }
}