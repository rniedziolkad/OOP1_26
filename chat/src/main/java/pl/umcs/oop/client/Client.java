package pl.umcs.oop.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // do łączenia się z serwerem służy klasa Socket
        try (Socket socket = new Socket("localhost", 12345)){
            System.out.println("Połączono z serwerem...");
            Scanner in = new Scanner(socket.getInputStream()); // do odczytywania wiadomości z serwera
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);   // do wysyłania wiadomości do serwera
            Scanner consoleIn = new Scanner(System.in);   // System.in to InputStream z konsoli
            String input = consoleIn.nextLine();
            while (!input.equals("exit")) {
                out.println(input); // wysyłamy wiadomość do serwera
                String response = in.nextLine(); // czekamy na odpowiedź
                System.out.println(response);
                input = consoleIn.nextLine(); // czytamy kolejne wejscie z konsoli
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
