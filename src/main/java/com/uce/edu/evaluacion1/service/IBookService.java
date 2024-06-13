package com.uce.edu.evaluacion1.service;

import com.uce.edu.evaluacion1.modelo.Book;

import java.util.List;

public interface IBookService {

    Book findById(Integer id);
    List<Book> findAll();
    Book save(Book book);
    Book update(Book book);
    Integer delete(Book book);


}
