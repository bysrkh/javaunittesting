package com.mitrais.unittesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mitrais.unittesting.config.EntityTestConfig;
import com.mitrais.unittesting.domain.Book;
import com.mitrais.unittesting.domain.Shelf;
import com.mitrais.unittesting.service.BookService;
import com.mitrais.unittesting.service.ShelfService;
import com.mitrais.unittesting.util.model.BookParameter;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ShelfController.class)
@ContextConfiguration(classes = EntityTestConfig.class)
public class ShelfControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelfService shelfService;
    @Autowired
    private Shelf shelf;

    @DirtiesContext
    @Test
    public void givenShelfIdParameter_ThenReturn_ShelfWithGivenShelfId() throws Exception {
        when(shelfService.findShelf(any(String.class)))
                .thenReturn(shelf);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/shelf/RANDOM_GUID")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentCapacity", CoreMatchers.is(shelf.getCurrentCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.maxCapacity", CoreMatchers.is(shelf.getMaxCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[*]", Matchers.hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @DirtiesContext
    @Test
    public void givenShelfWithNewAddedBookBodyRequest_ThenReturn_SuccessMessage() throws Exception {
        String jsonRequest = new ObjectMapper().writeValueAsString(shelf);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/shelf/RANDOM_GUID/addBook")
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(202)))
                .andDo(MockMvcResultHandlers.print());

        verify(shelfService)
                .addBookIntoShelf(any(Shelf.class));
    }

    @DirtiesContext
    @Test
    public void givenShelfWithToBeRemovedBookBodyRequest_ThenReturn_SuccessMessage() throws Exception {
        String jsonRequest = new ObjectMapper().writeValueAsString(shelf);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/shelf/RANDOM_GUID/removeBook")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(
                        MockMvcResultMatchers.status().is(303))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(303)))
                .andDo(MockMvcResultHandlers.print());

        verify(shelfService)
                .removeBookFromShelf(any(Shelf.class));
    }

}
