package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class chooseEventController {
    @FXML
    private Stage stage;

    @FXML
    public void chooseEventStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("chooseEvent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
    }

    public void createButton(ActionEvent event) throws Exception {
        createEventController createEventController = new createEventController();
        createEventController.createEventStartUp(event);
    }

    public void viewButton(ActionEvent event) throws Exception {
        viewEventController viewEventController = new viewEventController();
        viewEventController.viewEventStartUp(event);
    }



    @FXML
    public void homeButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.homeStartUp(event);
    }

    public void quizButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.quizButton(event);
    }

    public void eventButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.eventButton(event);
    }

    public void bookingButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.bookingButton(event);
    }

    public void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.profileButton(event);
    }

    public void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);

    }

}