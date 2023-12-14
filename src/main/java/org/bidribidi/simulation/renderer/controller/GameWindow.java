package org.bidribidi.simulation.renderer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GameWindow extends StackPane {

    @FXML
    private Button button;
    @FXML
    private GridPane gridPane;

    public GameWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GameWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
