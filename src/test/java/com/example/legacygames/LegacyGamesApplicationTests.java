package com.example.legacygames;

import com.example.legacygames.repositories.Game;


import com.example.legacygames.repositories.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LegacyGamesApplicationTests {

    @Autowired
    MockMvc mockMvc;


    /*@BeforeEach
    void setUp() {
        gameRepository.deleteAll();
    }*/

    @Autowired
    GameRepository gameRepository;

   @BeforeEach
   void setUp() {
      gameRepository.deleteAll();
   }




    @Test

    void CargaLaHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    /*@Test
    void returnsTheExistingGames() throws Exception {

        Game game = gameRepository.save(new Game("Wii Sports", "Sports", 7, 19.99));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute("games", hasItem(game)));

    }*/

    }
    @Test
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/Games/new")
                        .param("title", "Wii Sports")
                        .param("category", "Sports")
                        .param("pegi", "7")
                        .param("price", "19.99")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"))
        ;

        List<Game> existingBooks = (List<Game>) gameRepository.findAll();
        assertThat(existingBooks, contains(allOf(
                hasProperty("title", equalTo("Wii Sports")),
                hasProperty("pegi", equalTo("7")),
                hasProperty("category", equalTo("Sports")),
                hasProperty("price", equalTo("19.99"))
        )));
    }




}