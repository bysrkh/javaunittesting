package com.mitrais.unittesting.service;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.util.model.BookParameter;

import java.util.List;

public interface BookService {
    List<Book> findByParameters(BookParameter parameter);
}
