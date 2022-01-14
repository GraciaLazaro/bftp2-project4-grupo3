package com.example.legacygames;

import com.example.legacygames.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoryRepositoryTest {
    @Test
    void providesTheValidCategories() {
        CategoryRepository categoryRepository = new CategoryRepository();

        assertThat(categoryRepository.findAll(), hasItems(
                hasProperty("name", is("Sports")),
                hasProperty("name", is("Racing")),
                hasProperty("name", is("Shooter")),
                hasProperty("name", is("Role-playing")),
                hasProperty("name", is("Misc"))
        ));
    }
}
