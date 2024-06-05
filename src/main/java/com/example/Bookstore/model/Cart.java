package com.example.Bookstore.model;

import com.example.Bookstore.service.CartService;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
    @OneToOne(mappedBy = "cart")
    private User user;


    public void addItem(Book book, int quantity) {
        CartItem cartItem = new CartItem(this, book, quantity);
        for (CartItem i : items) {
            if (i.getBook().equals(book)) {
                i.setQuantity(i.getQuantity() + quantity);
                return;
            }
        }
        items.add(cartItem);
    }


    public void removeItem(Book book) {
        for (CartItem i : items) {
            if (i.getBook().equals(book)) {
                int newQuantity = i.getQuantity() - 1;
                if (newQuantity < 1) {
                    items.remove(i);
                } else {
                    i.setQuantity(newQuantity);
                }
                break;
            }
        }
    }
    public List<CartItem> getItems() {
        return items;
    }

}