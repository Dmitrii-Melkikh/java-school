package ru.croc.task11;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

class ServerConnection extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private ReentrantLock lock = new ReentrantLock();
    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        String msg;
        try {
            msg = in.readLine();
            out.write(msg + "\n");
            out.flush();
            while (true) {
                msg = in.readLine();
                if(msg.equals("exit")) {
                    this.downService();
                    break;                }
                for (ServerConnection c : Server.connectionsList) {
                    c.send(msg);
                }
            }

        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {

            out.write(msg + "\n");
            out.flush();

        } catch (IOException ignored) {this.downService();}
    }

    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerConnection c : Server.connectionsList) {
                    if(c.equals(this)) c.interrupt();
                    Server.connectionsList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}