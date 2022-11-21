package ru.croc.task11;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

class ClientConnection {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String addr;
    private int port;
    private String nickname;
    private ReentrantLock lock = new ReentrantLock();
    public ClientConnection(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
            System.out.println("connected");
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {

            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.enterNick();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            ClientConnection.this.downService();
        }
    }



    private void enterNick() {
        System.out.print("Your nick: ");
        try {
            lock.lock();
            nickname = inputUser.readLine();
            out.write("Hello " + nickname + "\n");
            out.flush();
            lock.unlock();
        } catch (IOException ignored) {
        }

    }


    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }
    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("exit")) {
                        ClientConnection.this.downService();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                ClientConnection.this.downService();
            }
        }
    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String msg;
                try {
                    msg = inputUser.readLine();
                    if (msg.equals("exit")) {
                        out.write("exit" + "\n");
                        ClientConnection.this.downService();
                        break;
                    } else {
                        out.write( nickname + ": " + msg + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientConnection.this.downService();

                }

            }
        }
    }
}
