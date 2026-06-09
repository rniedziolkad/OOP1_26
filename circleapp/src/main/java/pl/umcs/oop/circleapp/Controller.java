package pl.umcs.oop.circleapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField addressField;
    @FXML
    private TextField portField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider radiusSlider;
    @FXML
    private Canvas canvas;

    @FXML
    protected void onStartServerClicked() {
        // IGNORE
    }
    @FXML
    protected void onConnectClicked() {
        // TODO
    }
    @FXML
    protected void onMouseClicked() {
        // TODO
    }

}
