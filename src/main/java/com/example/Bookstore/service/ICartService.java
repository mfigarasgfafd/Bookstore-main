package com.example.Bookstore.service;

import com.example.Bookstore.model.Cart;

public interface ICartService {
    Cart getCurrentUserCart();

    Cart addToCart(int bookId, int quantity);

    Cart removeFromCart(int bookId);

    Cart saveCart(Cart cart);

}
