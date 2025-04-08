package com.example.guiclientserver;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("I'm the server...");
        ServerConnectorThread connectorThread = new ServerConnectorThread(25782);
        Thread thread1 = new Thread(connectorThread);
        thread1.start();
        }
    }
