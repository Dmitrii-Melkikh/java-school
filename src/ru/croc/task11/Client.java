package ru.croc.task11;


public class Client {

    public static String adr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientConnection(adr, port);
        new ClientConnection(adr, port);
    }
}