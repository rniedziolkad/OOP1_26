package pl.umcs.oop.circleapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        // 1. pobierz adres i port serwera
        String host = addressField.getText();
        int port = Integer.parseInt(portField.getText());
        // 2. połącz się z serwerem
        

    }
    @FXML
    protected void onMouseClicked(MouseEvent mouseEvent) {
        // 1. pobrać x oraz y kliknięcia myszy
        if (mouseEvent.getTarget() == canvas
                && mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED
                && mouseEvent.getButton() == MouseButton.PRIMARY) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            System.out.println("Kliknięto (" + x + ", " + y + ")");
            // 2. pobrać kolor z colorPicker
            Color color = colorPicker.getValue();
            // 3. pobrać promień z radiusSlider
            double radius = radiusSlider.getValue();
            // 4. wyrysować koło na canvas
            canvas.getGraphicsContext2D().setFill(color); // ustawia kolor kolejnej akcji
            canvas.getGraphicsContext2D().fillOval(x - radius, y - radius, radius*2, radius*2);
        }
    }

}
