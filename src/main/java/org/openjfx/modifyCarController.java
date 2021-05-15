package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;

public class modifyCarController {
    int modified = 0;
    ObservableList<String> choiceBoxListAlimentation = FXCollections.observableArrayList("Diesel","Gasoline","Electric");
    ObservableList<String> choiceBoxListGearbox = FXCollections.observableArrayList("Manual","Automatic");
    @FXML
    private  TextField txtID;
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtEnginePower;
    @FXML
    private TextField txtRegNr;
    @FXML
    private ChoiceBox<String> Alimentation;
    @FXML
    private ChoiceBox<String> Gearbox;
    @FXML
    private Label lblStatus;
    @FXML
    private javafx.scene.control.Button cancelButton;

    @FXML
    private void initialize (){
        Alimentation.setValue("Choose alimentation");
        Alimentation.setItems(choiceBoxListAlimentation);
        Gearbox.setValue("Choose gearbox");
        Gearbox.setItems(choiceBoxListGearbox);
    }

    public void modifyCar (javafx.event.ActionEvent actionEvent) throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object obj = parser.parse(reader);

            JSONArray cars = (JSONArray) obj;
            cars.forEach(car -> {
                try {
                    FileWriter file = new FileWriter("src/main/resources/DataBase/CarsData.json");
                    if(Objects.nonNull(car)) {
                        parseCarObject((JSONObject) car);
                    }
                    file.write(cars.toJSONString());
                    file.close();
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
        if(modified == 0)
        {
            lblStatus.setText("Incorrect ID!");
        }

    }

    public void parseCarObject(JSONObject car) throws Exception {
        //Get employee object within list
        JSONObject carObject = (JSONObject) car.get("car");
        if(Objects.nonNull(carObject)){
            String ID = (String) carObject.get("Id");

            if(ID.equals(txtID.getText())){

                String Brand = txtBrand.getText();
                String Model = txtModel.getText();
                String Year = txtYear.getText();
                String Description = txtDescription.getText();
                String EnginePower = txtEnginePower.getText();
                String RegNr = txtRegNr.getText();
                String AlimentationType = Alimentation.getValue();
                String GearBoxType = Gearbox.getValue();

                if(!Brand.isEmpty()){
                    carObject.put("Brand", Brand);
                    modified = 1;
                }
                if(!Model.isEmpty()){
                    carObject.put("Model", Model);
                    modified = 1;
                }
                if(!Year.isEmpty()){
                    carObject.put("Year", Year);
                    modified = 1;
                }
                if(!Description.isEmpty()){
                    carObject.put("Description", Description);
                    modified = 1;
                }
                if(!EnginePower.isEmpty()){
                    carObject.put("EnginePower", EnginePower);
                    modified = 1;
                }
                if(AlimentationType.equals("Diesel") || AlimentationType.equals("Gasoline") || AlimentationType.equals("Electric")){
                    carObject.put("Alimentation", AlimentationType);
                    modified = 1;
                }
                if(!RegNr.isEmpty()){
                    carObject.put("RegNr", RegNr);
                    modified = 1;
                }
                if(GearBoxType.equals("Manual") || GearBoxType.equals("Automatic")){
                    carObject.put("GearBox", GearBoxType);
                    modified = 1;
                }

                lblStatus.setText("Car modified! Please REFRESH TABLE!");
            }}
    }
    public void Cancel (javafx.event.ActionEvent actionEvent) throws Exception {

        Stage closeLogin = (Stage) cancelButton.getScene().getWindow();
        closeLogin.close();
    }

}
