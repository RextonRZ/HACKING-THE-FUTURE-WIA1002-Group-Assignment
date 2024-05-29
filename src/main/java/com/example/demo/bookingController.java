package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class bookingController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private ChoiceBox destinationChoiceBox;

    @FXML
    private TextArea timeSlotsTextArea;

    @FXML
    private ChoiceBox timeSlotBooking;

    private ArrayList<BookingDestination> bookingDestinations;
    String usernamelogin = loginController.usernameID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBookingDestinations();
        displayBookingDestinations();
    }

    private void loadBookingDestinations() {
        bookingDestinations = new ArrayList<>();


        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/Data/BookingDestination.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String name = line;
                line = br.readLine();
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                BookingDestination destination = new BookingDestination(name, x, y);
                bookingDestinations.add(destination);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void displayBookingDestinations() {
        String fileName = "src/main/java/Data/user.csv";
        String user;
        double userX = 0, userY = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                user = userData[0].trim();

                if (user.equals(usernamelogin)) {
                    userX = Double.parseDouble(userData[4].trim());
                    userY = Double.parseDouble(userData[5].trim());
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (BookingDestination destination : bookingDestinations) {
            double distance = Math.sqrt(Math.pow(userX - destination.getX(), 2) + Math.pow(userY - destination.getY(), 2));
            destination.setDistance(distance);
        }

        Collections.sort(bookingDestinations, Comparator.comparing(BookingDestination::getDistance));

        for (BookingDestination destination : bookingDestinations) {
            destinationChoiceBox.getItems().add(destination.getName() + "\n" + String.format("%.2f km away", destination.getDistance()));
            destinationChoiceBox.setValue("");
        }
    }

    public void destinationSelected(MouseEvent event) {
        timeSlotsTextArea.clear();
        timeSlotBooking.getItems().clear();
        timeSlotBooking.getSelectionModel().clearSelection();

        ArrayList<String> timeslot = new ArrayList<>();

        if(destinationChoiceBox.getValue()!=null) {
            LocalDate currentDate = LocalDate.now();
            for (int i = 1; i < 7; i++) {
                String n = "[" + i + "] " + currentDate.plusDays(i) + "\n";
                timeslot.add(n);
            }
        }

        for(String slot: timeslot){
            timeSlotsTextArea.appendText(slot);
            timeSlotBooking.getItems().add(slot);
        }
    }

    @FXML
    public void bookDestination(ActionEvent event) {
        int selectedIndex = destinationChoiceBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Please select a destination.");
            return;
        }

        if (timeSlotBooking.getValue()==null) {
            showAlert("Error", "Please select a time slot.");
            return;
        }

        String fileName = "src/main/java/Data/bookingData.csv";
        if(!clashing()){
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
                writer.print(usernamelogin+","+destinationChoiceBox.getValue().toString().replace("\n",",")+","+timeSlotBooking.getValue().toString().substring(4).trim()+"\n");
                writer.flush();timeSlotBooking.getValue().toString().substring(4).trim();
                showBookingSuccess();
                destinationChoiceBox.getSelectionModel().clearSelection();
                timeSlotsTextArea.clear();
                timeSlotBooking.getItems().clear();
                timeSlotBooking.getSelectionModel().clearSelection();
                destinationChoiceBox.setValue("");

            }catch (IOException e){
                throw new RuntimeException(e);
            }
        } else{
            showAlert("Clashing", "You did a booking on this date.");
        }
    }

    private void showBookingSuccess(){
        Alert alertSU = new Alert(Alert.AlertType.INFORMATION);
        alertSU.setTitle("Booking Successful");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Your booking has been successfully made!");

        alertSU.showAndWait();
    }

    public boolean clashing() {
        // Additional logic to check for event clashes can be implemented here
        String fileName = "src/main/java/Data/bookingData.csv";
        String prebook;
        String date = timeSlotBooking.getValue().toString().substring(4).trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String user = userData[0].trim();

                if (user.equals(usernamelogin)) {
                    prebook = userData[3].trim();

                    if (prebook.equals(date)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void bookingStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("booking.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);

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
        homeController homeController = new homeController();
        homeController.profileButton(event);
    }

    public void logOutButton(ActionEvent event) throws Exception{
        homeController homeController = new homeController();
        homeController.logOutButton(event);

    }

}