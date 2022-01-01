package com.andersont.bookshelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Library extends Book{
    private ObservableList<Book> library = FXCollections.observableArrayList();
    private ArrayList<Shelf> shelves = new ArrayList<>();


    public Shelf activeShelf = null;

    private int bookID = 0;

    Library() {
    }

    public void addBook(Book book){

        // set book id if unknown
        if (book.getString("bookID") == "") {
            book.setProperty("bookID", String.valueOf(bookID));
            bookID++;
        }

        // avoid bookID collisions
        if (Integer.parseInt(book.getString("bookID")) > bookID) {
            bookID = Integer.parseInt(book.getString("bookID")) + 1;
        }

        library.add(book);
    }

    public void removeBook(Book book){
        library.remove(book);
    }

    public void addShelf(Shelf shelf){
        shelves.add(shelf);
    }

    public void removeShelf(Shelf shelf){
        shelves.remove(shelf);
    }

    public Shelf searchForBook(String searchterm){

        ObservableList<Book> results = FXCollections.observableArrayList();

        // search for options and populate shelf with books here

        return new Shelf(results);
    }

    public Shelf getActiveShelf() {
        return activeShelf != null ? activeShelf : new Shelf(library);
    }

    public ObservableList<Book> getLibrary() {
        return library;
    }

    public void setLibrary(ObservableList<Book> library) {
        this.library = library;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(ArrayList<Shelf> shelves) {
        this.shelves = shelves;
    }

    public void setActiveShelf(Shelf activeShelf) {
        this.activeShelf = activeShelf;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}