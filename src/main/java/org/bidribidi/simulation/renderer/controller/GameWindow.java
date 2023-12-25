package org.bidribidi.simulation.renderer.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.bidribidi.simulation.engine.Simulation;
import org.bidribidi.simulation.engine.SimulationImpl;
import org.bidribidi.simulation.engine.entities.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWindow extends StackPane implements Initializable {

    @FXML
    private Button button;
    @FXML
    private GridPane gridPane = new GridPane();

    private final Simulation simulation = SimulationImpl.getInstance();

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

    private ImageView getImageView(Entity entity) {
        ImageView imageView = new ImageView();
        Image image;
        if (entity instanceof Predator) {
            image = new Image("File:/Users/bidribidi/IdeaProjects/Simulation/src/main/resources/images/entities/predator.png");
        } else if (entity instanceof Herbivore) {
            image = new Image("File:/Users/bidribidi/IdeaProjects/Simulation/src/main/resources/images/entities/herbivore.png");
        } else if (entity instanceof Grass) {
            image = new Image("File:/Users/bidribidi/IdeaProjects/Simulation/src/main/resources/images/entities/grass.png");
        } else if (entity instanceof Tree) {
            image = new Image("File:/Users/bidribidi/IdeaProjects/Simulation/src/main/resources/images/entities/tree.png");
        } else {
            image = new Image("File:/Users/bidribidi/IdeaProjects/Simulation/src/main/resources/images/entities/rock.png");
        }

        imageView.setImage(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.setGridLinesVisible(true);
        simulation.startSimulation();
        redrawMap();
    }

    private void redrawMap() {
        for (Entity entity: simulation.getMap().getEntities()) {
            if (entity != null) {
                addImage(entity, entity.getCoordinates().getX(),
                        entity.getCoordinates().getY());
            }
        }
    }

    private void addImage(Entity entity, int x, int y) {
        try {
            StackPane node = (StackPane) getNodeByRowColumnIndex(x, y);
            node.getChildren().add(getImageView(entity));
        } catch (Exception e) {
            StackPane stackPane = new StackPane(getImageView(entity));
            GridPane.setConstraints(stackPane, x, y);
            this.gridPane.getChildren().add(stackPane);
        }
    }

    private Node getNodeByRowColumnIndex(final int row, final int column) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}
