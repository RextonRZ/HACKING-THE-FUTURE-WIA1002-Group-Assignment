package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
        setVar(friend1);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile2(ActionEvent event) throws Exception{
        setVar(friend2);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile3(ActionEvent event) throws Exception{
        setVar(friend3);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile4(ActionEvent event) throws Exception{
        setVar(friend4);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile5(ActionEvent event) throws Exception{
        setVar(friend5);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile6(ActionEvent event) throws Exception{
        setVar(friend6);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile7(ActionEvent event) throws Exception{
        setVar(friend7);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile8(ActionEvent event) throws Exception{
        setVar(friend8);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile9(ActionEvent event) throws Exception{
        setVar(friend9);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    @FXML
    void viewProfile10(ActionEvent event) throws Exception{
        setVar(friend10);

        viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
        viewProfileYSControllerNoADD.profileStartUp(event);
    }

    public static String UsernamePU, EmailPU, CoordinatePU, RolePU, PointsPU;
    private void setVar(Text friend){
        viewProfileUsername = friend.getText();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(viewProfileUsername)){
                    UsernamePU = userData[0].trim();
                    EmailPU = userData[1].trim();
                    CoordinatePU = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                    RolePU = userData[3].trim();
                    PointsPU = userData[6].trim();
                    break;
                }
            }
        }catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        friends = new Text[]{friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8, friend9, friend10};
        vbox = new VBox[]{vbox1,vbox2,vbox3,vbox4,vbox5,vbox6,vbox7,vbox8,vbox9,vbox10};
        printFriendsRequest();
    }

    @FXML
    void reject1(ActionEvent event) throws Exception{
        deleteEntry(friend1);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject2(ActionEvent event) throws Exception{
        deleteEntry(friend2);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject3(ActionEvent event) throws Exception{
        deleteEntry(friend3);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject4(ActionEvent event) throws Exception{
        deleteEntry(friend4);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject5(ActionEvent event) throws Exception{
        deleteEntry(friend5);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject6(ActionEvent event) throws Exception{
        deleteEntry(friend6);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject7(ActionEvent event) throws Exception{
        deleteEntry(friend7);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject8(ActionEvent event) throws Exception{
        deleteEntry(friend8);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject9(ActionEvent event) throws Exception{
        deleteEntry(friend9);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void reject10(ActionEvent event) throws Exception{
        deleteEntry(friend10);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    public void deleteEntry(Text friend){
        String line;
        StringBuilder entries = new StringBuilder();
        viewProfileUsername = friend.getText();
        String check = viewProfileUsername + "," + loginController.HostUsername + ",0";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.equals(check)){
                    entries.append("");
                }else{
                    entries.append(line + "\n");
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            String entry = entries.toString();
            writer.print(entry);
            writer.flush();
            showDeleteSuccess();
        }catch (IOException e) {
            personalProfileYSController.showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            personalProfileYSController.showError("Error storing new event data: " + e.getMessage());
        }
    }

    private void showDeleteSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Friend request from " + viewProfileUsername + " successfully deleted!");

        alert.showAndWait();
    }

    @FXML
    void accept1(ActionEvent event) throws Exception{
        changeStatus(friend1);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept2(ActionEvent event) throws Exception{
        changeStatus(friend2);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept3(ActionEvent event) throws Exception{
        changeStatus(friend3);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept4(ActionEvent event) throws Exception{
        changeStatus(friend4);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept5(ActionEvent event) throws Exception{
        changeStatus(friend5);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept6(ActionEvent event) throws Exception{
        changeStatus(friend6);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept7(ActionEvent event) throws Exception{
        changeStatus(friend7);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept8(ActionEvent event) throws Exception{
        changeStatus(friend8);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept9(ActionEvent event) throws Exception{
        changeStatus(friend9);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    @FXML
    void accept10(ActionEvent event) throws Exception{
        changeStatus(friend10);

        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    public void changeStatus(Text friend){
        String line;
        StringBuilder entries = new StringBuilder();
        viewProfileUsername = friend.getText();
        String check = viewProfileUsername + "," + loginController.HostUsername + ",0";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.equals(check)){
                    entries.append("");
                }else{
                    entries.append(line + "\n");
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            String entry = entries.toString();
            writer.print(entry);
            writer.print(viewProfileUsername + "," + loginController.HostUsername + ",1");
            writer.flush();
            showAddSuccess();
        }catch (IOException e) {
            personalProfileYSController.showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            personalProfileYSController.showError("Error storing new event data: " + e.getMessage());
        }
    }

    private void showAddSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Successfully added " + viewProfileUsername + " as friend!");

        alert.showAndWait();
    }

}