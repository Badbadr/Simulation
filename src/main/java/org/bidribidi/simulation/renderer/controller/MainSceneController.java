package org.bidribidi.simulation.renderer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController {

    @FXML
    private Button mainButton;

    @FXML
    private void buttonClicked() {
        mainButton.setText("Button clicked!");
    }
}
