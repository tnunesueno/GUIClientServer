package com.example.guiclientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WriterThread implements Runnable{
    Socket socket;// maybe get rid of this so that we can use the controller connector thread
    PrintWriter out;
    ShoebQueue myData;
    Boolean isServer;
    ClientController controller;

    public WriterThread(Socket socket, ShoebQueue myData, Boolean isServer, ClientController controller) throws IOException {
        this.socket = socket;
        this.myData = myData;
        out = new PrintWriter(socket.getOutputStream(), true);
        this.isServer = isServer;
        this.controller = controller;
    }

    public void run() {
        while (!Thread.interrupted()) {
            Object message = myData.get();
            while (message == null) {
                message = myData.get();
            }

            if (isServer) {
                out.println("LOL it was funny when you wrote: " + message);
                controller.updateViewWithNewMsg(message.toString());
            } else {
                System.out.println("CLIENT WROTE: " + message);
                // im not sure if this goes here - updateViewWithNewMsg does put it on the gui
                // no idea where the lol comes from
                controller.updateViewWithNewMsg(message.toString());
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
