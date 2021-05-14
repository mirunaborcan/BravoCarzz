package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    int logged = 0;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private javafx.scene.control.Button loginButton;

    public void Login(javafx.event.ActionEvent actionEvent) throws Exception {

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/DataBase/UserData.json")) {
            Object obj = parser.parse(reader);

            JSONArray users = (JSONArray) obj;
            users.forEach(usr -> {
                try {
                    parseUserObject((JSONObject) usr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(logged == 0)
        {
            lblStatus.setText("Wrong User or Password!");
        }
    }

    public void parseUserObject(JSONObject user) throws Exception {
        //Get employee object within list
        JSONObject userObject = (JSONObject) user.get("user");
        String UserName = (String) userObject.get("UserName");
        String Pass = (String) userObject.get("Password");
        String role = (String) userObject.get("role");

        if (UserName.equals(txtUserName.getText()) && Pass.equals(txtPassword.getText())) {
            if (role.equals("Customer")) {
                lblStatus.setText("Login Customer Succes");
                logged = 1;
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/customerMenu.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Stage closeLogin = (Stage) loginButton.getScene().getWindow();
                closeLogin.close();

            }
            if (role.equals("Admin")) {
                lblStatus.setText("Login Admin Succes");
                logged = 1;
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/adminMenu.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Stage closeLogin = (Stage) loginButton.getScene().getWindow();
                closeLogin.close();

            }

        }

    }

    public void Register (javafx.event.ActionEvent actionEvent) throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
