package com.andersont.bookshelf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import static com.andersont.bookshelf.Application.library;


public class Controller {

    @FXML
    public static ListView list;
    public MenuItem about;
    public Menu autofill;
    public CheckMenuItem darkTheme;
    public static BorderPane contentpane;

    public void createBook(ActionEvent actionEvent) {

        // this looks suspicious but it works ok
        Book b = new Book();
        library.addBook(b);
        library.activeBook = b;

        GUIGenerator.updatePane();
        GUIGenerator.updateList();


        System.out.println("HI!");
    }

    public void createShelf(ActionEvent actionEvent) {
    }

    public void deleteShelf(ActionEvent actionEvent) {
    }

    public void toggleDarkTheme(ActionEvent actionEvent) {
    }

    public void importData(ActionEvent actionEvent) {
    }

    public void exportData(ActionEvent actionEvent) {
    }

    public void toggleAutofill(ActionEvent actionEvent) {
    }
}