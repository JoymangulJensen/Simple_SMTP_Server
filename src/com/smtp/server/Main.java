package com.smtp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NB_CONNEXION_MAX = 10;

    private static final int SERVER_PORT = 1096;

    private static List<Connection> connexions = new ArrayList<>();

    public static void main(String[] args) {

        try {
            launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the server
     * @throws IOException in case of exception
     */
    private static void launch() throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server Running on port " + serverSocket.getLocalPort());

        boolean stop = false;
        while (connexions.size() < NB_CONNEXION_MAX && !stop) {
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket);
            connexions.add(connection);
            System.out.println("New Connexion n°" + connexions.size() + " " + socket);
            new Thread(connection).start();
        }

        if (stop) {
            serverSocket.close();
        }
    }
}
