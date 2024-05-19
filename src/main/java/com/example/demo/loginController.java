package com.example.demo;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
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
        
        try(Connection SQL = DriverManager.getConnection("**jdbc:mysql://**","**Username**", "**Password**")){
            String query = "SELECT * FROM users WHERE username=? AND passwords=?";
            try(PreparedStatement statement = SQL.prepareStatement(query)){
                statement.setString(1, useremailLogin.getText());
                statement.setString(2, passwordLogin.getText());
                try (ResultSet result = statement.executeQuery()){
                    return result.next();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    private void displayLoginError(){
        Alert alertLogin = new Alert(AlertType.ERROR);
        alertLogin.setTitle("Login Failed");
        alertLogin.setHeaderText(null);
        alertLogin.setContentText("Invalid username or password. Please try again later!");
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
}