package com.andersont.bookshelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Library extends Book{
    private ObservableList<Book> library = FXCollections.observableArrayList();
    private ArrayList<Shelf> shelves;

    public Shelf activeShelf;
    public Book activeBook;

    public Library() {
        Book example = new Book();
        example.setName("Matilda");
        example.setIsbn(1234);
        this.addBook(example);
    }

    public void writeLibrary(){}

    public void loadLibrary(){}

    public void addBook(Book book){
        library.add(book);
    }

    public void removeBook(Book book){
        library.remove(book);
    }

    public Shelf searchForBook(String searchterm){

        ObservableList<Book> results = FXCollections.observableArrayList();

        // search for options and populate shelf with books here

        return new Shelf(results);
    }

    public Shelf getActiveShelf() {
        return activeShelf != null ? activeShelf : new Shelf(library);
    }
}