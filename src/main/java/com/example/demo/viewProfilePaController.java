package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

public class viewProfilePaController implements Initializable {
    @FXML
    private Label coords;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label username;
    @FXML
    private Label usernameTitle;
    @FXML
    private Label booking;
    @FXML
    private VBox vbox;

    String usernamelogin = loginController.HostUsername;


    String csv = "src/main/java/Data/bookingData.csv";

    public void profileStartUp(ActionEvent event) throws Exception {
        Parent root3 = FXMLLoader.load(getClass().getResource("ProfilePa.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Profile");
        Scene homeScene = new Scene(root3);
        stage.setScene(homeScene);
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
    }

    public void reset() {
        String bookings = setBooking();
        usernameTitle.setText(personalProfileYSController.Username);
        username.setText(personalProfileYSController.Username);
        email.setText(personalProfileYSController.Email);
        coords.setText(personalProfileYSController.Coordinate);
        role.setText(personalProfileYSController.Role);
        booking.setText(bookings);
    }

    public String setBooking() {
        String bookingShow = "";
        try (BufferedReader read = new BufferedReader(new FileReader(csv))) {
            String line, user;
            String[] bookingSet;
            ArrayList<String[]> show = new ArrayList<>();

            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                user = data[0].trim();

                if (user.equals(personalProfileYSController.searchUser)) {
                    bookingSet = new String[]{data[1].trim(), data[2].trim(), data[4].trim()};
                    show.add(bookingSet);
                }
            }
                show.sort(Comparator.comparing(a -> LocalDate.parse(a[2])));
                Collections.reverse(show);

                for (int i = 0; i < Math.min(show.size(), 3); i++) {
                    String[] pastBooking = show.get(i);
                    bookingShow += pastBooking[2].trim() + "\n" +
                            String.format("%-20s", pastBooking[0].trim()) +
                            String.format("%-50s\n\n", pastBooking[1].trim());

                    booking.setText(bookingShow);
                    Text text = new Text(bookingShow);
                    double textHeight = text.getLayoutBounds().getHeight() + 20;
                    vbox.setPrefHeight(textHeight + vbox.getPadding().getTop() + vbox.getPadding().getBottom() + 200);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return bookingShow;
    }
}