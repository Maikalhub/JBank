package main.Utility;

import main.Models.Entities.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    private static final ClientSocket SINGLE_INSTANCE = new ClientSocket();
    private Client client;
    private static Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private ClientSocket() {
        try {
            socket = new Socket("localhost", 1111); // Порт сервера
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClientSocket getInstance() {
        return SINGLE_INSTANCE;
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getInStream() {
        return in;
    }

    public Client getUser() {
        return client;
    }

    //public void setUser(Client client) {
    //    this.client = client;
    //}

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setUser(Client loggedInClient)
    {
        this.client = loggedInClient;
    }
}
