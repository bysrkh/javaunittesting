package com.mitrais.unittesting.business;

import com.mitrais.unittesting.model.Item;
import com.mitrais.unittesting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusinessService {

    @Autowired
    private ItemRepository itemRepository;

    public Item retrieveHardcodedItem(){
        return new Item(2, "Glass", 20000, 10);
    }

    public List<Item> retrieveAllItems(){

        List<Item> all = itemRepository.findAll();
        all.forEach(a -> a.setValue(a.getPrice()*a.getQuantity()));

        return all;
    }
}
