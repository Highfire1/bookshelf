package com.andersont.bookshelf;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Controller {
    public Library library;

    @FXML
    public ListView list;
    public MenuItem about;
    public Menu autofill;
    public CheckMenuItem darkTheme;
    public BorderPane contentpane;

    public void initialize(){
        library = new Library();

        // set up observableList for ListView
        library.getActiveShelf().shelf.addListener((ListChangeListener<? super Book>) l -> {
            updateListView();
        });
        updateListView();

    };

    public void updateListView(){
        list.setItems(library.getActiveShelf().shelf);
    }


    public void createBook(ActionEvent actionEvent) {

        Book b = new Book();
        library.addBook(b);
        library.activeBook = b;

        GUIGenerator generator = new GUIGenerator();

        contentpane.setCenter( generator.generateBookPane(library.activeBook) );
    }

    public void setContentpane(GridPane pane){
        contentpane.setCenter(pane);
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