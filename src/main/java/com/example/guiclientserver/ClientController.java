package com.example.guiclientserver;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    @FXML
    public TextField textField;
    @FXML
    public TextArea textArea;
    @FXML
    public ScrollPane scrollPane;
    public  Button button;
    ShoebQueue myData = new ShoebQueue();
    String hostname = "127.0.0.1";
    Socket socket;
    PrintWriter out;

    public void initialize() throws IOException {
        // this shit dont work - where in the HELL can you declare the socket
        socket = new Socket(hostname, 12349);
        out = new PrintWriter(socket.getOutputStream());
    }

    public void connect() throws Exception {
        System.out.println("I'm the client");

        ReaderThread read1 = new ReaderThread(socket, myData, false, this);
        WriterThread write1 = new WriterThread(socket, myData, false, this);
        Thread reader = new Thread(read1);
        Thread writer = new Thread(write1);

        // start both threads for each client and then continue
        reader.start();
        writer.start();
    }

    public void updateViewWithNewMsg(String message) {
        textArea.setText(textArea.getText()+'\n'+message);
    }

    public void send() throws Exception{
       String sentMessage = textField.getText();
       out.println(sentMessage);
       textField.setText("");
    }


}
