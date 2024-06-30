package com.example.Bookstore.model;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private BigDecimal price;
    private int quantity;
    private String image;

    public Book(int id){
        this.id = id;
    }
}