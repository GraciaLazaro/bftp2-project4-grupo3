package com.example.legacygames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>  {

    List<Game> findGamesByTitleContaining(String word);

    List<Game> findGamesByByCategoryEquals(String category);
}




