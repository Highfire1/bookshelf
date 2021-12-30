package com.andersont.bookshelf;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.andersont.bookshelf.Application.library;
import static com.andersont.bookshelf.Controller.contentpane;
import static com.andersont.bookshelf.Controller.list;

public class GUIGenerator {

    public static void updatePane(){
        Book book = library.activeBook;

        GridPane gridpane = new GridPane();

        gridpane.add(new Label("NEW BOOK"), 1, 0); // column=1 row=0
        gridpane.add(new Label(book.getName()), 2, 0);  // column=2 row=0

        contentpane.setCenter(gridpane); // memory leaks? never heard of it
    }

     public GridPane about() {
        return new GridPane();
     }

     public static void updateList() {
        //list.setItems(library.getActiveShelf().shelf);
     }

}
