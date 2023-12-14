package org.bidribidi.simulation.renderer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import org.bidribidi.simulation.engine.Simulation;
import org.bidribidi.simulation.engine.SimulationImpl;

import java.io.IOException;

public class ControlBar extends HBox {

    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Button restartButton;
    @FXML
    private TextField moveCount;

    private final Simulation simulation;

    public ControlBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ControlBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        simulation = SimulationImpl.getInstance();

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void start() {
        toggleButton.setText(toggleButton.isSelected()? "Stop": "Resume");
    }

    public void restart() {
        toggleButton.setSelected(false);
        toggleButton.setText("Start");
        moveCount.setText("0");
    }

    public void backToOneRound() {

    }
    public void backToOneMove() {

    }

    public void forwardToOneMove() {

    }
    public void forwardToOneRound() {

    }

}
