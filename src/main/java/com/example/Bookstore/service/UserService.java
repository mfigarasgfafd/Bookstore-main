package com.example.Bookstore.service;

import com.example.Bookstore.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    String registerUser(User user);
}
