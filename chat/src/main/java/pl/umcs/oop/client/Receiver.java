package pl.umcs.oop.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// wątek do odczytywania wiadomości od serwera
public class Receiver extends Thread {
    private Socket socket;
    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            String message;
            while (in.hasNextLine()) {
                message = in.nextLine();
                System.out.println("otrzymano: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Serwer się rozłączył");
        }

    }
}
