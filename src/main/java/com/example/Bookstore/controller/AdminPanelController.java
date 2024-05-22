package com.example.Bookstore.controller;

import com.example.Bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {
    @Autowired
    private IBookService bookService;

    @GetMapping("admin/adminpanel")
    public String adminPanel(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "adminpanel";
    }
}