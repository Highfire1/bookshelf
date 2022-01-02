package com.andersont.bookshelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Library extends Book{
    private Shelf library = new Shelf();
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

        library.addBook(book);
    }

    public void removeBook(Book book){
        library.removeBook(book);
    }

    public void addShelf(Shelf shelf){
        shelves.add(shelf);
    }

    public void removeShelf(Shelf shelf){
        shelves.remove(shelf);
    }

    public void searchForBook(String searchterm){
        if (searchterm.isBlank()) {
            activeShelf = library;
            return;
        }
        Shelf results = new Shelf();

        for (Book book : library.shelf) {
            for (String data : book.bookData()) {
                if (data.toLowerCase().contains(searchterm.toLowerCase())) {
                    //System.out.println("MATCH: " + searchterm + ", " + data);
                    results.addBook(book);
                    break;
                }
            }
        }

        activeShelf = results;
    }

    public Shelf getActiveShelf() {
        if (activeShelf == null) {
            return library;
        } else {
            return activeShelf;
        }
    }

    public ObservableList<Book> getLibrary() {
        return library.shelf;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}