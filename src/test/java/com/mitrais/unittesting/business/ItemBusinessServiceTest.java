package com.mitrais.unittesting.business;

import com.mitrais.unittesting.model.Item;
import com.mitrais.unittesting.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemBusinessServiceTest {

    @InjectMocks
    private ItemBusinessService itemBusinessService;
    @Mock
    private ItemRepository itemRepository;

    @Test
    public void retrieveAllItems_Basic(){
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Item(1, "Item1", 1000, 10),
                new Item(2, "Item2", 2000, 20),
                new Item(3, "Item3", 3000, 30)
        ));

        List<Item> items = itemBusinessService.retrieveAllItems();
        assertEquals(3, items.size());
        assertEquals(1, items.get(0).getId());
        assertEquals(10000, items.get(0).getValue());
        assertEquals(40000, items.get(1).getValue());
        assertEquals(90000, items.get(2).getValue());
    }

}