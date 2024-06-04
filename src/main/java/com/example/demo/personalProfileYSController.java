package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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

    @FXML
    private Text parent1;

    @FXML
    private Text parent2;

    @FXML
    private TextField newFriendUsername;

    String fileName = "src/main/java/Data/user.csv";
    String usernamelogin = loginController.HostUsername;
    public static String searchUser;
    String userMail, userRole;
    StringBuilder[] errorMessage = {
        new StringBuilder("Oops! Looks like you've entered your own username. Try searching for someone else this time! \uD83D\uDE04"),
        new StringBuilder("Hold up! You're already here! Try searching for another username. \uD83D\uDE04"),
        new StringBuilder("Whoops! That's you! Try searching for a different username to find someone else. 🕵️‍♂️"),
        new StringBuilder("Hey there, you! You're already in the spotlight! Search for someone else's username to find them. 🌟"),
        new StringBuilder("Uh-oh! You've stumbled upon yourself! Try searching for a different username to explore. 🚀"),
        new StringBuilder("Looks like you're searching for a mirror image! Try searching for a different username to find someone new. 🪞")
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude, roleSet, emailSet, pointSet;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                usernameSet = userData[0].trim();
                emailSet = userData[1].trim();
                roleSet = userData[3].trim();
                latitude = userData[4].trim();
                longitude = userData[5].trim();

                if (usernameSet.equals(usernamelogin)) {
                    role.setText(roleSet);
                    username.setText(usernameSet);
                    coords.setText("(" + latitude + ", " + longitude + ")");
                    email.setText(emailSet);

                    if(roleSet.equals("Young Student")) {
                        pointSet = userData[6].trim();
                        point.setText(pointSet);
                    }

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    public void requestButton(ActionEvent event) throws Exception {
        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    public void friendButton(ActionEvent event) throws Exception {
        FriendListViewController friendListController = new FriendListViewController();
        friendListController.friendListStartUp(event);
    }

    public void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);
    }

    public void viewProfile(ActionEvent event) throws Exception{
        searchUser = newFriendUsername.getText();

        if (searchUser == null || searchUser.isEmpty()){
            showError("Please enter a username!");
        }else if (searchUser.equals(usernamelogin)) {
            Random random = new Random();
            int index = random.nextInt(6);
            System.out.println(index);
            showError(errorMessage[index].toString());
        } else if (authenticate()) {
            if (Role.equals("Young Student")) {
                viewProfileYSController viewProfileController = new viewProfileYSController();
                viewProfileController.profileStartUp(event);
            }else if (Role.equals("Educator")){
                viewProfileEduController viewProfileController = new viewProfileEduController();
                viewProfileController.profileStartUp(event);
            }else if (Role.equals("Parent")){
                viewProfilePaController viewProfileController = new viewProfilePaController();
                viewProfileController.profileStartUp(event);
            }
        } else{
            showError("User not found!");
        }
    }

    public static void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }

    String line;
    public static String Username, Email, Coordinate, Role, Points, Quiz, Events;
    private boolean authenticate(){
        searchUser = newFriendUsername.getText();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (searchUser.endsWith(".com")){
                    userMail = userData[1].trim();
                }else{
                    userMail = userData[0].trim();
                }
                userRole = userData[3].trim();

                if (userMail.equals(searchUser)){
                    if (userRole.equals("Young Student")){
                        Username = userData[0].trim();
                        Email = userData[1].trim();
                        Coordinate = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                        Role = userRole;
                        Points = userData[6].trim();
                    } else if (userRole.equals("Educator")) {
                        Username = userData[0].trim();
                        Email = userData[1].trim();
                        Coordinate = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                        Role = userRole;
                        Events = userData[6].trim();
                        Quiz = userData[7].trim();
                    } else if (userRole.equals("Parent")){
                        Username = userData[0].trim();
                        Email = userData[1].trim();
                        Coordinate = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                        Role = userRole;
                    }
                    return true;
                }
            }
        }catch (IOException e) {
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }
}