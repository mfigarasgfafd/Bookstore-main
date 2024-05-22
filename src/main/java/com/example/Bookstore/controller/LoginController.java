package com.example.Bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping({"/login", "/logout"})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "register", required = false) String registered,
                        Model model) {
        if(registered != null) {
            model.addAttribute("registeredMessage", "You have created a new account.");
        }
        if (error != null) {
            model.addAttribute("message", "incorrect username or password");
        }
        if (logout != null) {
            model.addAttribute("message", "logged out");
        }

        return "login";
    }
}