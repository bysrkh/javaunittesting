package com.mitrais.unittesting.service.impl;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.domain.Shelf;
import com.mitrais.unittesting.repository.BookRepository;
import com.mitrais.unittesting.repository.ShelfRepository;
import com.mitrais.unittesting.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {
    private ShelfRepository shelfRepository;
    private BookRepository bookRepository;

    @Override
    public List<Shelf> findAll() {

        return shelfRepository.findAll();
    }

    @Override
    public Shelf findShelf(String id) {

        return shelfRepository.findOne(id);
    }

    @Transactional
    @Override
    public void addBookIntoShelf(Shelf shelf) {
        Shelf existingShelf = shelfRepository.findOne(shelf.getId());

        if(existingShelf.getCurrentCapacity() < existingShelf.getMaxCapacity()) {
            for (Book book: shelf.getBooks()) {
                book = bookRepository.findOne(book.getId());
                existingShelf.getBooks().add(book);
            }
            shelfRepository.save(existingShelf);
        }
    }

    @Transactional
    @Override
    public void removeBookFromShelf(Shelf shelf) {
        Shelf existingShelf = shelfRepository.findOne(shelf.getId());

        if(existingShelf.getCurrentCapacity() > 0) {
            for (Book book: shelf.getBooks()) {
                book = bookRepository.findOne(book.getId());
                existingShelf.getBooks().remove(book);
            }
            shelfRepository.save(existingShelf);
        }
    }

    @Autowired
    public void setShelfRepository(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
