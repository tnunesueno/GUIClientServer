package com.example.guiclientserver;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("I'm the client");
        String hostname = "127.0.0.1";
        Socket socket = new Socket(hostname, 12349);

        ShoebQueue myData = new ShoebQueue();
        ReaderThread read1 = new ReaderThread(socket, myData, false);
        WriterThread write1 = new WriterThread(socket, myData, false, null);
        Thread reader = new Thread(read1);
        Thread writer = new Thread(write1);
        // start both threads for each client and then continues
        reader.start();
        writer.start();

        myData.put("Hi it's Twyla");
    }
}
