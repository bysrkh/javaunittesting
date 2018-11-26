package com.mitrais.unittesting.repository;

import com.mitrais.unittesting.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {
    List<Book> findAll();
    List<Book> findByStatus(byte status);
    List<Book> findByTitleIsLikeAndStatus(String title, byte status);
}
