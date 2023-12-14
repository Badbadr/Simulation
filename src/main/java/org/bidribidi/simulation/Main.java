package org.bidribidi.simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Hello world Application");
        InputStream iconStream = getClass().getResourceAsStream("/images/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/test_style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}