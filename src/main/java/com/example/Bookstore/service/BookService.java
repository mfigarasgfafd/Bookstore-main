package com.example.Bookstore.service;

import com.example.Bookstore.DAO.IBookDAO;
import com.example.Bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private final IBookDAO bookDAO;
    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @Override
    @Transactional
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }
    @Override
    @Transactional
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }
    @Transactional
    public void saveOrUpdate(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }
    @Override
    @Transactional
    public void delete(int id){
        bookDAO.delete(id);
    }
}