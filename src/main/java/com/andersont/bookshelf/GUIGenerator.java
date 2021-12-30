package com.andersont.bookshelf;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class GUIGenerator {

    public GridPane generateBookPane(Book book){
        GridPane pane = new GridPane();

        // generate GUI here
        pane.add(new Label("NEW BOOK"), 1, 0); // column=1 row=0

        return pane;
    }

     public GridPane about() {
        return new GridPane();
     }

}
