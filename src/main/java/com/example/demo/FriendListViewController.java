package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FriendListViewController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private Text friend1;
    @FXML
    private Text friend10;
    @FXML
    private Text friend11;
    @FXML
    private Text friend12;
    @FXML
    private Text friend13;
    @FXML
    private Text friend14;
    @FXML
    private Text friend15;
    @FXML
    private Text friend16;
    @FXML
    private Text friend17;
    @FXML
    private Text friend18;
    @FXML
    private Text friend19;
    @FXML
    private Text friend2;
    @FXML
    private Text friend20;
    @FXML
    private Text friend21;
    @FXML
    private Text friend22;
    @FXML
    private Text friend23;
    @FXML
    private Text friend24;
    @FXML
    private Text friend25;
    @FXML
    private Text friend26;
    @FXML
    private Text friend27;
    @FXML
    private Text friend28;
    @FXML
    private Text friend29;
    @FXML
    private Text friend3;
    @FXML
    private Text friend30;
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
    private VBox box1;
    @FXML
    private VBox box10;
    @FXML
    private VBox box11;
    @FXML
    private VBox box12;
    @FXML
    private VBox box13;
    @FXML
    private VBox box14;
    @FXML
    private VBox box15;
    @FXML
    private VBox box16;
    @FXML
    private VBox box17;
    @FXML
    private VBox box18;
    @FXML
    private VBox box19;
    @FXML
    private VBox box2;
    @FXML
    private VBox box20;
    @FXML
    private VBox box21;
    @FXML
    private VBox box22;
    @FXML
    private VBox box23;
    @FXML
    private VBox box24;
    @FXML
    private VBox box25;
    @FXML
    private VBox box26;
    @FXML
    private VBox box27;
    @FXML
    private VBox box28;
    @FXML
    private VBox box29;
    @FXML
    private VBox box3;
    @FXML
    private VBox box30;
    @FXML
    private VBox box4;
    @FXML
    private VBox box5;
    @FXML
    private VBox box6;
    @FXML
    private VBox box7;
    @FXML
    private VBox box8;
    @FXML
    private VBox box9;

    private Text[] friends;
    private VBox[] vbox;

    String fileName = "src/main/java/Data/friend_requests.csv";

    String viewProfileUsername;

    public void friendListStartUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("friendList.fxml")); // Adjust the path as needed
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
    }

    @FXML
    void bookingButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.bookingButton(event);
    }

    @FXML
    void eventButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.eventButton(event);
    }

    @FXML
    void homeButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    @FXML
    void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);
    }

    @FXML
    void profileButton(ActionEvent event) throws Exception{
        personalProfileYSController personalProfileYSController = new personalProfileYSController();
        personalProfileYSController.personalProfileStartUp(event);
    }

    @FXML
    void quizButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.quizButton(event);
    }

    @FXML
    void requestButton(ActionEvent event) throws Exception{
        friendRequestController friendRequestController = new friendRequestController();
        friendRequestController.friendRequestStartUp(event);
    }

    public void printFriendsList() {
        String line;
        ArrayList<String> friendList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] friend = line.split(",");
                byte status = Byte.parseByte(friend[2]);
                if (status == 1) {
                    if (friend[0].equals(loginController.HostUsername)){
                        friendList.add(friend[1]);
                    }else if (friend[1].equals(loginController.HostUsername)){
                        friendList.add(friend[0]);
                    }
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        for(VBox box: vbox){
            box.setVisible(false);
        }

        for (int i = 0; i < Math.min(friends.length, friendList.size()); i++) {
            friends[i].setText(friendList.get(i));
            vbox[i].setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        friends = new Text[]{
                friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8, friend9, friend10,
                friend11, friend12, friend13, friend14, friend15, friend16, friend17, friend18, friend19, friend20,
                friend21, friend22, friend23, friend24, friend25, friend26, friend27, friend28, friend29, friend30
        };
        vbox = new VBox[]{
                box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,
                box11,box12,box13,box14,box15,box16,box17,box18,box19,box20,
                box21,box22,box23,box24,box25,box26,box27,box28,box29,box30
        };
        printFriendsList();
    }

    private void setVar(Text friend){
        viewProfileUsername = friend.getText();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(viewProfileUsername)){
                    friendRequestController.UsernamePU = userData[0].trim();
                    friendRequestController.EmailPU = userData[1].trim();
                    friendRequestController.CoordinatePU = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                    friendRequestController.RolePU = userData[3].trim();
                    friendRequestController.PointsPU = userData[6].trim();
                    break;
                }
            }
        }catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
    }

    @FXML
    void viewProfile1(ActionEvent event) throws Exception{
        setVar(friend1);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile2(ActionEvent event) throws Exception{
        setVar(friend2);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile3(ActionEvent event) throws Exception{
        setVar(friend3);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile4(ActionEvent event) throws Exception{
        setVar(friend4);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile5(ActionEvent event) throws Exception{
        setVar(friend5);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile6(ActionEvent event) throws Exception{
        setVar(friend6);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile7(ActionEvent event) throws Exception{
        setVar(friend7);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile8(ActionEvent event) throws Exception{
        setVar(friend8);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile9(ActionEvent event) throws Exception{
        setVar(friend9);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile10(ActionEvent event) throws Exception{
        setVar(friend10);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile11(ActionEvent event) throws Exception{
        setVar(friend11);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile12(ActionEvent event) throws Exception{
        setVar(friend12);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile13(ActionEvent event) throws Exception{
        setVar(friend13);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile14(ActionEvent event) throws Exception{
        setVar(friend14);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile15(ActionEvent event) throws Exception{
        setVar(friend15);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile16(ActionEvent event) throws Exception{
        setVar(friend16);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile17(ActionEvent event) throws Exception{
        setVar(friend17);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile18(ActionEvent event) throws Exception{
        setVar(friend18);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile19(ActionEvent event) throws Exception{
        setVar(friend19);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile20(ActionEvent event) throws Exception{
        setVar(friend20);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile21(ActionEvent event) throws Exception{
        setVar(friend21);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile22(ActionEvent event) throws Exception{
        setVar(friend22);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile23(ActionEvent event) throws Exception{
        setVar(friend23);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile24(ActionEvent event) throws Exception{
        setVar(friend24);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile25(ActionEvent event) throws Exception{
        setVar(friend25);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile26(ActionEvent event) throws Exception{
        setVar(friend26);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile27(ActionEvent event) throws Exception{
        setVar(friend27);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile28(ActionEvent event) throws Exception{
        setVar(friend28);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile29(ActionEvent event) throws Exception{
        setVar(friend29);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void viewProfile30(ActionEvent event) throws Exception{
        setVar(friend30);

        viewProfileYSControllerWDelete viewProfileYSControllerWDelete = new viewProfileYSControllerWDelete();
        viewProfileYSControllerWDelete.profileStartUp(event);
    }

    @FXML
    void refresh(ActionEvent event) throws Exception{
        FriendListViewController friendListViewController = new FriendListViewController();
        friendListViewController.friendListStartUp(event);
    }
}
