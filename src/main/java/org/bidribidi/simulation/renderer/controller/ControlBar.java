package org.bidribidi.simulation.renderer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ControlBar extends HBox {

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private Button restartButton;

    public ControlBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ControlBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void start() {
        toggleButton.fire();
        if (toggleButton.isSelected()) {
            toggleButton.setText("Stop");
        }
    }

}
