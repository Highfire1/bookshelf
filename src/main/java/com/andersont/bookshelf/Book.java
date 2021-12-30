package com.andersont.bookshelf;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Book {
    private String name;
    private String review;
    private LocalDate readdate;
    private Integer rating;

    private Hashtable<String, String> properties;

    // actual book data
    private ArrayList<Image> thumbnails;
    private Image thumbnail;
    private Integer isbn;

    private ArrayList<String> tags;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReaddate() {
        return readdate;
    }

    public void setReaddate(LocalDate readdate) {
        this.readdate = readdate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Hashtable<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Hashtable<String, String> properties) {
        this.properties = properties;
    }

    public ArrayList<Image> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<Image> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
