package com.andersont.bookshelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    Shelf shelf;
    Book book;

    @BeforeEach
    void setUp() {
        shelf = new Shelf();
        book = new Book();
    }

    @Test
    void addBook() {
        assertEquals(shelf.shelf.size(), 0);
        shelf.addBook(book);
        assertEquals(shelf.shelf.size(), 1);
    }

    @Test
    void removeBook() {
        shelf.addBook(book);
        assertEquals(shelf.shelf.size(), 1);
        shelf.removeBook(book);
        assertEquals(shelf.shelf.size(), 0);
    }
}