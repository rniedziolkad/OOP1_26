package pl.umcs.oop.circleapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.umcs.oop.circleapp.client.ServerThread;

import java.io.IOException;

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

    private ServerThread serverThread;

    @FXML
    protected void onStartServerClicked() {
        // IGNORE
    }
    @FXML
    protected void onConnectClicked() {
        // 1. pobierz adres i port serwera
        String host = addressField.getText();
        int port = Integer.parseInt(portField.getText());
        try {
            // 2. połącz się z serwerem
            serverThread = new ServerThread(host, port);

            serverThread.setConsumer(dot -> Platform.runLater(() -> {
                canvas.getGraphicsContext2D().setFill(dot.c()); // ustawia kolor kolejnej akcji
                canvas.getGraphicsContext2D().fillOval(dot.x() - dot.r(), dot.y() - dot.r(),
                        dot.r()*2, dot.r()*2);
            }));
            serverThread.setDaemon(true); // zakończy wątek, gdy kontroler zostanie usunięty
            serverThread.start();
        } catch (IOException e) {
            System.out.println("Unable to connect: " + e.getMessage());
        }

    }
    @FXML
    protected void onMouseClicked(MouseEvent mouseEvent) {
        // 1. pobrać x oraz y kliknięcia myszy
        if (mouseEvent.getTarget() == canvas
                && mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED
                && mouseEvent.getButton() == MouseButton.PRIMARY) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            // 2. pobrać kolor z colorPicker
            Color color = colorPicker.getValue();
            // 3. pobrać promień z radiusSlider
            double radius = radiusSlider.getValue();
            // 4. prześlij dane koła do serwera
            if (serverThread == null) {
                System.out.println("Connect to server!");
            } else {
                Dot dot = new Dot(x, y, color, radius);
                serverThread.send(dot.toMessage());
            }
        }
    }

}
