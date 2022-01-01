package com.andersont.bookshelf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.Properties;


public class Controller {
    public Library library;
    private GUIGenerator generator;

    Book tempBook = new Book();

    @FXML
    public ListView list;
    public Menu autofill;
    public CheckMenuItem darkTheme;
    public BorderPane contentpane;

    //String savelocation = System.getProperty("user.dir") + "\bookshelf_data.txt";
    public String saveLocation = "C:\\Users\\Ander\\Desktop\\temp";
    public Properties properties = IO.getBundleFile();

    public void initialize(){
        library = IO.loadLibrary(saveLocation);
        generator = new GUIGenerator();

        // ObservableList is a godsend
        list.setItems(library.getActiveShelf().shelf);

        // load last book
        contentpane.setCenter( generator.generateBookPane(library.getActiveShelf().shelf.get(0)));
        list.getSelectionModel().select(library.getActiveShelf().shelf.get(0));
    };

    public void deleteBook(ActionEvent actionEvent) {
        library.removeBook((Book) list.getSelectionModel().getSelectedItem());
    }

    public void createBook(ActionEvent actionEvent) {

        Book b = new Book();
        b.setProperty("title", "BOOK_TITLE");
        library.addBook(b);
        contentpane.setCenter( generator.generateBookPane(b) );
        list.getSelectionModel().select(b);
    }

    public void listClicked(MouseEvent mouseEvent) {
        Book b = (Book) list.getSelectionModel().getSelectedItem();

        // don't reload if blank item clicked
        // necessary because we're just using the selected item
        if (b != tempBook) {
            contentpane.setCenter( generator.generateBookPane(b) );
            tempBook = b;
        }
    }

    public void createShelf(ActionEvent actionEvent) {
        Shelf sh = new Shelf();
        library.addShelf(sh);
    }

    public void deleteShelf(ActionEvent actionEvent) {
        contentpane.setCenter( generator.deleteShelfPane() );
    }

    public void toggleDarkTheme(ActionEvent actionEvent) {

    }

    public void importData(ActionEvent actionEvent) {
        contentpane.setCenter( generator.importPane() );
        initialize();
        System.out.println("INITIALIZING");
    }

    public void exportData(ActionEvent actionEvent) {
        IO.writeLibrary(saveLocation, library);
        contentpane.setCenter( generator.exportPane() );
    }

    public void toggleAutofill(ActionEvent actionEvent) {
    }

    public void about(ActionEvent actionEvent) {
        contentpane.setCenter( generator.aboutPane() );
    }


}