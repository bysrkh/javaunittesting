package com.mitrais.unittesting;

import org.hamcrest.CoreMatchers;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.service.impl.BookServiceImpl;
import com.mitrais.unittesting.util.model.BookParameter;
import com.mitrais.unittesting.repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private static BookRepository bookRepository;
    private final byte STATUS_ZERO = 0b0000000;
    private final byte STATUS_MINUS_ONE = -1;
    private final String RANDOM_TITLE = "RANDOM_TITLE_01";
    private Book book;
    private List<Book> books;
    private BookParameter parameters;
    private static BookServiceImpl bookService;

    @BeforeClass
    public static void init() {
        bookRepository = mock(BookRepository.class);

        bookService = new BookServiceImpl();
        bookService.setBookRepository(bookRepository);
    }

    @Before
    public void setUp() {
        book = new Book();
        books = new ArrayList<>();
        parameters = new BookParameter();
    }

    @Test
    public void given_StatusParameter_ThenReturn_BooksWithStatusFilter() {
        book.setStatus(STATUS_ZERO);
        book.setTitle("RANDOM_TITLE_01");

        books = Arrays.asList(new Book[]{book, book});

        parameters.setStatus(STATUS_ZERO);

        when(bookRepository.findByStatus(STATUS_ZERO)).thenReturn(books);

        List<Book> actualBooks = bookService.findByParameters(parameters);

        assertThat(books, CoreMatchers.hasItem(actualBooks.get(0)));
        assertThat(books.size(), CoreMatchers.is(actualBooks.size()));
    }

    @Test
    public void given_WithoutParameters_ThenReturn_BooksWithoutFilter() {
        book.setStatus(STATUS_ZERO);
        book.setTitle(RANDOM_TITLE);

        books = Arrays.asList(new Book[]{book, book});

        parameters.setStatus(STATUS_MINUS_ONE);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> actualBooks = bookService.findByParameters(parameters);

        assertThat(books, CoreMatchers.hasItem(actualBooks.get(0)));
        assertThat(books.size(), CoreMatchers.is(actualBooks.size()));
    }

    @Test
    public void given_TitleAndStatusParameters_ThenReturn_BooksWithTitleAndStatusParameters() {
        book.setStatus(STATUS_ZERO);
        book.setTitle(RANDOM_TITLE);

        books = Arrays.asList(new Book[]{book, book});

        parameters.setStatus(STATUS_ZERO);
        parameters.setTitle(RANDOM_TITLE);

        when(bookRepository.findByTitleIsLikeAndStatus(RANDOM_TITLE, STATUS_ZERO)).thenReturn(books);

        List<Book> actualBooks = bookService.findByParameters(parameters);

        assertThat(books, CoreMatchers.hasItem(actualBooks.get(0)));
        assertThat(books.size(), CoreMatchers.is(actualBooks.size()));
    }

        @After
        public void destroy() {
            book = null;
            books = null;
            parameters = null;
        }
}
