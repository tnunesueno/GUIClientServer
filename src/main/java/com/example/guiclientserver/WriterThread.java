package com.example.guiclientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WriterThread implements Runnable{
    Socket socket;
    PrintWriter out;
    ShoebQueue myData;
    Boolean isServer;

    public WriterThread(Socket socket, ShoebQueue myData, Boolean isServer) throws IOException {
        this.socket = socket;
        this.myData = myData;
        out = new PrintWriter(socket.getOutputStream(), true);
        this.isServer =isServer;
    }

    public void run() {
        while (!Thread.interrupted()) {
            Object message = myData.get();
            while (message == null) {
                message = myData.get();
            }

            if (isServer) {
                out.println("LOL it was funny when you wrote: " + message);
                System.out.println("SERVER WROTE: LOL it was funny when you wrote: " + message);
            } else {
                out.println(message);
                System.out.println("CLIENT WROTE: " + message);
            }
        }

        try {
            out.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println("ServerConnectorThread closing failed: " + ex);
        }
    }
}
