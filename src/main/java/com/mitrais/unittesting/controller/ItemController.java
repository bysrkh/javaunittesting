package com.mitrais.unittesting.controller;

import com.mitrais.unittesting.business.ItemBusinessService;
import com.mitrais.unittesting.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService itemBusinessService;

    @GetMapping("/dummy-item")
    public Item dummyItem(){
        return new Item(1, "Ball", 10000, 20);
    }

    @GetMapping("/dummy-item-from-business-service")
    public Item itemFromBusinessService(){
        return itemBusinessService.retrieveHardcodedItem();
    }
}
