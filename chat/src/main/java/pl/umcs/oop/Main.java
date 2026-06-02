package pl.umcs.oop;

import pl.umcs.oop.server.Server;

public class Main {
    public static void main(String[] args) {
        Server chatServer = new Server(12345);
        chatServer.listen();
    }
}