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
import java.util.Random;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;

public class addCarController {
    ObservableList<String> choiceBoxListAlimentation = FXCollections.observableArrayList("Diesel","Gasoline","Electric");
    ObservableList<String> choiceBoxListGearbox = FXCollections.observableArrayList("Manual","Automatic");

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

    public void AddCar(javafx.event.ActionEvent actionEvent) throws Exception {

        String Brand = txtBrand.getText();
        String Model = txtModel.getText();
        String Year = txtYear.getText();
        String Description = txtDescription.getText();
        String EnginePower = txtEnginePower.getText();
        String RegNr = txtRegNr.getText();
        String AlimentationType = Alimentation.getValue();
        String GearBoxType = Gearbox.getValue();


        if(Brand.isEmpty() || Model.isEmpty() || Year.isEmpty() || RegNr.isEmpty()|| Description.isEmpty() || EnginePower.isEmpty() ||
                (!AlimentationType.equals("Diesel") && !AlimentationType.equals("Gasoline") && !AlimentationType.equals("Electric")) ||
                (!GearBoxType.equals("Manual") && !GearBoxType.equals("Automatic"))){
            lblStatus.setText("All fields must be completed!");
        }
        else{
            JSONObject carDetails = new JSONObject();
            JSONArray jrr = new JSONArray();
            JSONParser jp = new JSONParser();
            try {
                FileReader file = new FileReader("src/main/resources/DataBase/CarsData.json");
                jrr = (JSONArray) jp.parse(file);
            } catch (Exception ex) {
                lblStatus.setText("Error!");
            }
            Random rand = new Random();
            int ID = rand.nextInt(1000);
            String strID=String.valueOf(ID);

            carDetails.put("Id", strID);
            carDetails.put("Brand", Brand);
            carDetails.put("Model", Model);
            carDetails.put("Year", Year);
            carDetails.put("RegNr", RegNr);
            carDetails.put("Description", Description);
            carDetails.put("EnginePower", EnginePower);
            carDetails.put("Alimentation", AlimentationType);
            carDetails.put("Gearbox", GearBoxType);



            JSONObject carObject = new JSONObject();

            carObject.put("car", carDetails);

            jrr.add(carObject);
            try {
                FileWriter file = new FileWriter("src/main/resources/DataBase/CarsData.json");
                file.write(jrr.toJSONString());
                file.close();

                lblStatus.setText("Car Added! Please REFRESH TABLE!");

            } catch (Exception ex) {
                lblStatus.setText("Error!");
            }}
    }

    public void Cancel (javafx.event.ActionEvent actionEvent) throws Exception {

        Stage closeLogin = (Stage) cancelButton.getScene().getWindow();
        closeLogin.close();
    }

}
