package pl.umcs.oop.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private int port;
    public Server(int port) {
        this.port = port;
    }

    public void listen() {
        // ServerSocket służy do przyjmowania połączeń
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serwer nasłuchuje na porcie: " + port);
            while (true) {
                // oczekujemy na połączenia klientów
                System.out.println("Oczekuje na połączenie...");
                Socket connectedClient = serverSocket.accept(); // ten Socket służy do komunikacji z połączonym klientem
                System.out.println("Połączono: " + connectedClient);
                Scanner scanner = new Scanner(connectedClient.getInputStream()); // do odczytywania wiadomosci od klienta
                PrintWriter writer = new PrintWriter(connectedClient.getOutputStream(), true); // do wysyłania wiadomości do klienta
                String message;
                while (scanner.hasNextLine()) {
                    // czytamy wiadomości w nieskończoność (dopóki jest połączenie)
                    message = scanner.nextLine();
                    System.out.println("Otrzymano wiadomość: " + message);
                    writer.println("echo: " + message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
