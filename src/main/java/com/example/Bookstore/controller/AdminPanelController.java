package com.example.Bookstore.controller;

import com.example.Bookstore.model.Order;
import com.example.Bookstore.model.OrderStatus;
import com.example.Bookstore.repository.OrderRepository;
import com.example.Bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPanelController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private IBookService bookService;

    @GetMapping("admin/adminpanel")
    public String adminPanel(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "adminpanel";
    }
    @GetMapping("/admin/orderpanel")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orderpanel";
    }
    @PostMapping("/orders/{id}")
    public String updateOrder(@PathVariable Long id, @RequestParam OrderStatus status, Model model) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        model.addAttribute("order", order);
        return "redirect:/admin/orderpanel";
    }

}