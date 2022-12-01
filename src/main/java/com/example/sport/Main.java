package com.example.sport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 540);
        File file = new File("src/main/resources/img/icon.png");
        String urlImage = file.toURI().toURL().toString();
        stage.getIcons().add(new Image(urlImage));
        stage.setResizable(false);
        stage.setTitle("SPORTSSHOP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}