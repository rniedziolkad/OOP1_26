package pl.umcs.oop.circleapp.server;

import javafx.scene.paint.Color;
import pl.umcs.oop.circleapp.Dot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<ClientThread> handlers = new CopyOnWriteArrayList<>();
    private Connection connection;

    public Server(int port) throws IOException, SQLException {
        serverSocket = new ServerSocket(port);
        connection = DriverManager.getConnection("jdbc:sqlite:circle.db");
        initDB();
    }
    private void initDB() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS dot(
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                x INTEGER NOT NULL,
                y INTEGER NOT NULL,
                color TEXT NOT NULL,
                radius INTEGER NOT NULL
        );
        """;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    private void saveDot(Dot dot) throws SQLException {
        String sql = "INSERT INTO dot(x, y, color, radius) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, (int) dot.x());
        statement.setInt(2, (int) dot.y());
        statement.setString(3, dot.c().toString());
        statement.setInt(4, (int) dot.r());
        statement.executeUpdate();
    }

    private List<Dot> getSavedDots() throws SQLException {
        List<Dot> dots = new ArrayList<>();
        String sql = "SELECT * FROM dot;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            String color = rs.getString("color");
            int r = rs.getInt("radius");
            dots.add(new Dot((double) x, (double) y, Color.valueOf(color), (double) r));
        }
        return dots;
    }

    public void disconnectHandlers() {
        handlers.forEach(handler -> handler.send("disconnected"));
        handlers.clear();
    }

    public void removeHandler(ClientThread handler) {
        handlers.remove(handler);
    }

    public void broadcast(String message) throws SQLException {
        Dot dot = Dot.fromMessage(message);
        saveDot(dot);

        System.out.println("Sending: " + message);
        for (ClientThread ct : handlers) {
            ct.send(message);
        }
    }

    public void listen() throws IOException, SQLException {
        System.out.println("Server started");
        while (true) {
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            ClientThread ct = new ClientThread(socket, this);
            Thread thread = new Thread(ct);
            thread.start();
            // wysłać wszystkie punkty z bazy do podłaczonego klienta
            List<Dot> dots = getSavedDots();
            for(int i = 0; i < dots.size(); i++){
                ct.send(dots.get(i).toMessage());
            }
            handlers.add(ct);
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server(5000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.disconnectHandlers();
        }));

        server.listen();
    }
}
