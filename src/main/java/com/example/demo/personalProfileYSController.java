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
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class personalProfileYSController implements Initializable {
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


    String usernamelogin = loginController.usernameID;

    @FXML
    public void personalProfileStartUp(ActionEvent event) throws IOException {
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
        personalProfileYSController personalProfileYSController = new personalProfileYSController();
        personalProfileYSController.personalProfileStartUp(event);
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

    private String[] readUserDataYS(String fileName, String userMail) throws IOException {
        String line;
        String[] details = new String[5];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",");

            if ("Young Student".equals(userData[3]) && ((userMail.equals(userData[0])) || (userMail.equals(userData[1])))) {
                details[0] = userData[0];
                details[1] = userData[1];
                details[2] = userData[3];
                details[3] = userData[4];
                details[4] = userData[5];
                details[5] = userData[6];
            }
        }
        reader.close();
        return details;
    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude, roleSet, emailSet, pointSet;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                usernameSet = userData[0].trim();
                emailSet = userData[1].trim();
                latitude = userData[4].trim();
                longitude = userData[5].trim();
                roleSet = userData[3].trim();
                pointSet = userData[6].trim();

                if (usernameSet.equals(usernamelogin)) {
                    role.setText(roleSet);
                    username.setText(usernameSet);
                    coords.setText(latitude + ", " + longitude);
                    email.setText(emailSet);
                    point.setText(pointSet);

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}