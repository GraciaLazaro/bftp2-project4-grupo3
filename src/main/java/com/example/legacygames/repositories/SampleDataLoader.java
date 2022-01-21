package com.example.legacygames.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.awt.SystemColor.info;
import static java.awt.SystemColor.infoText;

@Component
public class SampleDataLoader {
    private GameRepository gameRepository;

    @Autowired
    public SampleDataLoader (GameRepository gameRepository){
    this.gameRepository = gameRepository;
    }

    @PostConstruct
    public void loadSampleData(){
        gameRepository.saveAll(List.of(
                new Game("Wii Sports","Sports", "7", 19.99, "img/wiisports.png", "Juego interactivo basado en los deportes." ),
                new Game("Mario Kart 7","Racing", "7", 19.99, "img/mariokart.png", "Juego de carreras de la familia de Mario Kart"),
                new Game("Call of Duty: Black Ops","Shooter", "16", 19.99, "img/callofdutty.png","Juego bélico de estrategia "),
                new Game("Pokémon Platinum","Role-playing", "7", 8.99, "img/pokemon.png","Nueva versión de Pokemon"),
                new Game("Just Dance 3","Misc", "3", 21.95, "img/justdance.png", "Juego de danza y diversión")
    ));
    }
}