package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
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


    String usernamelogin = loginController.usernameID;

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
}
