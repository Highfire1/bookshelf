package com.andersont.bookshelf;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.util.Properties;

public class Controller {
    // The brains of the operation.
    // Interfaces with essentially everything.

    public Library library;
    public TextField searchBar;
    public Menu shelfMenu;
    private GUIGenerator generator;

    @FXML
    public ListView list;
    public Menu autofill;
    public CheckMenuItem darkTheme;
    public BorderPane contentpane;

    public String saveLocation = System.getProperty("user.dir") + "\\bookshelf\\";
    public Properties properties = IO.loadBundleFile();


    public void initialize(){
        // Generate Library and GUIGenerator
        System.out.println("Loading files from " + saveLocation);
        library = IO.loadLibrary(saveLocation);
        generator = new GUIGenerator();

        // ObservableList is a godsend
        list.setItems(library.getActiveShelf().shelf);

        // load welcome page or last book
        if (library.getLibrary().size() == 1) {
            contentpane.setCenter(generator.aboutPane());
        } else {
            viewBook(library.getActiveShelf().shelf.get(0));
        }


        // setup auto save
        // note that you can't use a regular Timer as that creates its own thread, which doesn't close with JavaFX
        // returns an integer because I couldn't figure out how to make it void
        ScheduledService<Integer> svc = new ScheduledService<>() {
            protected Task<Integer> createTask() {
                return new Task<>() {
                    protected Integer call() {
                        IO.writeLibrary(saveLocation, library);
                        return 1;
                    }
                };
            }
        };
        svc.setPeriod(Duration.seconds(60));
        svc.start();

    }

    public void deleteBook(ActionEvent actionEvent) {
        library.removeBook((Book) list.getSelectionModel().getSelectedItem());
        contentpane.setCenter(generator.emptyPane());

        if (!list.getSelectionModel().isEmpty()) {
            viewBook((Book) list.getSelectionModel().getSelectedItem());
        }
    }

    public void createBook(ActionEvent actionEvent) {

        Book book = new Book();
        book.setProperty("title", "BOOK_TITLE");
        library.addBook(book);
        viewBook(book);
        list.getSelectionModel().select(book);
    }

    public void listClicked(MouseEvent mouseEvent) {
        if (list.getSelectionModel().getSelectedItem() != null) {
            viewBook( (Book) list.getSelectionModel().getSelectedItem() );
        }
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

    public void about(ActionEvent actionEvent) {
        contentpane.setCenter( generator.aboutPane() );
    }


    public void globalEvent(KeyEvent keyEvent) {

        if(keyEvent.getText().equals("n") && keyEvent.isControlDown()) {
            createBook(new ActionEvent());
        }

        if(keyEvent.getCode().getName().equalsIgnoreCase("delete")) {
            deleteBook(new ActionEvent());
        }
    }

    public void newSearch(KeyEvent keyEvent) {
        String search = searchBar.textProperty().get() + keyEvent.getText();
        library.searchForBook(search);

        list.setItems(library.getActiveShelf().shelf);
    }


    // UNIMPLEMENTED
    public void toggleDarkTheme(ActionEvent actionEvent) {

    }

    // UNIMPLEMENTED
    public void importData(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(contentpane.getScene().getWindow());

        // select zip to import
        // unpack zip
        // load zip
    }

    // UNIMPLEMENTED
    public void exportData(ActionEvent actionEvent) {
        IO.writeLibrary(saveLocation, library);

        // compress to zip
        // then open fileChooser with the zip
    }

    // UNIMPLEMENTED
    public void toggleAutofill(ActionEvent actionEvent) {
    }
}