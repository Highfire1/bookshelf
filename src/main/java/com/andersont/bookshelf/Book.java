package com.andersont.bookshelf;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Book {
    private String name;
    //private Review review;
    private LocalDate readdate;
    private Integer rating;

    private Hashtable<String, String> properties;

    // actual book data
    private ArrayList<Image> thumbnails;
    private Image thumbnail;
    private Integer isbn;

    private ArrayList<String> tags;


}
