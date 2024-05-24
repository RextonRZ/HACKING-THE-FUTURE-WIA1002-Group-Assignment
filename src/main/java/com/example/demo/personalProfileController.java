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

    private String userMail;

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @FXML
    public void personalProfileStartUp(ActionEvent event) throws IOException {
        showError(this.userMail);
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("personalProfileYS.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(homeScene);

            String fileName = "src/main/java/Data/user.csv";
            String[] details = readUserDataYS(fileName, userMail);

            username = (Label) root2.lookup("#username");
            if (username != null) {
                username.setText(details[0]);
            } else {
                System.err.println("Error: Label 'username' not found.");
            }

            email = (Label) root2.lookup("#email");
            if (email != null) {
                email.setText(details[1]);
            } else {
                System.err.println("Error: Label 'email' not found.");
            }

            coords = (Label) root2.lookup("#coords");
            if (coords != null) {
                coords.setText("(" + details[3] + ", " + details[4] + ")");
            } else {
                System.err.println("Error: Label 'coords' not found.");
            }

            point = (Label) root2.lookup("#point");
            if (point != null) {
                point.setText(details[5]);
            } else {
                System.err.println("Error: Label 'point' not found.");
            }

            role = (Label) root2.lookup("#role");
            if (role != null) {
                role.setText(details[2]);
            } else {
                System.err.println("Error: Label 'role' not found.");
            }
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
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

    public void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }
}