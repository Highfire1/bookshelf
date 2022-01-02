package com.andersont.bookshelf;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;


import static com.andersont.bookshelf.IO.loadImage;
import static javafx.beans.binding.Bindings.bindBidirectional;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundSize.DEFAULT;


// Generates GridPanes for Books / About page / Config pages
// Apparently you're supposed to use scenes / separate fxml files, but I wanted to try to create pages with pure Java
public class GUIGenerator extends Controller {


    // Returns a GridPane to view and modify a Book
    // Uses lots of bindings :)
    public GridPane generateBookPane(Book book) {
        GridPane pane = new GridPane();

        // LOAD BOOK THUMBNAIL (to background of pane)
        String thumbnailImageURL = book.getString("thumbnail");

        Image image = loadImage(thumbnailImageURL);
        BackgroundImage backgroundImage = new BackgroundImage(image, NO_REPEAT, NO_REPEAT, CENTER, DEFAULT);
        pane.setBackground(new Background(backgroundImage));

        // event listener on click for the background image
        // launches a file dialogue when triggered and accordingly swaps out the image
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pick a thumbnail");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
            File selectedFile = fileChooser.showOpenDialog(pane.getScene().getWindow());

            if (selectedFile != null) {
                book.setProperty("thumbnail", selectedFile.toURI().toString());

                // duplicated code is ugly, you say
                // a separate method is even uglier, I say
                Image image2 = loadImage(book.getString("thumbnail"));
                BackgroundImage backgroundImage2 = new BackgroundImage(image2, NO_REPEAT, NO_REPEAT, CENTER, DEFAULT);
                pane.setBackground(new Background(backgroundImage2));
            }
            event.consume(); // something something good programming practices
        });


        // LOAD TITLE
        TextField title = new TextField();

        Font titleFont = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 36);
        title.setFont(titleFont);
        title.setBackground(Background.EMPTY);

        bindBidirectional(title.textProperty(), book.getStringProperty("title"));

        pane.add(title, 1, 1, 4, 1);

        // LOAD RATING
        Label rating = new Label(properties.getProperty("rating"));
        Slider slider = new Slider(0, 10, 0);

        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);

        slider.setValue(Double.parseDouble(book.getString("rating"))); // because the binding doesn't set it???
        StringProperty bookReview = book.getStringProperty("rating");
        DoubleProperty sliderNum = slider.valueProperty();
        bookReview.bind(sliderNum.asString());

        pane.add(rating, 1, 2);
        pane.add(slider, 2, 2);

        // LOAD READ DATE
        Label readDate = new Label(properties.getProperty("readDate"));
        TextField date = new TextField();

        bindBidirectional(date.textProperty(), book.getStringProperty("readDate"));

        pane.add(readDate, 1, 3);
        pane.add(date, 2, 3);

        // LOAD REVIEW
        Label reviewLabel = new Label(properties.getProperty("review"));
        TextArea review = new TextArea();
        review.setWrapText(true);

        bindBidirectional(review.textProperty(), book.getStringProperty("review"));

        pane.add(reviewLabel, 1, 4);
        pane.add(review, 1, 5, 3, 1);


        // GRID PANE LAYOUT
        pane.setHgap(10); //horizontal gap in pixels
        pane.setVgap(10); //vertical gap in pixels

        //pane.setGridLinesVisible(true);

        // COLUMNS
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setMinWidth(10);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(75);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(225);
        column2.setMinWidth(225);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPrefWidth(10000);

        ColumnConstraints column4 = new ColumnConstraints();
        column4.setMinWidth(10);

        pane.getColumnConstraints().add(column0);
        pane.getColumnConstraints().add(column1);
        pane.getColumnConstraints().add(column2);
        pane.getColumnConstraints().add(column3);
        pane.getColumnConstraints().add(column4);

        // ROWS

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        row4.setMaxHeight(50);
        RowConstraints row5 = new RowConstraints();
        row5.setPrefHeight(1000);
        RowConstraints row6 = new RowConstraints();
        row6.setMinHeight(10);

        pane.getRowConstraints().add(row0);
        pane.getRowConstraints().add(row1);
        pane.getRowConstraints().add(row2);
        pane.getRowConstraints().add(row3);
        pane.getRowConstraints().add(row4);
        pane.getRowConstraints().add(row5);
        pane.getRowConstraints().add(row6);

        return pane;
    }

    // An about pane
    // Displays some helpful information
     public GridPane aboutPane() {
        GridPane pane = new GridPane();

        String text = String.join(
                System.getProperty("line.separator"),
                "Thank you for using bookshelf!",
                "Made by Anderson",
                "",
                "Tips and Tricks:",
                "",
                "Ctrl + N to create a new Book",
                "DEL to delete the selected Book",
                "Click on the background image to change it!",
                "Search by title, rating, date read or inside reviews!",
                "Your data is auto saved every 60 seconds,",
                "as well as when you exit the application."
                );

        TextArea textLabel = new TextArea(text);

        Font font = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        textLabel.setFont(font);
        textLabel.setEditable(false);

        pane.setHgap(10); //horizontal gap in pixels
        pane.setVgap(10); //vertical gap in pixels
        pane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        // (top/right/bottom/left)

        pane.add(textLabel, 1, 1);

        return pane;
     }

     // UNIMPLEMENTED
    public GridPane deleteShelfPane() {
        return new GridPane();
    }

    // UNIMPLEMENTED
    public GridPane createShelfPane() {
        return new GridPane();
    }

    // Convenience method
    public GridPane emptyPane() {
        return new GridPane();
    }

}
