package Client_Side.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class LogingController {
    public AnchorPane root;
    public JFXTextField txtusername;
    public JFXPasswordField txtpw;
    public static String userName;
    public static String pw;
    public static ArrayList<String> users = new ArrayList<>();

    public void CreateOnAction(ActionEvent actionEvent) {
    }

    public void LogingOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtusername.getText().trim();
        pw=txtpw.getText().trim();
        boolean flag = false;
        if(users.isEmpty()){
            users.add(userName);
            flag = true;
        }

        for(String s : users) {
            if (!s.equalsIgnoreCase(userName)) {
                flag = true;
                System.out.println(userName);
                break;
            }
        }
            if(flag){
                this.root.getChildren().clear();
                this.root.getChildren().add(FXMLLoader.load(this.getClass().
                        getResource("../View/Chat.fxml")));
            }

    }
}

