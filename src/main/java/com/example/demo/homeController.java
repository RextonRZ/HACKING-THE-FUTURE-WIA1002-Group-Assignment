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
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.text.Text;


public class homeController implements Initializable {

    private Stage stage;

    @FXML
    private Text role;
    @FXML
    private Text coordinate;
    @FXML
    private Text point;
    @FXML
    private Text username;

    String usernamelogin = loginController.usernameID;
    public static String roleCon;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude, pointSet, roleSet;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                usernameSet = userData[0].trim();
                roleSet = userData[3].trim();
                latitude = userData[4].trim();
                longitude = userData[5].trim();

                if(usernameSet.equals(usernamelogin)) {
                    role.setText("\uD83D\uDC64  " + roleSet);
                    username.setText(usernameSet);
                    coordinate.setText("\uD83D\uDCCD  " + latitude + ", " + longitude);

                    if(roleSet.equals("Parent")||roleSet.equals("Educator")){
                        point.setText("");
                    }
                    else {
                        pointSet= userData[6].trim();
                        point.setText("⛀  "+pointSet);
                    }

                    roleCon = roleSet;

                }




            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void homeStartUp(ActionEvent event) throws Exception  {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }



    public void quizButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Young Student")) {
            attemptQuizController attemptQuizController = new  attemptQuizController();
            attemptQuizController.attemptQuizStartUp(event);
        }

        else if(roleCon.equals("Educator")) {
            createQuizController createQuizController = new  createQuizController();
            createQuizController.createQuizStartUp(event);
        }

        else {
            showError("You have no access to this page.");
        }
    }

    public void eventButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Educator")) {
            chooseEventController chooseEventController = new chooseEventController();
            chooseEventController.chooseEventStartUp(event);
        }

        else {
            viewEventController viewEventController = new viewEventController();
            viewEventController.viewEventStartUp(event);
        }
    }

    public void bookingButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Parent")) {
            bookingController bookingController = new bookingController();
            bookingController.bookingStartUp(event);
        }

        else {
            showError("You have no access to this page.");
        }

    }

    public void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Young Student")){
            personalProfileYSController personalProfileYSController = new personalProfileYSController();
            personalProfileYSController.personalProfileStartUp(event);
        }

        else if(roleCon.equals("Educator")){
            personalProfileEduController personalProfileEduController = new personalProfileEduController();
            personalProfileEduController.personalProfileStartUp(event);
        }

        else{
            personalProfilePaController personalProfilePaController = new personalProfilePaController();
            personalProfilePaController.personalProfileStartUp(event);
        }

    }

    public void logOutButton(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure want to log out?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isEmpty()){
            System.out.println("Alert closed");

        } else if(result.get() == ButtonType.OK) {
            loginController loginController = new loginController();
            loginController.loginStartUp(event);

        } else if (result.get() == ButtonType.CANCEL);

    }

    public void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }


}
