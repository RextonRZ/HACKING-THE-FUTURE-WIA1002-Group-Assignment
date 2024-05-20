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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

        if (Title.length() > 100) {
            eventTitleErrorMessage.setText("Event description should not contain more than 100 characters");
        } else {
            eventTitleErrorMessage.setText(""); 
            titleValid = true;
        }
    }
    
    
    public void eventDateValidation() {
        LocalDate date = eventDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate currentDate = LocalDate.now();//today's date

            if (date.isBefore(currentDate)) {
                eventDateErrorMessage.setText("The selected date has already passed.");
                dateValid = false;
                return;
            }
            try {
                // Format the selected date to check for correct format
                String formattedDate = date.format(formatter);
                eventDateErrorMessage.setText("");
                dateValid = true;
            } catch (Exception e) {
                eventDateErrorMessage.setText("Incorrect format of date DD/MM/YYYY");
                dateValid = false;
            }
    }
    
    
    public void eventStartTimeValidation() throws Exception {
        String startTimeText = eventStartTime.getText();

        // Define the expected format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        try {
            // Parse the input time
            LocalTime startTime = LocalTime.parse(startTimeText, formatter);

        } catch (Exception e) {
            eventStartTimeErrorMessage.setText("Incorrect format of time (Exp: 8:00 am)");
            timeStartValid = false;
        }
    }
    
    public void eventEndTimeValidation() throws Exception {
        String endTimeText = eventStartTime.getText();

        // Define the expected format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        try {
            // Parse the input time
            LocalTime endTime = LocalTime.parse(endTimeText, formatter);

        } catch (Exception e) {
            eventEndTimeErrorMessage.setText("Incorrect format of time (Exp: 8:00 am)");
            timeEndValid = false;
        }
    }
    
    
    @FXML
    public void createEvent(ActionEvent event) throws Exception{
        // Collect input data
        String title = eventTitle.getText();
        String description = eventDescription.getText();
        String venue = eventVenue.getText();
        String date = eventDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String startTime = eventStartTime.getText();
        String endTime = eventEndTime.getText();

       
        if(!titleValid || !descriptionValid || !venueValid || !dateValid || !timeStartValid || !timeEndValid) {
            if(title.isBlank())
                eventTitleErrorMessage.setText("Event Title should not be empty");
            if(description.isBlank())
                eventDescriptionErrorMessage.setText("Event Description should not be empty");
            if(venue.isBlank())
                eventVenueErrorMessage.setText("Event venue should not be empty");
            if(date.isBlank())
                eventDateErrorMessage.setText("Event date should not be empty");
            if(startTime.isBlank())
                eventStartTimeErrorMessage.setText("Event start time should not be empty");
            if(endTime.isBlank())
                eventEndTimeErrorMessage.setText("Event end time should not be empty");
        }
        if (titleValid && descriptionValid && venueValid && dateValid && timeStartValid && timeEndValid) {
            //store
        }
    }
    
    
    

    private boolean isTimeValid(String time) {
    String pattern = "hh:mm a";
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(pattern);
    try {
        timeFormatter.parse(time);
        return true;
    } catch (DateTimeParseException e) {
        return false;
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