package com.andersont.bookshelf;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    public MenuItem createBook;

    @FXML
    public MenuItem createShelf;

    @FXML
    public MenuItem deleteShelf;

    @FXML
    public CheckMenuItem darkTheme;

    @FXML
    public MenuItem importData;

    @FXML
    public MenuItem exportData;

    @FXML
    public Menu autofill;

    @FXML
    public MenuItem about;

    @FXML
    public GridPane gridpane;
}