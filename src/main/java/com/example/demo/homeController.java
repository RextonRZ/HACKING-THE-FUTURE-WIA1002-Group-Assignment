package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class homeController {

    private Stage stage;

    @FXML
    public void homeStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void quizButton(ActionEvent event) throws Exception{
        createQuizController createQuizController = new createQuizController();
        createQuizController.createQuizStartUp(event);
    }

    public void eventButton(ActionEvent event) throws Exception{
        createEventController createEventController = new createEventController();
        createEventController.createEventStartUp(event);
    }

    public void bookingButton(ActionEvent event) throws Exception{
        bookingController bookingController = new bookingController();
        bookingController.bookingStartUp(event);
    }

    public void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception{
        personalProfileController personalProfileController = new personalProfileController();
        personalProfileController.personalProfileStartUp(event);
    }

    public void logOutButton(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure want to log out?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isEmpty()){
            System.out.println("Alert closed");

        } else if(result.get() == ButtonType.OK) {
            loginController loginController = new loginController();
            loginController.loginStartUp(event);

        } else if (result.get() == ButtonType.CANCEL);

    }


}
