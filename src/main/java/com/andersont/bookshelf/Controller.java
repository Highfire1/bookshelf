package com.andersont.bookshelf;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.util.Properties;


public class Controller {
    public Library library;
    public TextField searchBar;
    public Menu shelfMenu;
    private GUIGenerator generator;

    Book tempBook = new Book();

    @FXML
    public ListView list;
    public Menu autofill;
    public CheckMenuItem darkTheme;
    public BorderPane contentpane;

    String savelocation = System.getProperty("user.dir") + "\\bookshelf_data.txt";
    public String saveLocation = "C:\\Users\\Ander\\Desktop\\temp";
    public Properties properties = IO.getBundleFile();

    public void initialize(){
        library = IO.loadLibrary(saveLocation);
        generator = new GUIGenerator();

        // ObservableList is a godsend
        list.setItems(library.getActiveShelf().shelf);

        // load last book
        viewBook(library.getActiveShelf().shelf.get(0));

        // load shelves
        reloadShelves();
    }

    public void deleteBook(ActionEvent actionEvent) {
        library.removeBook((Book) list.getSelectionModel().getSelectedItem());
        contentpane.setCenter(generator.emptyPane());
    }

    public void createBook(ActionEvent actionEvent) {

        Book book = new Book();
        book.setProperty("title", "BOOK_TITLE");
        library.addBook(book);
        viewBook(book);
        list.getSelectionModel().select(book);
    }

    public void listClicked(MouseEvent mouseEvent) {
        viewBook( (Book) list.getSelectionModel().getSelectedItem() );

    }

    public void viewBook(Book book){
        contentpane.setCenter( generator.generateBookPane(book) );
        list.getSelectionModel().select(book);
        list.refresh();

        // refresh listview if title changes because observableMap doesn't do that for some reason?
        book.getStringProperty("title").addListener((observableValue, oldVal, newVal) -> {
            list.refresh();
        });
    }

    public void createShelf(ActionEvent actionEvent) {
        Shelf sh = new Shelf();
        library.addShelf(sh);
        reloadShelves();
    }

    public void deleteShelf(ActionEvent actionEvent) {
        contentpane.setCenter( generator.deleteShelfPane() );
    }

    public void reloadShelves() {
        shelfMenu.show();

        for (Shelf shelf : library.getShelves()) {
            CheckMenuItem menuItem = new CheckMenuItem(shelf.name);

            menuItem.setOnAction(event -> {
                library.activeShelf = shelf;
                list.setItems(library.getActiveShelf().shelf);



                System.out.println("BUTTON PRESSED");
            });

            shelfMenu.getItems().add(menuItem);
        }

        for (MenuItem item : shelfMenu.getItems()) {
            //System.out.println(item.getText() + item.getClass());
        }
    }

    public void toggleDarkTheme(ActionEvent actionEvent) {

    }

    public void importData(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(contentpane.getScene().getWindow());

        // select zip to import
        // unpack zip
        // load zip
    }

    public void exportData(ActionEvent actionEvent) {
        IO.writeLibrary(saveLocation, library);

        // compress to zip
        // then open fileChooser with the zip
    }

    public void toggleAutofill(ActionEvent actionEvent) {
    }

    public void about(ActionEvent actionEvent) {
        contentpane.setCenter( generator.aboutPane() );
    }


    public void globalEvent(KeyEvent keyEvent) {
        //list.refresh();
        //System.out.println("REFRESH");
    }

    public void newSearch(KeyEvent keyEvent) {
        String search = searchBar.textProperty().get() + keyEvent.getText();
        library.searchForBook(search);

        list.setItems(library.getActiveShelf().shelf);
    }
}