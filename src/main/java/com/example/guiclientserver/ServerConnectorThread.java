package com.example.guiclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectorThread implements Runnable{
    ServerSocket serverSocket;

    public ServerConnectorThread(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        Socket client;
        PrintWriter out;
        BufferedReader in;
        int clientNum=1;
        String message;

        while (!Thread.interrupted()) {

            try {
                System.out.println("I'm still here " + clientNum++);
                client = serverSocket.accept(); // blocking api

                ShoebQueue myData = new ShoebQueue();
                ReaderThread read1 = new ReaderThread(client, myData, true, null);
                WriterThread write1 = new WriterThread(client, myData, true, null);
                Thread reader = new Thread(read1);
                Thread writer = new Thread(write1);
                // start both threads for each client and then continues
                reader.start();
                writer.start();
                // they must put and get from the same queue



            } catch (Exception ex){
                System.out.println("ServerConnectorThread failed: "+ ex);
                return;
            }
        }
    }
}
