package com.example.legacygames.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PegiRepository {

    public PegiRepository() {
    }

    public List<Pegi> findAll() {
        return List.of(
                new Pegi("3"),
                new Pegi("7"),
                new Pegi("16")
        );
    }
}
