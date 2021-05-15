package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
