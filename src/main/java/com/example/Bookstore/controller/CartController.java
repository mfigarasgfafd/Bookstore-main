package com.example.Bookstore.controller;

import com.example.Bookstore.model.Cart;
import com.example.Bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/add/{bookId}/{quantity}")
    public String addToCart(@PathVariable int bookId, @PathVariable int quantity) {
        cartService.addToCart(bookId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String getCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("totalCost", cart.getTotalCost());
        return "cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable int bookId) {
        cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }
}
