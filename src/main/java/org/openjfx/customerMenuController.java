package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class customerMenuController implements Initializable {

     @FXML
    private Pane pn_viewCars, pn_myList, pn_RentCar;

    @FXML
    private Button viewCarsButton, myListButton, rentCarButton, logOutButton;

    @FXML
    private TableView<GetAllCarsTable> table;

    @FXML
    private TableColumn<GetAllCarsTable, String> id;

    @FXML
    private TableColumn<GetAllCarsTable, String> brand;

    @FXML
    private TableColumn<GetAllCarsTable, String> model;

    @FXML
    private TableColumn<GetAllCarsTable, String> year;

    @FXML
    private TableColumn<GetAllCarsTable, String> regNr;

    @FXML
    private TableColumn<GetAllCarsTable, String> details;

    @FXML
    private TableColumn<GetAllCarsTable, String> engPwr;

    @FXML
    private TableColumn<GetAllCarsTable, String> aliment;

    @FXML
    private TableColumn<GetAllCarsTable, String> gearbox;

    @FXML
    private DatePicker pickUp;
    @FXML
    private DatePicker dropOff;
    @FXML
    private TextField txtID;
    @FXML
    private Label stsLabel;
    @FXML
    private TextField txtUser;
    @FXML
    private Button rentButton;
    int available = 1;
    int empty = 0;

    ObservableList<GetAllCarsTable> listCars = FXCollections.observableArrayList();

    public void addCarToTable(GetAllCarsTable car) {
        listCars.add(car);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JSONParser parserCar = new JSONParser();

        try (FileReader readerCar = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object objCar = parserCar.parse(readerCar);

            JSONArray cars = (JSONArray) objCar;
            cars.forEach(car -> {
                try {
                    if (Objects.nonNull(car)) {
                        parseCarObject((JSONObject) car);
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

        id.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("id"));
        brand.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("model"));
        year.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("year"));
        regNr.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("regNr"));
        aliment.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("aliment"));
        gearbox.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("gearbox"));
        details.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("details"));
        engPwr.setCellValueFactory(new PropertyValueFactory<GetAllCarsTable,String>("engPwr"));

        table.setItems(listCars);
    }
    public void parseCarObject(JSONObject car) throws Exception {
        //Get employee object within list
        JSONObject carObject = (JSONObject) car.get("car");
        if(Objects.nonNull(carObject)) {
            String ID = (String) carObject.get("Id");
            String Brand = (String) carObject.get("Brand");
            String Model = (String) carObject.get("Model");
            String Year = (String) carObject.get("Year");
            String Description = (String) carObject.get("Description");
            String EnginePower = (String) carObject.get("EnginePower");
            String RegNr = (String) carObject.get("RegNr");
            String AlimentationType = (String) carObject.get("Alimentation");
            String GearBoxType = (String) carObject.get("Gearbox");

            GetAllCarsTable row = new GetAllCarsTable(ID, Brand, Model, Year, RegNr, Description, EnginePower, AlimentationType, GearBoxType);
            addCarToTable(row);
        }
    }

        @FXML
    private void customerButton(ActionEvent event)
    {
        if(event.getSource()==viewCarsButton)
        {
            pn_viewCars.toFront();
        }
        else
        if(event.getSource()== myListButton)
        {
            pn_myList.toFront();
        }
        else if(event.getSource()==rentCarButton)
        {
            pn_RentCar.toFront();
        }

    }
    public void Check (javafx.event.ActionEvent actionEvent) throws Exception {
         available=1;
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/DataBase/RentedCars.json")) {
            Object obj = parser.parse(reader);

            JSONArray cars = (JSONArray) obj;
            cars.forEach(car -> {
                try {

                    parseRentedObject((JSONObject) car);

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
        if(available == 1 ) {

            stsLabel.setText("Available!");
        }

    }
    public void parseRentedObject(JSONObject car) throws Exception {

        JSONObject carObject = (JSONObject) car.get("car");
        String ID = (String) carObject.get("Id");
        LocalDate pickUpDate = pickUp.getValue();
        LocalDate dropOffDate = dropOff.getValue();
        if(txtID.getText().isEmpty()){

            stsLabel.setText("Incorrect ID!");
        }
        else {

            if (ID.equals(txtID.getText())) {

                String pickUp = (String) carObject.get("PickUp");
                String dropOff = (String) carObject.get("DropOff");
                LocalDate dt_pick = LocalDate.parse(pickUp);
                LocalDate dt_drop = LocalDate.parse(dropOff);
                if(Objects.nonNull(pickUpDate) && Objects.nonNull(dropOffDate)) {

                    if (pickUpDate.compareTo(dt_pick) >= 0 && pickUpDate.compareTo(dt_drop) <= 0) {

                        available = 0;
                        stsLabel.setText("Dates unavailable! Check other dates!");
                    }
                    else
                    if (dropOffDate.compareTo(dt_pick) >= 0 && pickUpDate.compareTo(dt_pick) <= 0) {
                            available = 0;
                            stsLabel.setText("Dates unavailable! Check other dates!");
                    }

                }
                else {
                    available = 0;
                    stsLabel.setText("Choose pick up and drop off dates!");
                }
            }

        }
    }


    public void RentCar (javafx.event.ActionEvent actionEvent) throws Exception{

        String id = txtID.getText();
        String user = txtUser.getText();
        String pick = String.valueOf(pickUp.getValue());
        String drop = String.valueOf(dropOff.getValue());

            JSONObject carDetails = new JSONObject();
            JSONArray jrr = new JSONArray();
            JSONParser jp = new JSONParser();
            try {
                FileReader file = new FileReader("src/main/resources/DataBase/RentedCars.json");
                jrr = (JSONArray) jp.parse(file);
            } catch (Exception ex) {
                stsLabel.setText("Error!");
            }

            carDetails.put("Id", id);
            carDetails.put("Renter", user);
            carDetails.put("PickUp", pick);
            carDetails.put("DropOff", drop);

            JSONObject carObject = new JSONObject();

            carObject.put("car", carDetails);

            jrr.add(carObject);
            try {
                FileWriter file = new FileWriter("src/main/resources/DataBase/RentedCars.json");
                file.write(jrr.toJSONString());
                file.close();

                stsLabel.setText("Rented");

            } catch (Exception ex) {
                stsLabel.setText("Error!");
            }

    }

    public void logOut (javafx.event.ActionEvent actionEvent) throws Exception {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage closeCustomerMenu = (Stage) logOutButton.getScene().getWindow();
        closeCustomerMenu.close();

    }


}
