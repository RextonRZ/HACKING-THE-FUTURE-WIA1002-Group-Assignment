package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class friendRequestController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private Text friend1;
    @FXML
    private Text friend10;
    @FXML
    private Text friend2;
    @FXML
    private Text friend3;
    @FXML
    private Text friend4;
    @FXML
    private Text friend5;
    @FXML
    private Text friend6;
    @FXML
    private Text friend7;
    @FXML
    private Text friend8;
    @FXML
    private Text friend9;

    Text[] friends = {
            friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8, friend9, friend10
    };

    String fileName = "src/main/java/Data/friend_requests.csv";

    @FXML
    public void friendRequestStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("friendRequest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
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

    public void printFriendsRequest() {
        String line;
        ArrayList<String> pendingRequest = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] friendRequest = line.split(",");
                int status = Integer.parseInt(friendRequest[2]);
                if ((friendRequest[1].equals(loginController.HostUsername)) && (status == 0)) {
                    pendingRequest.add(friendRequest[0]);
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        for (String request : pendingRequest) {
            System.out.println(request);
        }

        for (int i = 0; i < friends.length && i < pendingRequest.size(); i++) {
            if (friends[i] != null) {
                System.out.println("Friend " + (i + 1) + ": " + friends[i].getText());
                friends[i].setText(pendingRequest.get(i));
            } else {
                System.out.println("Friend " + (i + 1) + ": null");
            }
        }
    }

    @FXML
    void viewProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printFriendsRequest();
    }
}