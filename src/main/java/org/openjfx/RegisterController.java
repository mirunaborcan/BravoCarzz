package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;

public class RegisterController  {
    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Customer","Admin");
    @FXML
    private javafx.scene.control.Button registerButton;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private void initialize (){
        choiceBox.setValue("Role");
        choiceBox.setItems(choiceBoxList);
    }
    public void Register(javafx.event.ActionEvent actionEvent) throws Exception {

        String FirstName = txtFirstName.getText();
        String LastName = txtLastName.getText();
        String PhoneNumber = txtPhoneNumber.getText();
        String Email = txtEmail.getText();
        String UserName = txtUserName.getText();
        String Password = txtPassword.getText();
        String role = choiceBox.getValue();

        if(FirstName.isEmpty() || LastName.isEmpty() || PhoneNumber.isEmpty() ||
        Email.isEmpty() || UserName.isEmpty() || Password.isEmpty() || (!role.equals("Admin") && !role.equals("Customer"))){
            JOptionPane.showMessageDialog(null, "All fields must be completed or you must choose a role!");
        }
        else{
        JSONObject userDetails = new JSONObject();
        JSONArray jrr = new JSONArray();
        JSONParser jp = new JSONParser();
        try {
            FileReader file = new FileReader("src/main/resources/DataBase/UserData.json");
            jrr = (JSONArray) jp.parse(file);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured");
        }
        userDetails.put("FirstName", FirstName);
        userDetails.put("LastName", LastName);
        userDetails.put("PhoneNumber", PhoneNumber);
        userDetails.put("Email", Email);
        userDetails.put("UserName", UserName);
        userDetails.put("Password", Password);
        userDetails.put("role",role);

        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);

        jrr.add(userObject);
        try {
            FileWriter file = new FileWriter("src/main/resources/DataBase/UserData.json");
            file.write(jrr.toJSONString());
            file.close();

            JOptionPane.showMessageDialog(null, "Registration complete! Please login!");
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured");
        }}
    }

}
