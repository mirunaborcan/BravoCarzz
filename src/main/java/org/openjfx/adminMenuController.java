package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class adminMenuController {

    @FXML
    private Pane pn_allCars, pn_rentedCars, pn_viewCustomers;

    @FXML
    private Button logOutButton, viewRentedButton, viewCustomersButton, viewAllCarsButton;

    @FXML
    private void adminButton(ActionEvent event)
    {
        if(event.getSource()==viewRentedButton)
        {
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
    }

    public void modifyCar (javafx.event.ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/modifyCar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void DeleteCar (javafx.event.ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/deleteCar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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
}
