package com.example.guiclientserver;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.Socket;

public class HelloController {
    @FXML
    public TextField textField;
    @FXML
    public TextArea textArea;
    @FXML
    public ScrollPane scrollPane;
    public  Button button;
    ShoebQueue myData = new ShoebQueue();



    public void connect() throws Exception {
        System.out.println("I'm the client");
        String hostname = "127.0.0.1";
        Socket socket = new Socket(hostname, 12349);

        ReaderThread read1 = new ReaderThread(socket, myData, false);
        WriterThread write1 = new WriterThread(socket, myData, false, this);
        Thread reader = new Thread(read1);
        Thread writer = new Thread(write1);

        // start both threads for each client and then continue
        reader.start();
        writer.start();

        myData.put("User Connected");
        //recieve();
    }

    public void recieve(String message) {
        textArea.setText(message);
    }

    public void send() throws Exception{
       String sentMessage = textField.getText();
       myData.put(sentMessage);
    }


}
