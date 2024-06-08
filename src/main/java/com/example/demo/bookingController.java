package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
    private ChoiceBox childChoiceBox;

    @FXML
    private TextArea timeSlotsTextArea;

    @FXML
    private ChoiceBox timeSlotBooking;

    private ArrayList<BookingDestination> bookingDestinations;
    String usernamelogin = loginController.HostUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        childSelection();
        loadBookingDestinations();
        displayBookingDestinations();
        childChoiceBox.setOnAction(this::destinationSelected);
    }


    public void childSelection(){
        String fileName = "src/main/java/Data/ParentChild.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String user = userData[0].trim();

                if (user.equals(usernamelogin)) {
                    String child = userData[1].trim();
                    childChoiceBox.getItems().add(child);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    private void destinationSelected(Event event) {
        ArrayList<String> timeslot = new ArrayList<>();
        ArrayList<String> prebook = new ArrayList<>();
        ArrayList<String> allDate = new ArrayList<>();
        int clashing;

        LocalDate currentDate = LocalDate.now();

        if (!childChoiceBox.getSelectionModel().isEmpty()) {
            String fileName = "src/main/java/Data/bookingData.csv";

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    String user = userData[1].trim();

                    if (user.equals(childChoiceBox.getValue())) {
                        String date = userData[4].trim();
                        prebook.add(date);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String file = "src/main/java/Data/registerEvent.csv";

            try (BufferedReader read = new BufferedReader(new FileReader(file))) {
                String s;

                while ((s = read.readLine()) != null) {
                    String[] userData = s.split(",");
                    String user = userData[0].trim();

                    if (user.equals(childChoiceBox.getValue())) {
                        String date = userData[2].substring(0, 10);
                        prebook.add(date);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i < 8; i++) {
                allDate.add(currentDate.plusDays(i).toString());
            }

            for (String date : allDate) {
                clashing=0;

                for(String filter: prebook) {
                    if (filter.equals(date)) {
                        clashing ++;
                    }
                }
                if(clashing==0) {
                    timeslot.add(date);
                }
            }

            for(int i=0; i< timeslot.size(); i++){
                String n = "[" + (i+1) + "] " + timeslot.get(i) + "\n";
                timeSlotsTextArea.appendText(n);
                timeSlotBooking.getItems().add(n);
            }

        }

    }



    @FXML
    public void bookDestination(ActionEvent event) throws Exception{
        int selectedIndex = destinationChoiceBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Please select a destination.");
            return;
        }

        if (timeSlotBooking.getValue() == null) {
            showAlert("Error", "Please select a time slot.");
            return;
        }

        if (childChoiceBox.getValue() == null) {
            showAlert("Error", "Please select a child.");
            return;
        }
        String fileName = "src/main/java/Data/bookingData.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.print(usernamelogin+ ","+childChoiceBox.getValue() + "," + destinationChoiceBox.getValue().toString().replace("\n", ",") + "," + timeSlotBooking.getValue().toString().substring(4).trim() + "\n");
            writer.flush();
            showBookingSuccess();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bookingStartUp(event);
    }

    private void showBookingSuccess(){
        Alert alertSU = new Alert(Alert.AlertType.INFORMATION);
        alertSU.setTitle("Booking Successful");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Your booking has been successfully made!");

        alertSU.showAndWait();
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