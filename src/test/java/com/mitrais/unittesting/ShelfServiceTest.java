package com.mitrais.unittesting;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.domain.Shelf;
import com.mitrais.unittesting.repository.BookRepository;
import com.mitrais.unittesting.repository.ShelfRepository;
import com.mitrais.unittesting.service.impl.ShelfServiceImpl;

import org.hamcrest.CoreMatchers;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShelfServiceTest {
    private static ShelfRepository shelfRepository;
    private static BookRepository bookRepository;
    private final byte STATUS_ZERO = 0b0000000;
    private final String RANDOM_TITLE = "RANDOM_TITLE_01";
    private final String RANDOM_GUID = "RANDOM_GUID_01";
    private Shelf shelf;
    private Book book;
    private List<Book> books;
    private static ShelfServiceImpl shelfService;

    @BeforeClass
    public static void init() {
        bookRepository = mock(BookRepository.class);
        shelfRepository = mock(ShelfRepository.class);

        shelfService = new ShelfServiceImpl();
        shelfService.setShelfRepository(shelfRepository);
        shelfService.setBookRepository(bookRepository);
    }

    @Before
    public void setUp() {
        shelf = new Shelf();
        book = new Book();
        books = new ArrayList<>();
    }

    @Test
    public void given_ShelfId_ThenReturnShelf() {
        book.setTitle(RANDOM_TITLE);
        book.setStatus(STATUS_ZERO);

        books = Arrays.asList(new Book[]{book, book});

        shelf.setId(RANDOM_GUID);
        shelf.setBooks(books);

        when(shelfRepository.findOne(RANDOM_GUID)).thenReturn(shelf);

        Shelf actualShelf = shelfService.findShelf(RANDOM_GUID);

        assertThat(shelf.getId(), CoreMatchers.is(actualShelf.getId()));
        assertThat(shelf.getBooks(), CoreMatchers.hasItem(actualShelf.getBooks().get(0)));
    }

    @Test
    public void given_AddedBookIntoShelf_ThenReturnShelfWithAddedBook() {
        book.setId(RANDOM_GUID);

        books.add(book);
        books.add(book);

        shelf.setId(RANDOM_GUID);
        shelf.setBooks(books);

        Shelf existingShelf = new Shelf();
        existingShelf.setMaxCapacity(100);
        existingShelf.setCurrentCapacity(0);
        existingShelf.setBooks(new ArrayList<Book>());

        when(bookRepository.findOne(RANDOM_GUID)).thenReturn(book);
        when(shelfRepository.findOne(RANDOM_GUID)).thenReturn(existingShelf);

        shelfService.addBookIntoShelf(shelf);

        verify(shelfRepository).save(existingShelf);
    }

    @Test
    public void given_RemovedBookFromShelf_ThenReturnShelfWithRemovedBook() {
        book.setId(RANDOM_GUID);

        books.add(book);
        books.add(book);

        shelf.setId(RANDOM_GUID);
        shelf.setBooks(books);

        Shelf existingShelf = new Shelf();
        existingShelf.setMaxCapacity(100);
        existingShelf.setCurrentCapacity(1);
        existingShelf.setBooks(new ArrayList<Book>());

        when(bookRepository.findOne(RANDOM_GUID)).thenReturn(book);
        when(shelfRepository.findOne(RANDOM_GUID)).thenReturn(existingShelf);

        shelfService.removeBookFromShelf(shelf);

        verify(shelfRepository).save(existingShelf);
    }

    @After
    public void destroy() {
        shelf = null;
        book = null;
        books = null;
    }
}
