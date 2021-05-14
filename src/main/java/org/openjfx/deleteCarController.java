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

    public void Delete(javafx.event.ActionEvent actionEvent) throws Exception {

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object obj = parser.parse(reader);

            JSONArray cars = (JSONArray) obj;
            cars.forEach(car -> {
                try {
                    parseCarObject((JSONObject) car);
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
            lblStatus.setText("Can NOT delete car!");
        }
    }

    public void parseCarObject(JSONObject car) throws Exception {
        //Get car object within list
        JSONObject carObject = (JSONObject) car.get("car");
        String CarID = (String) carObject.get("Id");

        if (CarID.equals(txtId.getText())) {


                lblStatus.setText("Deleted!");
                deleted = 1;
    } }
    public void Cancel (javafx.event.ActionEvent actionEvent) throws Exception {

        Stage closeLogin = (Stage) cancelButton.getScene().getWindow();
        closeLogin.close();
    }


}
