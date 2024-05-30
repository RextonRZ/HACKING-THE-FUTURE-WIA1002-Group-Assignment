package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class friendRequestController {
    @FXML
    private Stage stage;

    @FXML
    private Label friend1;
    @FXML
    private Label friend10;
    @FXML
    private Label friend2;
    @FXML
    private Label friend3;
    @FXML
    private Label friend4;
    @FXML
    private Label friend5;
    @FXML
    private Label friend6;
    @FXML
    private Label friend7;
    @FXML
    private Label friend8;
    @FXML
    private Label friend9;

    Label[] friends = {
            friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8, friend9, friend10
    };

    String fileName = "src/main/java/Data/friend_requests.csv";

    @FXML
    public void friendRequestStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("friendRequest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        printFriendsRequest();
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
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
        personalProfileYSController personalProfileYSController = new personalProfileYSController();
        personalProfileYSController.personalProfileStartUp(event);
    }

    public void friendButton(ActionEvent event) throws Exception {
        FriendListViewController friendListController = new FriendListViewController();
        friendListController.friendListStartUp(event);
    }

    public void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);
    }

    public void printFriendsRequest(){
        String line;
        ArrayList<String> pendingRequest = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] friendRequest = line.split(",");
                int status = Integer.parseInt(friendRequest[2]);
                if ((friendRequest[1].equals(loginController.HostUsername)) && (status == 0)){
                    pendingRequest.add(friendRequest[0]);
                }
            }
            for (int i = 0; i < pendingRequest.size(); i++){
                System.out.println(pendingRequest.get(i));
            }
        }catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
    }

}