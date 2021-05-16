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


public class adminMenuController implements Initializable {

    @FXML
    private Button viewRentedButton;

    @FXML
    private Button viewCustomersButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button viewAllCarsButton;

    @FXML
    private Pane pn_viewCustomers;

    @FXML
    private Pane pn_rentedCars;

    @FXML
    private Pane pn_allCars;

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
    private TableView<GetAllUserTable> userTable;

    @FXML
    private TableColumn<GetAllUserTable, String> FirstName;

    @FXML
    private TableColumn<GetAllUserTable, String> LastName;

    @FXML
    private TableColumn<GetAllUserTable, String> PhoneNumber;

    @FXML
    private TableColumn<GetAllUserTable, String> Email;

    @FXML
    private TableColumn<GetAllUserTable, String> UserName;

    @FXML
    private TableView<GetAllRented> tableRented;

    @FXML
    private TableColumn<GetAllRented, String> idRented;

    @FXML
    private TableColumn<GetAllRented, String> renter;

    @FXML
    private TableColumn<GetAllRented, String> pickDate;

    @FXML
    private TableColumn<GetAllRented, String> dropDate;



    ObservableList<GetAllCarsTable> listCars = FXCollections.observableArrayList();
    ObservableList<GetAllUserTable> listUser = FXCollections.observableArrayList();
    ObservableList<GetAllRented> listRented = FXCollections.observableArrayList();

    public void addCarToTable(GetAllCarsTable car) {
        listCars.add(car);
    }
    public void addUserToTable(GetAllUserTable user) {
        listUser.add(user);
    }
    public void addRentedToTable(GetAllRented rent) {
        listRented.add(rent);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JSONParser parserCar = new JSONParser();

        try (FileReader readerCar = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object objCar = parserCar.parse(readerCar);

            JSONArray cars = (JSONArray) objCar;
            cars.forEach(car -> {
                try {
                    if(Objects.nonNull(car)) {
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

        JSONParser parserUser = new JSONParser();
        try (FileReader readerUser = new FileReader("src/main/resources/DataBase/UserData.json")) {
            Object objUser = parserUser.parse(readerUser);

            JSONArray users = (JSONArray) objUser;
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
        JSONParser parserRent = new JSONParser();
        try (FileReader readerRent = new FileReader("src/main/resources/DataBase/RentedCars.json")) {
            Object objRent = parserRent.parse(readerRent);

            JSONArray renters = (JSONArray) objRent;
            renters.forEach(rnt -> {
                try {
                    parseRentObject((JSONObject) rnt);
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

        FirstName.setCellValueFactory(new PropertyValueFactory<GetAllUserTable,String>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<GetAllUserTable,String>("LastName"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<GetAllUserTable,String>("PhoneNumber"));
        Email.setCellValueFactory(new PropertyValueFactory<GetAllUserTable,String>("Email"));
        UserName.setCellValueFactory(new PropertyValueFactory<GetAllUserTable,String>("UserName"));

        userTable.setItems(listUser);

        idRented.setCellValueFactory(new PropertyValueFactory<GetAllRented,String>("idRented"));
        renter.setCellValueFactory(new PropertyValueFactory<GetAllRented, String>("renter"));
        pickDate.setCellValueFactory(new PropertyValueFactory<GetAllRented, String>("pickDate"));
        dropDate.setCellValueFactory(new PropertyValueFactory<GetAllRented, String>("dropDate"));


        tableRented.setItems(listRented);

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
    public void parseUserObject(JSONObject user) throws Exception {

        JSONObject userObject = (JSONObject) user.get("user");
        String Role = (String) userObject.get("role");
        if(Objects.nonNull(userObject) && Role.equals("Customer")) {
            String FirstName = (String) userObject.get("FirstName");
            String LastName = (String) userObject.get("LastName");
            String PhoneNumber = (String) userObject.get("PhoneNumber");
            String Email = (String) userObject.get("Email");
            String UserName = (String) userObject.get("UserName");


            GetAllUserTable row = new GetAllUserTable(FirstName, LastName, PhoneNumber, Email, UserName);
            addUserToTable(row);
        }
    }
    public void parseRentObject(JSONObject user) throws Exception {

        JSONObject userObject = (JSONObject) user.get("car");

        if(Objects.nonNull(userObject)) {
            String id = (String) userObject.get("Id");
            String renter = (String) userObject.get("Renter");
            String dt_pick = (String) userObject.get("PickUp");
            String dt_drop = (String) userObject.get("DropOff");



            GetAllRented row = new GetAllRented(id, renter, dt_pick, dt_drop);
            addRentedToTable(row);
        }
    }

    @FXML
    public void adminButton(ActionEvent event)
    {
        if(event.getSource()==viewRentedButton)
        {   table.refresh();
            pn_rentedCars.toFront();
        }
        else
        if(event.getSource()== viewCustomersButton)
        {
            pn_viewCustomers.toFront();
        }
        else if(event.getSource()==viewAllCarsButton)
        {
            pn_allCars.toFront();
        }

    }

    public void AddCar (javafx.event.ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/addCar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        table.refresh();
    }

    public void modifyCar (javafx.event.ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/modifyCar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        table.refresh();
    }

    public void DeleteCar (javafx.event.ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/deleteCar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        table.refresh();
    }

    public void logOut(javafx.event.ActionEvent actionEvent) throws Exception {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage closeCustomerMenu = (Stage) logOutButton.getScene().getWindow();
        closeCustomerMenu.close();

    }
    public void refreshTable(javafx.event.ActionEvent actionEvent) throws Exception{

        listCars.clear();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/DataBase/CarsData.json")) {
            Object obj = parser.parse(reader);

            JSONArray cars = (JSONArray) obj;
            cars.forEach(car -> {
                try {
                    if(Objects.nonNull(car)) {
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
}
