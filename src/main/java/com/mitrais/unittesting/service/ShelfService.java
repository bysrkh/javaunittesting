package com.mitrais.unittesting.service;

import com.mitrais.unittesting.domain.Shelf;

import java.util.List;

public interface ShelfService {
    List<Shelf> findAll();
    Shelf findShelf(String id);
    void addBookIntoShelf(Shelf shelf);
    void removeBookFromShelf(Shelf shelf);
}
