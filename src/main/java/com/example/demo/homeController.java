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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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


    @FXML
    public void homeStartUp(ActionEvent event) throws Exception  {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Profile Dashboard
        //Username

        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude,roleSet;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                usernameSet = userData[0].trim();
                latitude = userData[4].trim();
                longitude = userData[5].trim();
                roleSet = userData[3].trim();
                role.setText(roleSet);
                username.setText(usernameSet);
                coordinate.setText(latitude+", " + longitude);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        public void quizButton(ActionEvent event) throws Exception{
        createQuizController createQuizController = new createQuizController();
        createQuizController.createQuizStartUp(event);
    }

    public void eventButton(ActionEvent event) throws Exception{
        createEventController createEventController = new createEventController();
        createEventController.createEventStartUp(event);
    }

    public void bookingButton(ActionEvent event) throws Exception{
        bookingController bookingController = new bookingController();
        bookingController.bookingStartUp(event);
    }

    public void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception{
        personalProfileController personalProfileController = new personalProfileController();
        personalProfileController.personalProfileStartUp(event);
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


}
