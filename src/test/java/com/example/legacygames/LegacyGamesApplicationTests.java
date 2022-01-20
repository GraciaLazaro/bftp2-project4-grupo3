package com.example.legacygames;

import com.example.legacygames.repositories.CategoryRepository;
import com.example.legacygames.repositories.Game;


import com.example.legacygames.repositories.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class LegacyGamesApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameRepository gameRepository;

   @BeforeEach
   void setUp() {
      gameRepository.deleteAll();
   }


    @Test
    @WithMockUser
    void CargaLaHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    @Test
    @WithMockUser
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/games/new")
                        .param("title", "Wii Sports")
                        .param("category", "Sports")
                        .param("pegi", "7")
                        .param("price", "19.99")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"))
        ;

        List<Game> existingGames = (List<Game>) gameRepository.findAll();
        assertThat(existingGames, contains(allOf(
                hasProperty("title", equalTo("Wii Sports")),
                hasProperty("category", equalTo("Sports")),
                hasProperty("pegi", equalTo("7")),
                hasProperty("price", equalTo(19.99))
        )));
    }


    @Test
    @WithMockUser
    void allowsToDeleteAGame() throws Exception {
        Game game = gameRepository.save(new Game("sims", "Sports", "7", 19.99, "img/wiisports.png"));
        mockMvc.perform(get("/games/delete/" + game.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"));

        assertThat(gameRepository.findById(game.getId()), equalTo(Optional.empty()));
    }



    @Test
    @WithMockUser
    void allowsToEditAnyGame () throws Exception {
        Game game = gameRepository.save(new Game("Wii sports", "Sports", "7", 19.99, "img/wiisports.png"));
        mockMvc.perform(get("/games/edit/" + game.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attribute("game", game))
                .andExpect(model().attribute("title", "Edit game"));
    }
    @Test
    @WithMockUser
    void returnsAFormToAddNewGames() throws Exception {
        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attribute("title", "Create new game"))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Shooter")),
                        hasProperty("name", is("Role-playing")),
                        hasProperty("name", is("Misc"))
                )));
    }

    @Test
    @WithMockUser
    void returnsGamesFromAGivenCategory() throws Exception {

        Game SportsGame = gameRepository.save(new Game("Wii 3","Sports", "7", 19.99, "img/wiisports.png"));

        mockMvc.perform(get("/games?category=sports"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute("games", hasItem(SportsGame)));

    }


    @Test
    @WithMockUser
    void anonymousUsersCannotCreateAGame() throws Exception {
        mockMvc.perform(post("/games/new")
                        .param("title", "Wii Sports")
                        .param("category", "Sports")
                        .param("pegi", "7")
                        .param("price", "19.99")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    void returnsTheExistingGames() throws Exception {

        Game game = gameRepository.save(new Game("Wii Sports", "Sports", "7", 19.99, "img/wiisports.png"));

        mockMvc.perform(get("/games/"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute("games", hasItem(game)))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Shooter")),
                        hasProperty("name", is("Role-playing")),
                        hasProperty("name", is("Misc"))
                )));
    }
}