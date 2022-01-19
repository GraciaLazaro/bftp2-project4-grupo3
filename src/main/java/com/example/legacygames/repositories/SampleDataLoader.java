package com.example.legacygames.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
                new Game("Wii Sports","Sports", "7", 19.99),
                new Game("Mario Kart 7","Racing", "7", 19.99),
                new Game("Call of Duty: Black Ops","Shooter", "16", 19.99),
                new Game("Pokémon Platinum Version","Role-Playing", "7", 8.99),
                new Game("Just Dance 3","Misc", "3", 21.95)
    ));
    }
}