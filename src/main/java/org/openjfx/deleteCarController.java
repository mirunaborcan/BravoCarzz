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

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class deleteCarController {

    @FXML
    private TextField txtId;
    @FXML
    private javafx.scene.control.Button cancelButton;
    @FXML
    private Label lblStatus;
    int deleted =0;


    public void Delete (javafx.event.ActionEvent actionEvent) throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object obj = parser.parse(reader);

            JSONArray users = (JSONArray) obj;
            users.forEach(usr -> {
                try {
                    FileWriter file = new FileWriter("src/main/resources/DataBase/CarsData.json");
                    if(!usr.equals(null)) {
                        parseUserObject((JSONObject) usr);
                        file.write(users.toJSONString());
                        file.close();
                    }
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
        if(deleted == 0)
        {
            lblStatus.setText("Incorrect ID!");
        }

    }

    public void parseUserObject(JSONObject user) throws Exception {
        //Get employee object within list
        JSONObject userObject = (JSONObject) user.get("car");
        if(!userObject.equals(null)){
        String ID = (String) userObject.get("Id");
        System.out.println(ID);
        if(ID.equals(txtId.getText())){
             System.out.println("DA");
             user.remove("car");
             deleted = 1;
             lblStatus.setText("Car deleted!");
        }}
    }
    public void Cancel (javafx.event.ActionEvent actionEvent) throws Exception {

        Stage closeLogin = (Stage) cancelButton.getScene().getWindow();
        closeLogin.close();
    }


}
