package com.example.DrawLoterya.controllers;

import com.example.DrawLoterya.model.User;
import com.example.DrawLoterya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String mainPageDraws() {
        return "mainPage";
    }

//    @PostMapping("/addUser/")
//    public void addUser(@RequestParam("email") String email, @RequestParam("password_reg") String password) throws Exception {
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        if (user.getEmail().length() > 0) {
////            char randomChar = (char) (30 + (int) Math.round(Math.random() * 10));
////            String passwordCoding = user.getPassword().concat(String.valueOf(randomChar));
////            char[] listCharPassword = password.toCharArray();
////            StringBuilder builderPassword = new StringBuilder();
////            for (char c : listCharPassword) {
////                builderPassword.append(c);
////            }
////            builderPassword.append(randomChar);
//
////            System.out.println("Adding user '".concat(user.toString()).concat("'.").concat("\nLast char '")
////                    .concat(String.valueOf(listCharPassword[listCharPassword.length - 1])).concat("'."));
////            user.setPassword(listCharPassword.toString());
//            userRepository.save(user);
//        }
//    }

    @GetMapping("/pageCurrentUser")
    public String pageCurrentUser(@RequestParam("send_form_form_entrance_user") String email, Model model) {
        User user = userRepository.findByEmail(email);

        model.addAttribute("userId", user.getId());
        model.addAttribute("photo", user.getPhoto());
        model.addAttribute("fioUser", user.getFullName());
        model.addAttribute("birthday", user.getBirthdayUser());
        model.addAttribute("updateEmail", user.getEmail());
        model.addAttribute("phoneUser", user.getPhoneUser());
        model.addAttribute("telegram", user.getTelegram());
        model.addAttribute("vk", user.getVk());
        return "pageUser";
    }
}