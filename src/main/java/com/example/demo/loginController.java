package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class loginController {
    @FXML
    private Stage stage;
    @FXML
    private TextField useremailLogin;
    @FXML
    private TextField passwordLogin;


    @FXML
    public void loginStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void loginButton(ActionEvent event) throws Exception{
        
        if (authenticate()){
            Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(homeScene);
        }else{
            displayLoginError();
        }
        
    }
    
    private boolean authenticate(){
        String fileName = "src/main/java/Data/user.csv";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line, username, password;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (useremailLogin.getText().endsWith(".com")){
                    username = userData[1].trim();
                }else{
                    username = userData[0].trim();
                }
                password = userData[2].trim();
                
                if (username.equals(useremailLogin.getText().trim()) && password.equals(passwordLogin.getText().trim())) {
                    return true; 
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }
    
    private void displayLoginError(){
        Alert alertLogin = new Alert(AlertType.ERROR);
        alertLogin.setTitle("Login Failed");
        alertLogin.setHeaderText(null);
        alertLogin.setContentText("Invalid username or password. Please try again later!");
        
        alertLogin.showAndWait();
    }

    @FXML
    public void forgotpassword(MouseEvent event) throws Exception{
        
    }

    @FXML
    public void cAAButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene signUpScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(signUpScene);
    }

    @FXML
    public void closeButton(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void showError(String errorMessage){
        Alert alertError = new Alert(AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        
        alertError.showAndWait();
    }
}