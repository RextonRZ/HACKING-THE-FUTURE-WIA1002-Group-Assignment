package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.time.LocalTime;

import java.util.Optional;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class createEventController {
    @FXML
    private Stage stage;

    @FXML
    private TextField eventTitle;

    @FXML
    private Text eventTitleErrorMessage;

    @FXML
    private TextField eventDescription;

    @FXML
    private Text eventDescriptionErrorMessage;

    @FXML
    private TextField eventVenue;

    @FXML
    private Text eventVenueErrorMessage;

    @FXML
    private DatePicker eventDate;

    @FXML
    private Text eventDateErrorMessage;

    @FXML
    private TextField eventStartTime;

    @FXML
    private Text eventStartTimeErrorMessage;

    @FXML
    private TextField eventEndTime;

    @FXML
    private Text eventEndTimeErrorMessage;





    private boolean titleValid = false, descriptionValid = false, venueValid = false, dateValid = false,
            timeStartValid = false, timeEndValid = false;

    String Title, Description, Venue, Date, StartTime, EndTime;

    @FXML
    public void createEventStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("eventCreate.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void eventDescriptionValidation() throws Exception {
        Description = eventDescription.getText();

        if (Description.length() > 300) {
            eventDescriptionErrorMessage.setText("Event description should not contain more than 300 characters");
            descriptionValid = false;
        }else if(Description.isEmpty()){
            eventDescriptionErrorMessage.setText("Event description should not be empty");
            descriptionValid = false;
        } else {
            eventDescriptionErrorMessage.setText("");
            descriptionValid = true;
        }
    }


    public void eventDateValidation() {
        LocalDate date = null;
        String dateString = eventDate.getEditor().getText();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            date = LocalDate.parse(dateString, dateFormatter);
            if (date.isBefore(LocalDate.now())) {
                eventDateErrorMessage.setText("The selected date has already passed.");
                dateValid = false;
            } else {
                eventDateErrorMessage.setText("");
                dateValid = true;
            }
        } catch (DateTimeParseException e) {
            eventDateErrorMessage.setText("Event date should not be empty");
            dateValid = false;
        }
    }



    public void eventStartTimeValidation() {
        String startTimeText = eventStartTime.getText(); // corrected to eventEndTime.getText()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        try {
            LocalTime.parse(startTimeText, formatter);
            eventStartTimeErrorMessage.setText("");
            timeStartValid = true;
        } catch (DateTimeParseException e) {
            eventStartTimeErrorMessage.setText("Incorrect format of time (Exp: 11:00 pm)");
            if (startTimeText.isBlank()) eventStartTimeErrorMessage.setText("Event start time should not be empty");
            timeStartValid = false;
        }
    }

    public void eventEndTimeValidation() throws Exception {
        String endTimeText = eventEndTime.getText(); // corrected to eventEndTime.getText()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        try {
            LocalTime.parse(endTimeText, formatter);
            eventEndTimeErrorMessage.setText("");
            timeEndValid = true;
        } catch (DateTimeParseException e) {
            eventEndTimeErrorMessage.setText("Incorrect format of time (Exp: 8:00 am)");
            if (endTimeText.isBlank()) eventEndTimeErrorMessage.setText("Event end time should not be empty");
            timeEndValid = false;
        }
    }

    public void eventTitleValidation() throws Exception{
        Title = eventTitle.getText();
        String[] words = Title.split("\\s+");
        if (words.length > 20) {
            eventTitleErrorMessage.setText("Event Title should not have more than 20 words");
            venueValid = false;
        }
        if (Title.isBlank()) {
            eventTitleErrorMessage.setText("Event Title should not be empty");
            titleValid = false;
        }else {
            eventTitleErrorMessage.setText("");
            titleValid = true;
        }
    }

    public void eventVenueValidation() throws Exception{
        Venue = eventVenue.getText();

        if (Venue.isBlank()) {
            eventVenueErrorMessage.setText("Event venue should not be empty");
            venueValid = false;
        } else {
            eventVenueErrorMessage.setText("");
            venueValid = true;
        }
    }

    @FXML
    public void createEvent(ActionEvent event) throws Exception {

        eventTitleValidation();
        eventDescriptionValidation();
        eventVenueValidation();
        eventDateValidation();
        eventStartTimeValidation();
        eventEndTimeValidation();


        if (!titleValid || !descriptionValid || !venueValid || !dateValid || !timeStartValid || !timeEndValid) {
            showError("Please make sure you correct all the fields stated.");
            if (Title.isBlank()) eventTitleErrorMessage.setText("Event Title should not be empty");
            if (Description.isBlank()) eventDescriptionErrorMessage.setText("Event Description should not be empty");
            if (Venue.isBlank()) eventVenueErrorMessage.setText("Event venue should not be empty");
            if (eventDate.getValue() == null) eventDateErrorMessage.setText("Event date should not be empty");
            if (StartTime.isBlank()) eventStartTimeErrorMessage.setText("Event start time should not be empty");
            if (EndTime.isBlank()) eventEndTimeErrorMessage.setText("Event end time should not be empty");
        } else {
            // store event or proceed further
            showCreateEventSuccess();
        }
    }


    @FXML
    public void homeButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void quizButton(ActionEvent event) throws Exception{
        attemptQuizController attemptQuizController = new attemptQuizController();
        attemptQuizController.attemptQuizStartUp(event);
    }

    public void eventButton(ActionEvent event) throws Exception{
        viewEventController viewEventController = new viewEventController();
        viewEventController.viewEventStartUp(event);
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

    private void showCreateEventSuccess(){
        Alert alertSU = new Alert(AlertType.INFORMATION);
        alertSU.setTitle("Successful");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Event "+ Title + " succesfully created.");

        alertSU.showAndWait();
    }

    public void showError(String errorMessage){
        Alert alertError = new Alert(AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }
}