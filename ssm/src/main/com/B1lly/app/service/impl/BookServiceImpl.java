package com.B1lly.app.service.impl;

import com.B1lly.app.bean.Book;
import com.B1lly.app.DAO.BookDAO;
import com.B1lly.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    public List<Book> listBookPrice() {
        return bookDAO.listBookPrice();
    }

    @Override
    public List<Book> listPage(int page,int size,String bname) {
        return bookDAO.listPage((page - 1) * size,size,bname);
    }

    @Override
    public int count(String bname) {
        return bookDAO.count(bname);
    }

    @Override
    public boolean deleteBook(int bid) {
        return (bookDAO.deleteBook(bid) > 0);
    }

    @Override
    public boolean addBook(Book book) {
        return (bookDAO.addBook(book) > 0);
    }

    @Override
    public boolean editBook(Book book) {
        return (bookDAO.editBook(book) > 0);
    }
}

