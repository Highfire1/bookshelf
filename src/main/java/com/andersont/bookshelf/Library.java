package com.andersont.bookshelf;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Library extends Book{
    // The centerpiece of the operation: The Library
    // Stores all books and keeps them in order

    private Shelf library = new Shelf(); // main library
    private ArrayList<Shelf> shelves = new ArrayList<>(); // other shelves (not implemented)

    public Shelf activeShelf = null; // tracks if searchbar is in use

    private int bookID = 0; // tracks # of books; not very cross device friendly but that's not in the specifications :)

    public void addBook(Book book){
        // main method for adding a book to the library

        // set book id if not written
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

    public void searchForBook(String searchterm){
        // Search function
        // Probably the most terrible way to implement it but no one's counting o(n) here anyway

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

    // GETTERS AND SETTERS
    public Shelf getActiveShelf() {
        // Yet another layer of abstraction so JavaFX doesn't get murdered by nulls
        if (activeShelf == null) {
            return library;
        } else {
            return activeShelf;
        }
    }

    public ObservableList<Book> getLibrary() {
        return library.shelf;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}