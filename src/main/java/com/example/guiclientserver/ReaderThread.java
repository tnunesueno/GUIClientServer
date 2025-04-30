package com.example.guiclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReaderThread implements Runnable{
    Socket socket;
    ShoebQueue myData;
    BufferedReader in;
    Boolean isServer;
    ClientController controller;

    public ReaderThread(Socket socket, ShoebQueue myData, Boolean isServer, ClientController controller) throws IOException {
        this.socket = socket;
        this.myData = myData;
        this.isServer = isServer;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.controller = controller;
    }

    public void run(){
        while(!Thread.interrupted()) {
            // reading code
            String message = null;
            try {
                message = in.readLine();
                System.out.println("READ : " + message);
            } catch (IOException e) {
                System.out.println("Reader error: " + e);
            }
            boolean succsess = myData.put(message);
            while (!succsess) {
                succsess = myData.put(message);
                }
            }
        }
    }
