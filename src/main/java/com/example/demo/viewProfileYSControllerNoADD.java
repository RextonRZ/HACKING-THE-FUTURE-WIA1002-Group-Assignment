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

public class viewProfileYSControllerNoADD implements Initializable{
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

    public void profileStartUp(ActionEvent event) throws Exception {
        Parent root3 = FXMLLoader.load(getClass().getResource("ProfileYSNoAddFriend.fxml"));
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
        usernameTitle.setText(friendRequestController.UsernamePU);
        username.setText(friendRequestController.UsernamePU);
        email.setText(friendRequestController.EmailPU);
        coords.setText(friendRequestController.CoordinatePU);
        point.setText(friendRequestController.PointsPU);
        role.setText(friendRequestController.RolePU);
    }
}
