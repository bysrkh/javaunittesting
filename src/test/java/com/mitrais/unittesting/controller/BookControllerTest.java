package com.mitrais.unittesting.controller;

import com.mitrais.unittesting.config.EntityTestConfig;
import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.service.BookService;
import com.mitrais.unittesting.util.model.BookParameter;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = EntityTestConfig.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Autowired
    private BookParameter parameters;
    @Autowired
    private List<Book> books;

    @DirtiesContext
    @Test
    public void givenStatusParameter_ThenReturn_BooksWithGivenStatus() throws Exception {
        when(bookService.findByParameters(any(BookParameter.class)))
                .thenReturn(books);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/books?status=1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", CoreMatchers.is(books.get(0).getTitle())))
                .andDo(MockMvcResultHandlers.print());
    }

    @DirtiesContext
    @Test
    public void givenStatusAndTitleParameters_ThenReturn_BooksWithGivenStatusAndTitleParameters() throws Exception {
        when(bookService.findByParameters(any(BookParameter.class)))
                .thenReturn(books);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/books?status=1&title=RANDOM_TITLE")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", CoreMatchers.is(books.get(0).getTitle())))
                .andDo(MockMvcResultHandlers.print());
    }

}
