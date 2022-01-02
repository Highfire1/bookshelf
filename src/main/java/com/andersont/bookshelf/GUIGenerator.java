package com.andersont.bookshelf;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.beans.binding.Bindings.bindBidirectional;


// Generates GridPanes for Books / About page / Config pages
// Apparently you're supposed to use scenes / separate fxml files, but I wanted to try to create pages with pure Java
public class GUIGenerator extends Controller {


    public GridPane generateBookPane(Book book) {
        GridPane pane = new GridPane();

        String absolutePath = saveLocation + "\\thumbnails\\" + book.getString("bookID") + ".jpg";

        // LOAD BOOK THUMBNAIL
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(saveLocation + "\\assets\\placeholder.png");
        } catch (FileNotFoundException e) {}
        
        try {
            inputStream = new FileInputStream(absolutePath);
        } catch (FileNotFoundException e) {}

        // thumbnail size
        Image image = new Image(inputStream);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(320);
        imageView.setFitWidth(180);

        pane.add(imageView, 0, 0, 1, 5); // x, y, # of rows, # of columns


        // title
        TextField title = new TextField();

        Font titleFont = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 36);
        title.setFont(titleFont);

        bindBidirectional(title.textProperty(), book.getStringProperty("title"));

        pane.add(title, 1, 1, 2, 1);

        //rating
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

        // readDate
        Label readDate = new Label(properties.getProperty("readDate"));
        TextField date = new TextField();

        bindBidirectional(date.textProperty(), book.getStringProperty("readDate"));

        pane.add(readDate, 1, 3);
        pane.add(date, 2, 3);

        // review
        Label reviewLabel = new Label(properties.getProperty("review"));
        TextArea review = new TextArea();
        review.setWrapText(true);

        bindBidirectional(review.textProperty(), book.getStringProperty("review"));


        pane.add(reviewLabel, 1, 4);
        pane.add(review, 1, 5, 2, 1);

        // tags

        //pane.add(new Label("tags:  " + book.getString("tags")), 3, 5);
        //pane.add(new Label("id:  " + book.getString("bookID")), 3, 6);

        // LAYOUT
        pane.setHgap(10); //horizontal gap in pixels
        pane.setVgap(10); //vertical gap in pixels

        //pane.setGridLinesVisible(true);

        // COLUMNS
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setMinWidth(0);
        column0.setPercentWidth(30);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(75);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMinWidth(75);
        column2.setPrefWidth(1000);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setMinWidth(10);

        pane.getColumnConstraints().add(column0);
        pane.getColumnConstraints().add(column1);
        pane.getColumnConstraints().add(column2);
        pane.getColumnConstraints().add(column3);

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

     public GridPane aboutPane() {

        GridPane pane = new GridPane();

        Label text = new Label("Thank you for using bookshelf!\n\nMade by Anderson.");
        text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));

         // ty stackoverflow
        pane.setHgap(10); //horizontal gap in pixels
        pane.setVgap(10); //vertical gap in pixels
        pane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        // (top/right/bottom/left)

        pane.add(text, 1, 1);

        return pane;
     }

    public GridPane welcomePane() {

        GridPane pane = new GridPane();

        Label text = new Label("Welcome to bookshelf!\n\nTo begin, select Shelf -> Create Book");
        text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // ty stackoverflow
        pane.setHgap(10); //horizontal gap in pixels
        pane.setVgap(10); //vertical gap in pixels
        pane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        // (top/right/bottom/left)

        pane.add(text, 1, 1);

        return pane;
    }

    public GridPane deleteShelfPane() {
        return new GridPane();
    }

    public GridPane createShelfPane() {
        return new GridPane();
    }

    public GridPane emptyPane() {
        return new GridPane();
    }

}
