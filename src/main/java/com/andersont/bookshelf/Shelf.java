package com.andersont.bookshelf;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Shelf {
    private String name;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;
    private ArrayList<Book> shelf;

    Shelf(){
        this(new ArrayList<Book>());
    }

    Shelf(ArrayList<Book> books){
        this.createddate = java.time.LocalDateTime.now();
        this.lastmodified = this.createddate;
        this.shelf = books;
    }

    public String writeShelf(){
        return "";
    }

    public ArrayList<Book> getShelf(){
        return shelf = new ArrayList<>();
    }
}