package com.andersont.bookshelf;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book;

    String bookTitle = "Matilda";
    String bookReview = "A really good book.";

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setProperty("title", bookTitle);
        book.setProperty("review", bookReview);
    }

    @Test
    void writeBook() {
        String data = book.writeBook();

        Book book2 = new Book(data);

        assertEquals(book.getString("title"), book2.getString("title"));
        assertEquals(book.getString("readDate"), book2.getString("readDate"));
        assertEquals(book.getString("review"), book2.getString("review"));
    }

    @Test
    void bookData() {
        ArrayList<String> data = book.bookData();

        assertTrue(data.contains(bookTitle));
        assertFalse(data.contains("1984"));
    }

    @Test
    void getString() {
        assertEquals(book.getString("title"), bookTitle);
        assertEquals(book.getString("aaa"), "ERROR FOR KEY aaa");
    }

    @Test
    void getStringProperty() {
        StringProperty test = new SimpleStringProperty(bookTitle);

        assertEquals(book.getStringProperty("title").get(), test.get());
    }

    @Test
    void setProperty() {
        assertEquals(book.getString("title"), bookTitle);

        book.setProperty("title", "Fantastic Mr. Fox");

        assertEquals(book.getString("title"), "Fantastic Mr. Fox");

    }
}