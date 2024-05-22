package com.example.Bookstore.controller;


import com.example.Bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private IBookService bookService;
    @GetMapping({"/home"})
    public String home() {
        return "home";
    }

    @RequestMapping(path = { "/main_visible"}, method = RequestMethod.GET)
    public String main_visible(Model model){
        model.addAttribute("books", this.bookService.getAll());
        return "main_visible";
    }

//    @GetMapping("/admin/adminpanel")
//    public String adminpanel() {
//        return "adminpanel";
//    }

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books", this.bookService.getAll());
        return "home";
    }

}
