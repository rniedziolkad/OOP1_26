package pl.umcs.oop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    public Server(int port) {
        this.port = port;
    }

    public void listen() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serwer nasłuchuje na porcie: " + port);
            while (true) {
                // oczekujemy na połączenia klientów
                System.out.println("Oczekuje na połączenie...");
                Socket connectedClient = serverSocket.accept(); // ten Socket służy do komunikacji z połączonym klientem
                System.out.println("Połączono: " + connectedClient);
                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
