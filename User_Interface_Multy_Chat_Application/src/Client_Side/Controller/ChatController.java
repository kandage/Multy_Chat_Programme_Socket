package Client_Side.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatController extends Thread{
    public JFXTextArea txtmessage;
    public TextField txtmsg;
    public Label txtprofile;
    public BufferedReader reader;
    public PrintWriter writer;
    public Socket socket;

    @Override
    public void run() {
        try{
            while (true){
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                StringBuilder fullMessage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMessage.append(tokens[i]);
                }

                System.out.println(fullMessage);

                if(cmd.equalsIgnoreCase(LogingController.userName + ": ")){
                    continue;
                }else if (fullMessage.toString().equalsIgnoreCase("bye")){
                    break;
                }

                txtmessage.appendText(msg + "\n");
            }

            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        System.out.println(LogingController.userName);
        txtprofile.setText(LogingController.userName);
        try{
            socket = new Socket("localhost", 5000);
            System.out.println("Connected.....");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void msgOnAction(ActionEvent actionEvent) {
        String msg = txtmsg.getText().trim();
        writer.println(LogingController.userName + ": "+ msg);
        txtmessage.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtmsg.setText("");
        if(msg.equalsIgnoreCase("Bye") || (msg.equalsIgnoreCase("logout"))){
            System.exit(0);
        }
    }
}
