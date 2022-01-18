package com.example.legacygames.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;





@Repository
public class CategoryRepository {

    public CategoryRepository() {
    }

    public List<Category> findAll() {
        return List.of(
                new Category("Sports"),
                new Category("Racing"),
                new Category("Shooter"),
                new Category("Role-playing"),
                new Category("Misc")
        );
    }
}
