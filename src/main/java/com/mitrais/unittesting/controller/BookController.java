package com.mitrais.unittesting.controller;

import com.mitrais.unittesting.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.unittesting.service.BookService;
import com.mitrais.unittesting.util.model.BookParameter;

import java.util.List;

@RestController
public class BookController {
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> findByParameters(String title, @RequestParam(defaultValue = "-1") int status) {
        BookParameter parameters = new BookParameter();
        parameters.setTitle(title);
        parameters.setStatus((byte) status);

        return bookService.findByParameters(parameters);
    }


    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
