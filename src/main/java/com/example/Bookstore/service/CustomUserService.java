package com.example.Bookstore.service;

import com.example.Bookstore.model.Role;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.RoleRepository;
import com.example.Bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserService implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public String registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("USER").orElseGet(null);

        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
        }

        userRepository.save(user);

        return "success";
    }
}