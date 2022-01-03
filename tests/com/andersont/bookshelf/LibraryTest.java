package com.andersont.bookshelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Library library;
    Book book;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book();
    }

    @Test
    void addBook() {
        library.addBook(book);

        assert(library.getActiveShelf().shelf.get(0) == book);
    }

    @Test
    void removeBook() {
        library.addBook(book);
        library.removeBook(book);
        assert(library.getActiveShelf().shelf.isEmpty());
    }

    @Test
    void searchForBook() {
        Book book1 = book;
        Book book2 = new Book();
        Book book3 = new Book();

        book1.setProperty("title", "abc123");
        book2.setProperty("review", "abc456");
        book3.setProperty("readDate", "January 12");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.searchForBook("abc");
        assertTrue(library.activeShelf.shelf.contains(book1));
        assertTrue(library.activeShelf.shelf.contains(book2));
        assertFalse(library.activeShelf.shelf.contains(book3));

        library.searchForBook("12");
        assertTrue(library.activeShelf.shelf.contains(book1));
        assertFalse(library.activeShelf.shelf.contains(book2));
        assertTrue(library.activeShelf.shelf.contains(book3));
    }

    @Test
    void getActiveShelf() {
        assertNotNull(library.getActiveShelf());
        library.searchForBook("");
        assertNotNull(library.getActiveShelf());
        library.searchForBook("text");
        assertNotNull(library.getActiveShelf());
    }

    @Test
    void getLibrary() {
        assertNotNull(library.getLibrary());
        library.addBook(book);

        assertTrue(library.getLibrary().contains(book));
    }

    @Test
    void getBookID() {
        library.addBook(book);
        library.addBook(new Book());
        assertEquals(library.getBookID(), 2);
    }

    @Test
    void setBookID() {
        library.setBookID(19823);
        assertEquals(library.getBookID(), 19823);
    }
}