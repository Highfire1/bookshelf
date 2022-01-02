package com.andersont.bookshelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// This class is essentially a wrapper around an ObservableList, with a few details
public class Shelf {
    public String name;
    public LocalDateTime createddate;
    public LocalDateTime lastmodified;

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
        this.name = "New Shelf";
    }

    // load from saved data
    Shelf(String data, ObservableList<Book> src){
        String[] values = data.split("\n");

        this.name = values[0].split("::::")[1];
        this.createddate = LocalDateTime.parse( values[1].split("::::")[1] );
        this.lastmodified = LocalDateTime.parse( values[2].split("::::")[1] );

        String[] shelfBooks =  values[4].split("::::");

        for (Book book : src) {
            for (String bookID : shelfBooks) {
                if (book.getString("bookID").equals(bookID)) {
                    shelf.add(book);
                }
            }
        }
    }

    public void addBook(Book book) {
        shelf.add(book);
    }

    public void removeBook(Book book) {
        shelf.remove(book);
    }

    public void printShelf(){
        for (Book book : shelf) {
            System.out.println(book);
        }
    }


}