package com.example.Bookstore.controller;

import com.example.Bookstore.model.Order;
import com.example.Bookstore.model.User;
import com.example.Bookstore.service.CustomUserService;
import com.example.Bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomUserService userService;
    @PostMapping("/submit")
    public String submitOrder() {
        try{
            Order order = orderService.submitOrder();
            return "redirect:/order/" +
                    order.getId();
        }catch (RuntimeException e){
            return "errorPage";
        }
    }
    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        Order order =
                orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order";
    }
    @GetMapping("/history")
    public String getOrderHistory(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Order> orderHistory = orderService.getOrderHistoryForUser(currentUser);
        model.addAttribute("orderHistory", orderHistory);
        return "orderhistory";
    }


}
