package com.example.DrawLoterya.repository;

import com.example.DrawLoterya.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
