package com.example.legacygames.repositories;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private int pegi;
    private double price;



    public Game() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return pegi == game.pegi && Double.compare(game.price, price) == 0 && Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(category, game.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, pegi, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Game( String title, String category, int pegi, double price) {
        this.title = title;
        this.category = category;
        this.pegi = pegi;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", pegi=" + pegi +
                ", price=" + price + "€" +
                '}';
    }







}