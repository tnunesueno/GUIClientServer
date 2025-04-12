package com.example.guiclientserver;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.Socket;

public class HelloController {
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button button;


    ShoebQueue myData = new ShoebQueue();

    public void connect() throws IOException {
        System.out.println("I'm the client");
        String hostname = "127.0.0.1";
        Socket socket = new Socket(hostname, 12349);


        ReaderThread read1 = new ReaderThread(socket, myData, false);
        WriterThread write1 = new WriterThread(socket, myData, false);
        Thread reader = new Thread(read1);
        Thread writer = new Thread(write1);
        // start both threads for each client and then continues
        reader.start();
        writer.start();

        myData.put("User Connected");
        recieve();
    }

    public static void recieve(){
        textArea.setText(myData.get().toString());
    }

    public void send(){
       String sentMessage = textField.getText();
       myData.put(sentMessage);
    }
}
