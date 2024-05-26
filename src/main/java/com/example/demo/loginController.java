package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class loginController {
    @FXML
    private Stage stage;
    @FXML
    TextField useremailLogin;
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
    public void loginButton(ActionEvent event) throws Exception {

        if (authenticate()) {
            Parent root3 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene homeScene = new Scene(root3, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(homeScene);

        } else {
            displayLoginError();
        }
    }

    public static String line, username, password, usernameID;
    private boolean authenticate(){
        String fileName = "src/main/java/Data/user.csv";
        usernameID = useremailLogin.getText();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (usernameID.endsWith(".com")){
                    username = userData[1].trim();
                }else{
                    username = userData[0].trim();
                }
                password = userData[2].trim();

                String hashedInputPW = signUpController.hashPassword(passwordLogin.getText().trim());

                if (username.equals(usernameID.trim()) && password.equals(hashedInputPW)) {
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            showError("Error hashing your password: " + e.getMessage());
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
    public void forgotpassword(ActionEvent event) throws Exception{
        forgotPasswordController forgotPasswordController = new forgotPasswordController();
        forgotPasswordController.forgotPasswordStartUp(event);


    }

    @FXML
    public void cAAButton(ActionEvent event) throws Exception{
        signUpController signUpController = new signUpController();
        signUpController.signUP(event);
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

    
