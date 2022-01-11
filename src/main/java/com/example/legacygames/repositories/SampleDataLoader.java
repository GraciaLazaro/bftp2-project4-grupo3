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
                new Game("Wii Sports","Wii Sports","Sports","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.gamulator.com%2Fes%2Froms%2Fnintendo-wii%2Fwii-sports&psig=AOvVaw1rgRxB6p0KLx2_bSI5Pbht&ust=1641998399349000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLjTifH2qfUCFQAAAAAdAAAAABAZ", 7, 19.99),
                new Game("Mario Kart 7","Mario Kart 7","Racing","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.instant-gaming.com%2Fes%2F2613-comprar-juego-nintendo-mario-kart-7-3ds%2F&psig=AOvVaw3i2VVWpmerP8rhJQj3M9oP&ust=1641998618225000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIjx-dX3qfUCFQAAAAAdAAAAABAD" 7,19.99),
                new Game("Call of Duty: Black Ops","Shooter", 16, 19.99),
                new Game("Pokémon Platinum Version","Role-Playing", 7, 8.99),
                new Game("Just Dance 3","Misc", 3, 21.95)
    ));
    }
}