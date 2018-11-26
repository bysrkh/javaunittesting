package com.mitrais.unittesting.controller;

import com.mitrais.unittesting.domain.Shelf;
import com.mitrais.unittesting.service.ShelfService;
import com.mitrais.unittesting.util.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShelfController {
    private ShelfService shelfService;

    @GetMapping("/shelf/{id}")
    public Shelf findShelf(@PathVariable String id) {

        return shelfService.findShelf(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/shelf/{id}/addBook")
    public MessageResponse addBookIntoShelf(@RequestBody Shelf shelf) {
        shelfService.addBookIntoShelf(shelf);

        return new MessageResponse(202, String.format("Shelf with id %s has been added with some books", shelf.getId()));
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PutMapping("/shelf/{id}/removeBook")
    public MessageResponse removeBookFromShelf(@RequestBody Shelf shelf) {
        shelfService.removeBookFromShelf(shelf);

        return new MessageResponse(303, String.format("Shelf with id %s has been updated with some books has been removed", shelf.getId()));
    }

    @Autowired
    public void setShelfService(ShelfService shelfService) {
        this.shelfService = shelfService;
    }
}
