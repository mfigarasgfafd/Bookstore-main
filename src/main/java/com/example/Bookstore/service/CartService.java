package com.example.Bookstore.service;

import com.example.Bookstore.DAO.IBookDAO;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Cart;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.CartRepository;
import com.example.Bookstore.service.CustomUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IBookDAO bookRepository;
    @Autowired
    private CustomUserService userService;


    @Transactional
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return user.getCart();
    }
    @Transactional
    public Cart addToCart(int bookId, int quantity){
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.addItem(book,quantity);
        return saveCart(cart);
    }
    @Transactional
    public Cart removeFromCart(int bookId){
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.removeItem(book);
        return saveCart(cart);
    }



    @Transactional
    public Cart getCurrentUserCart() {
        User currentUser = userService.getCurrentUser();
        return currentUser.getCart();
    }

    @Transactional
    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }


}