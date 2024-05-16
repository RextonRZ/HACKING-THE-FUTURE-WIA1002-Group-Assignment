package com.example.demo;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


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
        homeController homeController = new homeController();
        homeController.homeButton(event);
    }

    @FXML
    public void cAAButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene signUpScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(signUpScene);
    }
}