package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class friendRequestController {
    @FXML
    private Stage stage;


    private static User currentUser;


    private static final String USER_FILE = "src/main/java/Data/user.csv";
    private static final String REQUEST_FILE = "src/main/java/Data/friend_requests.csv";

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    @FXML
    public void friendRequestStartUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("friendRequest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
    }

    public void sendFriendRequest(String fromUsername, String toUsername) throws IOException {
        List<String> requests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                requests.add(line);
            }
        }

        requests.add(fromUsername + "," + toUsername);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(REQUEST_FILE))) {
            for (String request : requests) {
                bw.write(request);
                bw.newLine();
            }
        }
    }

    public void manageFriendRequests(String username, boolean accept) throws IOException {
        List<String> requests = new ArrayList<>();
        List<String> updatedRequests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                requests.add(line);
                String[] parts = line.split(",");
                if (parts[1].equals(username)) {
                    if (accept) {
                        addFriend(parts[0], parts[1]);
                    }
                } else {
                    updatedRequests.add(line);
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(REQUEST_FILE))) {
            for (String request : updatedRequests) {
                bw.write(request);
                bw.newLine();
            }
        }
    }

    private void addFriend(String user1, String user2) throws IOException {
        List<String> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(line);
            }
        }

        List<String> updatedUsers = new ArrayList<>();
        for (String user : users) {
            String[] parts = user.split(",");
            if (parts[0].equals(user1)) {
                parts[5] = parts[5] + ":" + user2;
            }
            if (parts[0].equals(user2)) {
                parts[5] = parts[5] + ":" + user1;
            }
            updatedUsers.add(String.join(",", parts));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (String updatedUser : updatedUsers) {
                bw.write(updatedUser);
                bw.newLine();
            }
        }
    }

    public List<String> viewFriendList(String username) throws IOException {
        List<String> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(line);
            }
        }

        for (String user : users) {
            String[] parts = user.split(",");
            if (parts[0].equals(username)) {
                return Arrays.asList(parts[5].split(":"));
            }
        }
        return new ArrayList<>();
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void quizButton(ActionEvent event) throws Exception {
        attemptQuizController attemptQuizController = new attemptQuizController();
        attemptQuizController.attemptQuizStartUp(event);
    }

    @FXML
    public void eventButton(ActionEvent event) throws Exception {
        viewEventController viewEventController = new viewEventController();
        viewEventController.viewEventStartUp(event);
    }

    @FXML
    public void bookingButton(ActionEvent event) throws Exception {
        bookingController bookingController = new bookingController();
        bookingController.bookingStartUp(event);
    }

    @FXML
    public void leaderBoardButton(ActionEvent event) throws Exception {
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    @FXML
    public void profileButton(ActionEvent event) throws Exception {
        personalProfileController personalProfileController = new personalProfileController();
        personalProfileController.personalProfileStartUp(event);
    }

    @FXML
    public void friendButton(ActionEvent event) throws Exception {
        FriendListController friendListController = new FriendListController();
        friendListController.friendListStartUp(event);
    }

    @FXML
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

    @FXML
    public void handleSendRequestButton(ActionEvent event) {
        String currentUser = getCurrentUsername(); // You should implement this method to get the logged-in user's email
        DashboardController dashboardController = new DashboardController(currentUser);
        dashboardController.promptFriendRequest();
    }
    private String getCurrentUsername() {
        return currentUser.getEmail();
    }


}
