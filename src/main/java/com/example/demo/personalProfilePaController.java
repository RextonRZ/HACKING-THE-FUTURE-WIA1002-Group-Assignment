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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.scene.layout.VBox;
public class personalProfilePaController implements Initializable {

    @FXML
    private Stage stage;

    @FXML
    private Label coords;

    @FXML
    private Label email;

    @FXML
    private Label booking;

    @FXML
    private Label role;

    @FXML
    private Label username;

    @FXML
    private VBox vbox;

    @FXML
    private TextField findChildren;

    String searchChildren;

    String usernamelogin = loginController.HostUsername;
    String fileName = "src/main/java/Data/ParentChild.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude, roleSet, emailSet;
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

                    if (roleSet.equals("Parent")) {

                        String csv = "src/main/java/Data/bookingData.csv";

                        try (BufferedReader read = new BufferedReader(new FileReader(csv))) {
                            String in, user;
                            String[] bookingSet;
                            ArrayList<String[]> show = new ArrayList<>();
                            String bookingShow = "";
                            while (( in = read.readLine()) != null) {
                                String[] data = in.split(",");
                                user= data[0].trim();

                                if(user.equals(usernamelogin)) {
                                    bookingSet = new String[]{data[1].trim(), data[3].trim()};
                                    show.add(bookingSet);
                                    show.sort(Comparator.comparing(a-> LocalDate.parse(a[1])));
                                }
                            }

                            for(int i=0;i<Math.min(show.size(),5);i++){
                                String [] haha = show.get(i);
                                bookingShow += haha[0].trim() + "\t" + haha[1].trim() + "\n\n";
                                booking.setText(bookingShow);
                                Text text = new Text(bookingShow);
                                double textHeight = text.getLayoutBounds().getHeight()+20;
                                vbox.setPrefHeight(textHeight + vbox.getPadding().getTop() + vbox.getPadding().getBottom()+200);
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void personalProfileStartUp(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("personalProfilePa.fxml"));
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

    public void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);

    }

    @FXML
    public void viewProfile(ActionEvent event) throws Exception{
        searchChildren = findChildren.getText();

        if (searchChildren == null || searchChildren.isEmpty()){
            personalProfileYSController.showError("Please enter a username!");
        } else if (!usernameFound()) {
            personalProfileYSController.showError("User not found!");
        } else if (!isChildren()) {
            personalProfileYSController.showError("User is not a 'Young Student'!");
        } else {
            viewProfileYSControllerNoADD viewProfileYSControllerNoADD = new viewProfileYSControllerNoADD();
            viewProfileYSControllerNoADD.profileStartUp(event);
        }
    }



    public boolean isChildren(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(searchChildren) && userData[3].equals("Young Student")) {
                    friendRequestController.UsernamePU = userData[0].trim();
                    friendRequestController.EmailPU = userData[1].trim();
                    friendRequestController.CoordinatePU = "(" + userData[4].trim() + ", " + userData[5].trim() + ")";
                    friendRequestController.RolePU = userData[3].trim();
                    friendRequestController.PointsPU = userData[6].trim();
                    return true;
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public boolean usernameFound(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(searchChildren))
                    return true;
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    @FXML
    void addChildren(ActionEvent event) throws Exception{
        searchChildren = findChildren.getText();

        if (searchChildren == null || searchChildren.isEmpty()){
            personalProfileYSController.showError("Please enter a username!");
        } else if (!usernameFound()) {
            personalProfileYSController.showError("User not found!");
        } else if (!isChildren()) {
            personalProfileYSController.showError("User is not a 'Young Student'!");
        } else if (overParents()){
            personalProfileYSController.showError(searchChildren + " has 2 parents now! One children can only have at most 2 parents!");
        } else {
            doubleConfirm(event);
        }

    }

    public boolean overParents(){
        String line;
        int parentNo = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] relationshipData = line.split(",");
                if (relationshipData[1].equals(searchChildren))
                    parentNo++;
                System.out.println(parentNo);
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        if (parentNo >= 2){
            return true;
        }else {
            return false;
        }
    }

    public void doubleConfirm(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Children");
        alert.setContentText("Are you sure want to add " + searchChildren + " as children?\nThis process is permanent and irreversible!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Alert closed");

        } else if (result.get() == ButtonType.OK) {
            storeRelation();
            childrenAdded();
            personalProfilePaController personalProfilePaController = new personalProfilePaController();
            personalProfilePaController.personalProfileStartUp(event);

        } else if (result.get() == ButtonType.CANCEL) ;
    }

    public void storeRelation(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
            writer.println(loginController.HostUsername + "," + searchChildren);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void childrenAdded() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Children added successfully!");

        alert.showAndWait();
    }
}
