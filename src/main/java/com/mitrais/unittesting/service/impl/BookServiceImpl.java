package com.mitrais.unittesting.service.impl;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.repository.BookRepository;
import com.mitrais.unittesting.service.BookService;
import com.mitrais.unittesting.util.model.BookParameter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public List<Book> findByParameters(BookParameter parameter) {
        List<Book> books;
        if(isNotBlank(parameter.getTitle()) && NumberUtils.compare(parameter.getStatus(), 0b00000000) > -1) {
            books = bookRepository.findByTitleIsLikeAndStatus(parameter.getTitle(), parameter.getStatus());
        } else if(isBlank(parameter.getTitle()) && NumberUtils.compare(parameter.getStatus(), 0b00000000) > -1) {
            books = bookRepository.findByStatus(parameter.getStatus());
        } else {
            books = bookRepository.findAll();
        }

        return books;

    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
