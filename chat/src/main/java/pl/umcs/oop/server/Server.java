package pl.umcs.oop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    // CopyOnWriteArrayList -- implementacja listy bezbieczna dla wielowątkowości
    private final List<ClientThread> clients = new CopyOnWriteArrayList<>();
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
                ClientThread ct = new ClientThread(connectedClient, clients);
                clients.add(ct);
                ct.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server chatServer = new Server(12345);
        chatServer.listen();
    }
}
