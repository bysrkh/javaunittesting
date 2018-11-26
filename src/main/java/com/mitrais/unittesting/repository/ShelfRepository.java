package com.mitrais.unittesting.repository;

import com.mitrais.unittesting.domain.Shelf;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, String> {
    List<Shelf> findAll();
}
