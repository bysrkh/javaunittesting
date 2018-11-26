package com.mitrais.unittesting.config;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.domain.Shelf;
import com.mitrais.unittesting.util.model.BookParameter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class EntityTestConfig {

    @Bean
    public Shelf shelf(List<Book> books) {
        Shelf shelf = new Shelf();
        shelf.setId("RANDOM_UUID");
        shelf.setMaxCapacity(100);
        shelf.setCurrentCapacity(0);
        shelf.setBooks(books);

        return shelf;
    }

    @Bean
    public Book book() {
        Book book = new Book();
        book.setStatus((byte) 1);
        book.setTitle("RANDOM_TITLE");

        return book;
    }

    @Bean
    public List<Book> books(Book book) {
        List<Book> books = Arrays.asList(new Book[]{book, book});

        return books;
    }
}
