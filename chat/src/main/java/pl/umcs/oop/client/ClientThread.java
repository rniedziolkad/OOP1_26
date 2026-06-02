package pl.umcs.oop.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientThread extends Thread {
    private Socket socket; // gniazdo, na którym połączony jest klient
    private List<ClientThread> allClients; // każdy klient ma referencję do listy wszystkich klientów
    PrintWriter writer;
    public ClientThread(Socket socket, List<ClientThread> allClients) {
        this.socket = socket;
        this.allClients = allClients;
    }

    public void broadcast(String message) {
        for (ClientThread ct : allClients) {
            ct.send(message);
        }
    }

    public void send(String message) {
        System.out.println("wysyłam wiadomość: " + message);
        writer.println(message);
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream()); // do odczytywania wiadomosci od klienta
            writer = new PrintWriter(socket.getOutputStream(), true); // do wysyłania wiadomości do klienta
            String message;
            while (scanner.hasNextLine()) {
                // czytamy wiadomości w nieskończoność (dopóki jest połączenie)
                message = scanner.nextLine();
                System.out.println("Otrzymano wiadomość: " + message);
                broadcast(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Zakończono połączenie " + socket);
        }

    }
}
