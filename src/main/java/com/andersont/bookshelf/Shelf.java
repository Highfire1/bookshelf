package com.andersont.bookshelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Essentially a wrapper around an ObservableList
public class Shelf {
    private String name;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;

    public ObservableList<Book> shelf;

    Shelf(){
        // calls below
        // have to use a factory instead of creating directly because JavaFX is silly ;-;
        this(FXCollections.observableArrayList());
    }

    Shelf(ObservableList<Book> books){
        this.createddate = java.time.LocalDateTime.now();
        this.lastmodified = java.time.LocalDateTime.now();
        this.shelf = books;
    }


}