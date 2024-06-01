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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
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
    @FXML
    private Text[] friends;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox10;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private VBox vbox4;
    @FXML
    private VBox vbox5;
    @FXML
    private VBox vbox6;
    @FXML
    private VBox vbox7;
    @FXML
    private VBox vbox8;
    @FXML
    private VBox vbox9;
    @FXML
    private VBox[] vbox;
    @FXML
    private ScrollPane showPanel;

    String fileName = "src/main/java/Data/friend_requests.csv";

    String viewProfileUsername;

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

        for(VBox box: vbox){
            box.setVisible(false);
        }

        for (int i = 0; i < Math.min(friends.length, pendingRequest.size()); i++) {
                friends[i].setText(pendingRequest.get(i));
                vbox[i].setVisible(true);
        }
    }

    @FXML
    void viewProfile1(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend1);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile2(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend2);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile3(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend3);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile4(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend4);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile5(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend5);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile6(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend6);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile7(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend7);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile8(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend8);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile9(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend9);
        viewProfileYSControllerNoADD.reset();
    }

    @FXML
    void viewProfile10(ActionEvent event) throws Exception{
        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);

        setVar(friend10);
        viewProfileYSControllerNoADD.reset();
    }


    private void setVar(Text friend){
        viewProfileUsername = friend.getText();
        System.out.println(viewProfileUsername);
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(viewProfileUsername)){
                    personalProfileYSController.Username = userData[0].trim();
                    personalProfileYSController.Email = userData[1].trim();
                    personalProfileYSController.Coordinate = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                    personalProfileYSController.Role = userData[3].trim();
                    personalProfileYSController.Points = userData[6].trim();
                    break;
                }
            }
        }catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        System.out.println(personalProfileYSController.Username);
        System.out.println(personalProfileYSController.Email);
        System.out.println(personalProfileYSController.Coordinate);
        System.out.println(personalProfileYSController.Role);
        System.out.println(personalProfileYSController.Points);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        friends = new Text[]{friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8, friend9, friend10};
        vbox = new VBox[]{vbox1,vbox2,vbox3,vbox4,vbox5,vbox6,vbox7,vbox8,vbox9,vbox10};
        printFriendsRequest();
    }

}