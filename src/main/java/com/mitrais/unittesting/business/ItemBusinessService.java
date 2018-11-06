package com.mitrais.unittesting.business;

import com.mitrais.unittesting.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemBusinessService {

    public Item retrieveHardcodedItem(){
        return new Item(2, "Glass", 20000, 10);
    }
}
