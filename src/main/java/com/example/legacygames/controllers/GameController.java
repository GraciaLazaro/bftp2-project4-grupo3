package com.example.legacygames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    private GameRepository gameRepository;

    @Autowired
    public GameController (GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    String listGames (Model model){
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("title", "Game list");
        model.addAttribute("games", games);
        return "games/all";
    }
}

