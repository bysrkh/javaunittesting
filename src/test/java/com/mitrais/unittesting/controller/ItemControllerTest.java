package com.mitrais.unittesting.controller;

import com.mitrais.unittesting.business.ItemBusinessService;
import com.mitrais.unittesting.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService itemBusinessService;

    @Test
    public void dummyItem_basic() throws Exception {
        // call GET "/dummy-item" application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10000,\"quantity\":20}"))
                .andReturn();
    }

    @Test
    public void itemFromBusinessService() throws Exception {
        when(itemBusinessService.retrieveHardcodedItem())
                .thenReturn(new Item(2, "Item2", 25000, 9));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:2,name:Item2,price:25000,quantity:9}"))
                .andReturn();
    }

    @Test
    public void retrieveAllItems_basic() throws Exception {
        when(itemBusinessService.retrieveAllItems())
                .thenReturn(
                        Arrays.asList(
                                new Item(1, "Item1", 15000, 10),
                                new Item(2, "Item2", 25000, 20),
                                new Item(3, "Item3", 35000, 30)
                        )
                );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                        "{id:1,name:Item1,price:15000,quantity:10}," +
                                        "{id:2,name:Item2,price:25000,quantity:20}," +
                                        "{id:3,name:Item3,price:35000,quantity:30}" +
                                    "]"))
                .andReturn();
    }
}