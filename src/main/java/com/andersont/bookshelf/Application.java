package com.andersont.bookshelf;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application extends javafx.application.Application {
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {

        // initialize language bundles
        InputStream stream = new FileInputStream("resources/bundles/bundle.properties");
        Properties prop = System.getProperties();
        prop.load(stream);

        // create gui window
        fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        scene.getStylesheets().add(Application.class.getResource("styles.css").toExternalForm());

        InputStream stream2 = new FileInputStream("resources/assets/book.png");
        stage.getIcons().add(new Image(stream2));
        stage.setTitle(prop.getProperty("windowTitle"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // save data on exit
        Controller c = fxmlLoader.getController();
        IO.writeLibrary(c.saveLocation, c.library);
    }

    public static void main(String[] args) {
        launch();
    }
}