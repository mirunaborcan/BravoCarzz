package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class customerMenuController  {

     @FXML
    private Pane pn_viewCars, pn_myList, pn_RentCar;

    @FXML
    private Button viewCarsButton, myListButton, rentCarButton, logOutButton;

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


    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }
}
