package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class forgotPasswordController {

    @FXML
    private Stage stage;

    @FXML
    public void forgotPasswordStartUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Forgot Password");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void confirmButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("updatePassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

}
