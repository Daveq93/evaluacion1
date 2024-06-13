package com.uce.edu.evaluacion1;


import com.google.gson.Gson;
import com.uce.edu.evaluacion1.modelo.Book;
import com.uce.edu.evaluacion1.service.BookServiceImpl;
import io.helidon.webserver.WebServer;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.io.Reader;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;

public final class Principal {

    private Principal() {}

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        SeContainer container = initializer.initialize();
        BookServiceImpl bookService = container.select(BookServiceImpl.class).get();

        WebServer server = WebServer.builder()
                .port(8080)
                .routing(r -> r
                        .get("/books",(req,res)->{
                            List<Book> books = bookService.findAll();
                            res.status(200);
                            res.send(new Gson().toJson(books));
                        })
                        .get("/books/{id}",(req,res)->{

                            Book book = bookService.findById(Integer.valueOf(req.path().param("id")));
                            res.status(200);
                           res.send(new Gson().toJson(book));
                        })
                        .post("/books",(req,res)->{
                            req.content().as(String.class);
                            var body = req.content().as(String.class);
                            Book book = new Gson().fromJson((Reader) body, Book.class);
                            res.status(201);
                            bookService.save(book);
                        })
                        .put("/books/{id}",(req,res)->{
                            var body = req.content().as(String.class);
                            Book book = new Gson().fromJson((Reader) body, Book.class);

                            Integer id =Integer.valueOf(req.path().param("id"));
                            book.setId(id);
                            res.status(201);
                            bookService.update(book);
                        })
                        .delete("/books/{id}",(req,res)->{
                            res.status(OK);
                            Book book = bookService.findById(Integer.valueOf(req.path().param("id")));
                            bookService.delete(book);
                        })
                )
                .build();

        server.start();

    }

}
