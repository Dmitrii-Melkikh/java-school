package ru.croc.task11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;



public class Server {

    public static final int PORT = 8080;
    public static LinkedList<ServerConnection> connectionsList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT, 10);
        try {
            while (true) {
                Socket socket = server.accept();

                try {
                    connectionsList.add(new ServerConnection(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}