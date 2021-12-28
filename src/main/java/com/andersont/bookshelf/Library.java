package com.andersont.bookshelf;

import java.util.ArrayList;

public class Library extends Book{
    private ArrayList<Book> library;
    private ArrayList<Shelf> shelves;

    public Library() {
    }

    public void writeLibrary(){}

    public void loadLibrary(){}

    public void addBook(Book book){}

    public void removeBook(Book book){}

    public Shelf searchForBook(String searchterm){

        ArrayList<Book> results = new ArrayList<>();
        // search for options and populate shelf with books here

        return new Shelf(results);
    }



}