package com.example.Bookstore.repository;

import com.example.Bookstore.model.Order;
import com.example.Bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}