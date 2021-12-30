package com.andersont.bookshelf;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        // initialize language bundles
        InputStream stream = new FileInputStream("resources/bundles/bundle.properties");
        Properties prop = System.getProperties();
        prop.load(stream);

        // create gui window
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle(prop.getProperty("windowTitle"));
        //stage.getIcons().add(new Image("resources/assets/book.png"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static Library library = new Library();

    public static void main(String[] args) {
        launch();
    }
}