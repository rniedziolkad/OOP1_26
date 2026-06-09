package pl.umcs.oop.circleapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// wątek odczytujący z serwera
public class ServerThread extends Thread {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ServerThread(String address, int port) throws IOException {
        socket = new Socket(address, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String message) {
        writer.println(message);
    }

    @Override
    public void run() {
        System.out.println("Connected to server");
        try {
            String message;
            while ((message = reader.readLine()) != null)
                System.out.println("Received: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
