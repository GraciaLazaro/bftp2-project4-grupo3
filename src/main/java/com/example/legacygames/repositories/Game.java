package com.example.legacygames.repositories;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;





@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private String pegi;
    private double price;
    private String coverImage;
    private String image;


    public Game() {

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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getPegi() {return pegi;}
    public void setPegi(String pegi) {this.pegi = pegi;}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }







    public Game( String title, String category,String pegi, double price, String image) {
        this.title = title;
        this.category = category;
        this.pegi = pegi;
        this.price = price;
        this.image = image;

    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", pegi=" + pegi +
                ", price=" + price + "€" +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(category, game.category) && Objects.equals(pegi, game.pegi) && Objects.equals(coverImage, game.coverImage) && Objects.equals(image, game.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, pegi, price, coverImage, image);
    }
}