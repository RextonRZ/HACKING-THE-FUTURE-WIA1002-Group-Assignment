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
import java.util.Optional;

public class personalProfileController {
    @FXML
    private Stage stage;

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
    public void personalProfileStartUp(ActionEvent event) throws Exception {
        String fileName = "src/main/java/Data/user.csv";
        readUserDataFromCSV(fileName);
        Parent root2 = FXMLLoader.load(getClass().getResource("personalProfileYS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.homeStartUp(event);
    }

    public void quizButton(ActionEvent event) throws Exception {
        attemptQuizController attemptQuizController = new attemptQuizController();
        attemptQuizController.attemptQuizStartUp(event);
    }

    public void eventButton(ActionEvent event) throws Exception {
        viewEventController viewEventController = new viewEventController();
        viewEventController.viewEventStartUp(event);
    }

    public void bookingButton(ActionEvent event) throws Exception {
        bookingController bookingController = new bookingController();
        bookingController.bookingStartUp(event);
    }

    public void leaderBoardButton(ActionEvent event) throws Exception {
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception {
        personalProfileController personalProfileController = new personalProfileController();
        personalProfileController.personalProfileStartUp(event);
    }

    public void requestButton(ActionEvent event) throws Exception {
        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    public void friendButton(ActionEvent event) throws Exception {
        friendListController friendListController = new friendListController();
        friendListController.friendListStartUp(event);
    }

    public void logOutButton(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure want to log out?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Alert closed");

        } else if (result.get() == ButtonType.OK) {
            loginController loginController = new loginController();
            loginController.loginStartUp(event);

        } else if (result.get() == ButtonType.CANCEL) ;
    }

    private void readUserDataFromCSV(String fileName) throws IOException {

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",");

            if (userData[3] == "Young Student") {
                String usernameText = userData[0];
                String emailText = userData[1];
                String roleText = userData[3];
                String latText = userData[4];
                String longText = userData[5];
                String pointText = userData[6];

                username.setText(usernameText);
                email.setText(emailText);
                role.setText(roleText);
                coords.setText("(" + latText + ", " + longText);
                point.setText(pointText);
            }
        }
        reader.close();
    }
}