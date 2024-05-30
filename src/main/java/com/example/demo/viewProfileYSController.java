package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class viewProfileYSController implements Initializable {
    @FXML
    private Label coords;
    @FXML
    private Label email;
    @FXML
    private Label point;
    @FXML
    private Label role;
    @FXML
    private Label username;
    @FXML
    private Label usernameTitle;

    String fileName = "src/main/java/Data/friend_requests.csv";

    public void profileStartUp(ActionEvent event) throws Exception {
        Parent root3 = FXMLLoader.load(getClass().getResource("ProfileYS.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Profile");
        Scene homeScene = new Scene(root3);
        stage.setScene(homeScene);
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
    }

    public void reset(){
        usernameTitle.setText(personalProfileYSController.Username);
        username.setText(personalProfileYSController.Username);
        email.setText(personalProfileYSController.Email);
        coords.setText(personalProfileYSController.Coordinate);
        point.setText(personalProfileYSController.Points);
        role.setText(personalProfileYSController.Role);
    }

    @FXML
    void addFriend(ActionEvent event) throws Exception{

    }

    private void storeRequest(){

    }
}
