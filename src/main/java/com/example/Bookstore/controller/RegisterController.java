package com.example.Bookstore.controller;

import com.example.Bookstore.model.User;
import com.example.Bookstore.service.UserService;
import com.example.Bookstore.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        String result = userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", result);
        if (result.equals("success")) {
            return "redirect:/login?registered";
        }
        return "register";
    }
}
