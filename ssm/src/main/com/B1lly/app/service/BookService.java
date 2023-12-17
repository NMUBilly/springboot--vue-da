package com.B1lly.app.service;

import com.B1lly.app.bean.Book;

import java.util.List;

public interface BookService {
    List<Book> listBookPrice();
    List<Book> listPage(int page,int size,String bname);
    int count(String bname);
    boolean deleteBook(int bid);
    boolean addBook(Book book);
    boolean editBook(Book book);
}
