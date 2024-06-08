package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class viewEventController{
    @FXML
    private Stage stage;

    //Live events


    //Upcoming event 1
    @FXML
    private Text liveEventTitle1;

    @FXML
    private Text message;

    @FXML
    private TextArea liveEventDescription1;

    @FXML
    private TextField liveEventTimeStart1;

    @FXML
    private TextField liveEventTimeEnd1;

    @FXML
    private TextField liveEventVenue1;

    @FXML
    private TextField liveEventDate1;

    @FXML
    private ImageView liveCalendar1;

    @FXML
    private ImageView liveTime1;

    @FXML
    private ImageView liveTimeArrow1;

    @FXML
    private ImageView liveLocation1;

    @FXML
    private Button liveRegister1;

    //Live events


    //Upcoming event 2
    @FXML
    private Text liveEventTitle2;


    @FXML
    private TextArea liveEventDescription2;

    @FXML
    private TextField liveEventTimeStart2;

    @FXML
    private TextField liveEventTimeEnd2;

    @FXML
    private TextField liveEventVenue2;

    @FXML
    private TextField liveEventDate2;

    @FXML
    private ImageView liveCalendar2;

    @FXML
    private ImageView liveTime2;

    @FXML
    private ImageView liveTimeArrow2;

    @FXML
    private ImageView liveLocation2;

    @FXML
    private Button liveRegister2;
    //Upcoming event 1

    //Upcoming event 3
    @FXML
    private Text liveEventTitle3;


    @FXML
    private TextArea liveEventDescription3;

    @FXML
    private TextField liveEventTimeStart3;

    @FXML
    private TextField liveEventTimeEnd3;

    @FXML
    private TextField liveEventVenue3;

    @FXML
    private TextField liveEventDate3;

    @FXML
    private ImageView liveCalendar3;

    @FXML
    private ImageView liveTime3;

    @FXML
    private ImageView liveTimeArrow3;

    @FXML
    private ImageView liveLocation3;

    @FXML
    private Button liveRegister3;

    @FXML
    private Text upEventTitle1;

    @FXML
    private TextArea upEventDescription1;

    @FXML
    private TextField upEventTimeStart1;

    @FXML
    private TextField upEventTimeEnd1;

    @FXML
    private TextField upEventVenue1;

    @FXML
    private TextField upEventDate1;

    @FXML
    private Button upEventRegister1;
    //Upcoming event 2
    @FXML
    private Text upEventTitle2;

    @FXML
    private TextArea upEventDescription2;

    @FXML
    private TextField upEventTimeStart2;

    @FXML
    private TextField upEventTimeEnd2;

    @FXML
    private TextField upEventVenue2;

    @FXML
    private TextField upEventDate2;

    @FXML
    private Button upEventRegister2;


    //Upcoming event 3
    @FXML
    private Text upEventTitle3;

    @FXML
    private TextArea upEventDescription3;

    @FXML
    private TextField upEventTimeStart3;

    @FXML
    private TextField upEventTimeEnd3;

    @FXML
    private TextField upEventVenue3;

    @FXML
    private TextField upEventDate3;

    @FXML
    private Button upEventRegister3;

    @FXML
    private VBox live1;

    @FXML
    private VBox live2;

    @FXML
    private VBox live3;

    @FXML
    private VBox event1;

    @FXML
    private VBox event2;

    @FXML
    private VBox event3;

    @FXML
    private VBox viewEvent;


    @FXML
    public void viewEventStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("eventView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);

        initialize();
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

    public List<Event> getEventsSortedByDate() {
        List<Event> events = new ArrayList<>();

        String DB_URL = "jdbc:sqlite:HackingTheFuture.db";
        String selectSQL = "SELECT * FROM events ORDER BY date ASC, start_time ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            // Process the result set
            while (rs.next()) {
                // Retrieve event data from the result set
                String title = rs.getString("title");
                String description = rs.getString("description");
                String venue = rs.getString("venue");
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime startTime = rs.getTime("start_time").toLocalTime();
                LocalTime endTime = rs.getTime("end_time").toLocalTime();

                // Create Event object and add it to the list
                Event event = new Event(title, description, venue, date, startTime, endTime);
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving events: " + e.getMessage());
        }

        return events;
    }

    public boolean hasLiveEvent() throws ClassNotFoundException {
        List<Event> events = getEventsSortedByDate();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        for (Event event : events) {
            if (event.getDate().isEqual(currentDate) && event.getEndTime().isAfter(currentTime)) {
                return true; // Found a live event happening on the current day
            }
        }
        return false;
    }

    public int countLiveEvents() throws ClassNotFoundException {
        List<Event> events = getEventsSortedByDate();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        int liveEventCount = 0;

        for (Event event : events) {
            if (event.getDate().isEqual(currentDate) && event.getEndTime().isAfter(currentTime)) {
                liveEventCount++; // Increment the count for each live event found
                System.out.println(event.getTitle());
            }
        }
        return liveEventCount;
    }

    public List<Event> getUpcomingEvents() throws ClassNotFoundException {
        List<Event> upcomingEvents = new ArrayList<>();
        List<Event> allEvents = getEventsSortedByDate();
        LocalDate currentDate = LocalDate.now();
        int count = 0;

        for (Event event : allEvents) {
            if (!event.getDate().isEqual(currentDate) && event.getDate().isAfter(currentDate)) {
                upcomingEvents.add(event);
                count++;
            }
            if (count == 3) {
                break;
            }
        }

        return upcomingEvents;
    }

    public List<Event> getLiveEvents() throws ClassNotFoundException {
        List<Event> liveEvents = new ArrayList<>();
        List<Event> allEvents = getEventsSortedByDate();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        for (Event event : allEvents) {
            if (event.getDate().isEqual(currentDate) && event.getEndTime().isAfter(currentTime)) {
                liveEvents.add(event);
            }
        }
        return liveEvents;
    }

    public String arrangeLongText(String longText) {
        final int maxLineLength = 130;

        String[] words = longText.split(" ");

        StringBuilder formattedText = new StringBuilder();

        // Temporary variable to hold the current line
        StringBuilder currentLine = new StringBuilder();


        for (String word : words) {
            // Check if adding the next word would exceed the maximum line length
            if (currentLine.length() + word.length() + 1 > maxLineLength) {
                // If yes, append the current line to the formatted text and start a new line
                formattedText.append(currentLine).append("\n");
                currentLine.setLength(0); //recalculate the line character
            }

            // Add a space before the word if the current line is not empty
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }

            // Add the word to the current line
            currentLine.append(word);
        }

        // Append any remaining text in the current line to the formatted text
        if (currentLine.length() > 0) {
            formattedText.append(currentLine);
        }

        return formattedText.toString();
    }


    public void setEventData(List<Event> upcomingEvents) {
        upEventTitle1.setText(upcomingEvents.get(0).getTitle());
        upEventDescription1.setText(arrangeLongText(upcomingEvents.get(0).getDescription()));
        upEventTimeStart1.setText(upcomingEvents.get(0).getStartTime().toString());
        upEventTimeEnd1.setText(upcomingEvents.get(0).getEndTime().toString());
        upEventVenue1.setText(upcomingEvents.get(0).getVenue());
        upEventDate1.setText(upcomingEvents.get(0).getDate().toString());

        upEventTitle2.setText(upcomingEvents.get(1).getTitle());
        upEventDescription2.setText(arrangeLongText(upcomingEvents.get(1).getDescription()));
        upEventTimeStart2.setText(upcomingEvents.get(1).getStartTime().toString());
        upEventTimeEnd2.setText(upcomingEvents.get(1).getEndTime().toString());
        upEventVenue2.setText(upcomingEvents.get(1).getVenue());
        upEventDate2.setText(upcomingEvents.get(1).getDate().toString());

        upEventTitle3.setText(upcomingEvents.get(2).getTitle());
        upEventDescription3.setText(arrangeLongText(upcomingEvents.get(2).getDescription()));
        upEventTimeStart3.setText(upcomingEvents.get(2).getStartTime().toString());
        upEventTimeEnd3.setText(upcomingEvents.get(2).getEndTime().toString());
        upEventVenue3.setText(upcomingEvents.get(2).getVenue());
        upEventDate3.setText(upcomingEvents.get(2).getDate().toString());
    }

    public void initialize() {
        try {
            List<Event> upcomingEvents = getUpcomingEvents();
            List<Event> liveEvents = getLiveEvents();
            setEventData(upcomingEvents);
            LocalTime currentTime = LocalTime.now();
            message.setVisible(false);
            Text[] eventTitle = {liveEventTitle1,liveEventTitle2,liveEventTitle3,upEventTitle1,upEventTitle2,upEventTitle3};
            Button[] registerButton= {liveRegister1,liveRegister2,liveRegister3,upEventRegister1,upEventRegister2,upEventRegister3};
            Text[] liveEvent={liveEventTitle1,liveEventTitle2,liveEventTitle3};
            Button[] liveButton = {liveRegister1,liveRegister2,liveRegister3};

            Platform.runLater(() -> {
                for (int i = 0; i < eventTitle.length; i++) {
                    if (!registerButton[i].getText().equals("Registered")) {
                        try {
                            if (checkRegisterEventClash(loginController.usernameID, eventTitle[i].getText())) {
                                registerButton[i].setText("N/A");
                                registerButton[i].setStyle("-fx-background-color: #E2A6BC;" + "-fx-background-radius: 20;");
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                // Then, update the button for the current event to "Registered"
                for (int i = 0; i < eventTitle.length; i++) {
                    if (isDoubleRegistration(loginController.usernameID,eventTitle[i].getText())) {
                        registerButton[i].setText("Registered");
                        registerButton[i].setStyle("-fx-background-color: #D59BE5;" + "-fx-background-radius: 20;");
                    }
                }

                for(int i=0;i<liveEvent.length;i++){
                    if(!registerButton[i].getText().equals("Registered")){
                        if (!registerButton[i].getText().equals("Closed")) {
                            for (Event event : liveEvents) {
                                if(liveEvent[i].getText().equals(event.getTitle())){
                                    if(event.getStartTime().isBefore(currentTime)){
                                        liveButton[i].setText("Closed");
                                        liveButton[i].setStyle("-fx-background-color: #FF808B;"+"-fx-background-radius: 20;");
                                    }
                                }
                            }
                        }
                    }
                }
            });




            if(!hasLiveEvent()){
                System.out.println("Live events=0");
                liveEventTitle1.setVisible(false);
                liveEventDescription1.setVisible(false);
                liveEventTimeStart1.setVisible(false);
                liveEventTimeEnd1.setVisible(false);
                liveEventVenue1.setVisible(false);
                liveEventDate1.setVisible(false);
                liveCalendar1.setVisible(false);
                liveTime1.setVisible(false);
                liveTimeArrow1.setVisible(false);
                liveLocation1.setVisible(false);
                liveRegister1.setVisible(false);
                message.setVisible(true);
                live2.setVisible(false);
                live3.setVisible(false);
                live2.setManaged(false);  //layout
                live3.setManaged(false);  //layout


                double height = 1585;


                viewEvent.setPrefHeight(height);

            }else if (countLiveEvents()==2){
                System.out.println("Live events=2");
                liveEventTitle1.setVisible(true);
                liveEventDescription1.setVisible(true);
                liveEventTimeStart1.setVisible(true);
                liveEventTimeEnd1.setVisible(true);
                liveEventVenue1.setVisible(true);
                liveEventDate1.setVisible(true);
                liveCalendar1.setVisible(true);
                liveTime1.setVisible(true);
                liveTimeArrow1.setVisible(true);
                liveLocation1.setVisible(true);
                liveRegister1.setVisible(true);
                message.setVisible(false);
                live2.setVisible(true);
                live3.setVisible(false);
                live3.setManaged(false);  //layout

                //live event set text
                liveEventTitle1.setText(liveEvents.get(0).getTitle());
                liveEventDate1.setText(liveEvents.get(0).getTitle());
                liveEventDescription1.setText(arrangeLongText(liveEvents.get(0).getDescription()));
                liveEventTimeStart1.setText(liveEvents.get(0).getStartTime().toString());
                liveEventTimeEnd1.setText(liveEvents.get(0).getEndTime().toString());
                liveEventVenue1.setText(liveEvents.get(0).getVenue());
                liveEventDate1.setText(liveEvents.get(0).getDate().toString());

                liveEventTitle2.setText(liveEvents.get(1).getTitle());
                liveEventDate2.setText(liveEvents.get(1).getTitle());
                liveEventDescription2.setText(arrangeLongText(liveEvents.get(1).getDescription()));
                liveEventTimeStart2.setText(liveEvents.get(1).getStartTime().toString());
                liveEventTimeEnd2.setText(liveEvents.get(1).getEndTime().toString());
                liveEventVenue2.setText(liveEvents.get(1).getVenue());
                liveEventDate2.setText(liveEvents.get(1).getDate().toString());

                double height = 1795;
                viewEvent.setPrefHeight(height);


            }else if (countLiveEvents()==3){
                System.out.println("Live events=3");
                liveEventTitle1.setVisible(true);
                liveEventDescription1.setVisible(true);
                liveEventTimeStart1.setVisible(true);
                liveEventTimeEnd1.setVisible(true);
                liveEventVenue1.setVisible(true);
                liveEventDate1.setVisible(true);
                liveCalendar1.setVisible(true);
                liveTime1.setVisible(true);
                liveTimeArrow1.setVisible(true);
                liveLocation1.setVisible(true);
                liveRegister1.setVisible(true);
                message.setVisible(false);
                live2.setVisible(true);
                live3.setVisible(true);

                //live event set text
                liveEventTitle1.setText(liveEvents.get(0).getTitle());
                liveEventDate1.setText(liveEvents.get(0).getTitle());
                liveEventDescription1.setText(arrangeLongText(liveEvents.get(0).getDescription()));
                liveEventTimeStart1.setText(liveEvents.get(0).getStartTime().toString());
                liveEventTimeEnd1.setText(liveEvents.get(0).getEndTime().toString());
                liveEventVenue1.setText(liveEvents.get(0).getVenue());
                liveEventDate1.setText(liveEvents.get(0).getDate().toString());

                liveEventTitle2.setText(liveEvents.get(1).getTitle());
                liveEventDate2.setText(liveEvents.get(1).getTitle());
                liveEventDescription2.setText(arrangeLongText(liveEvents.get(1).getDescription()));
                liveEventTimeStart2.setText(liveEvents.get(1).getStartTime().toString());
                liveEventTimeEnd2.setText(liveEvents.get(1).getEndTime().toString());
                liveEventVenue2.setText(liveEvents.get(1).getVenue());
                liveEventDate2.setText(liveEvents.get(1).getDate().toString());

                liveEventTitle3.setText(liveEvents.get(2).getTitle());
                liveEventDate3.setText(liveEvents.get(2).getTitle());
                liveEventDescription3.setText(arrangeLongText(liveEvents.get(2).getDescription()));
                liveEventTimeStart3.setText(liveEvents.get(2).getStartTime().toString());
                liveEventTimeEnd3.setText(liveEvents.get(2).getEndTime().toString());
                liveEventVenue3.setText(liveEvents.get(2).getVenue());
                liveEventDate3.setText(liveEvents.get(2).getDate().toString());

                double height = 2035;
                viewEvent.setPrefHeight(height);

            }else if (countLiveEvents()==1){
                System.out.println("Live events=1");
                liveEventTitle1.setVisible(true);
                liveEventDescription1.setVisible(true);
                liveEventTimeStart1.setVisible(true);
                liveEventTimeEnd1.setVisible(true);
                liveEventVenue1.setVisible(true);
                liveEventDate1.setVisible(true);
                liveCalendar1.setVisible(true);
                liveTime1.setVisible(true);
                liveTimeArrow1.setVisible(true);
                liveLocation1.setVisible(true);
                liveRegister1.setVisible(true);
                message.setVisible(false);
                live2.setVisible(false);
                live3.setVisible(false);
                live2.setManaged(false);  //layout
                live3.setManaged(false);  //layout

                //live event set text
                liveEventTitle1.setText(liveEvents.get(0).getTitle());
                liveEventDate1.setText(liveEvents.get(0).getTitle());
                liveEventDescription1.setText(arrangeLongText(liveEvents.get(0).getDescription()));
                liveEventTimeStart1.setText(liveEvents.get(0).getStartTime().toString());
                liveEventTimeEnd1.setText(liveEvents.get(0).getEndTime().toString());
                liveEventVenue1.setText(liveEvents.get(0).getVenue());
                liveEventDate1.setText(liveEvents.get(0).getDate().toString());

                double height = 1585;
                viewEvent.setPrefHeight(height);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showRegisterEventSuccess(String eventName) {
        Alert alertSU = new Alert(Alert.AlertType.INFORMATION);
        alertSU.setTitle("Successful");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Successfully register event "+eventName);

        alertSU.showAndWait();

    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }


    int points;
    public void registerConfirmation(String event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to register for event " + event + "?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        String usernamelogin = loginController.usernameID;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        if (result.isPresent() && result.get() == buttonTypeYes) {
            Text[] eventTitle = {liveEventTitle1, liveEventTitle2, liveEventTitle3, upEventTitle1, upEventTitle2, upEventTitle3};
            Button[] registerButton = {liveRegister1, liveRegister2, liveRegister3, upEventRegister1, upEventRegister2, upEventRegister3};

            Platform.runLater(() -> {
                for (int i = 0; i < eventTitle.length; i++) {
                    if (!registerButton[i].getText().equals("Registered")) {
                        if (!registerButton[i].getText().equals("Closed")) {
                            try {
                                if (checkRegisterEventClash(loginController.usernameID, eventTitle[i].getText())) {
                                    registerButton[i].setText("N/A");
                                    registerButton[i].setStyle("-fx-background-color: #E2A6BC;" + "-fx-background-radius: 20;");
                                }
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }

                // Then, update the button for the current event to "Registered"
                for (int i = 0; i < eventTitle.length; i++) {
                    if (eventTitle[i].getText().equals(event)) {
                        registerButton[i].setText("Registered");
                        registerButton[i].setStyle("-fx-background-color: #D59BE5;" + "-fx-background-radius: 20;");
                    }
                }
            });

            try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/Data/registerEvent.csv", true))) {
                try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] userData = line.split(",");
                        String usernameSearch = userData[0];
                        if (usernameSearch.equals(loginController.usernameID)) {
                            // Update the numEventCreated value for the current user
                            points = Integer.parseInt(userData[6].trim()) + 5;
                        }
                    }
                } catch (IOException e) {
                    showError("Error reading user data from file: " + e.getMessage());
                }
                updatePointsUserCSV();
                writer.println(usernamelogin + "," + event + "," + formattedDateTime + "," + points);

                writer.flush();
                showRegisterEventSuccess(event);

            } catch (IOException e) {
                showError("Registration Failed: " + e.getMessage());
            }
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            showError("Registration Cancelled");
        }else {
            showError("Registration Cancelled");
        }
    }
    public void registerEvent(ActionEvent event) throws Exception {
        Button clickedButton = (Button) event.getSource();
        List<Event> upcomingEvents = getUpcomingEvents();
        List<Event> liveEvents = getLiveEvents();
        LocalTime currentTime = LocalTime.now();
        if (!isYoungStudent()){
            showError("Only Young Students are allowed to register events.");
        }else{
            if (clickedButton == liveRegister1) {
                if (liveEvents.get(0).getStartTime().isBefore(currentTime)) {
                    showError("The event is in progress; you're not allowed to register now.");
                } else if (isDoubleRegistration(loginController.usernameID, liveEvents.get(0).getTitle())) {
                    showError("You have already registered '" + liveEvents.get(0).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,liveEvents.get(0).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else{
                    registerConfirmation(liveEvents.get(0).getTitle());
                }
            }
            if (clickedButton == liveRegister2) {
                if (liveEvents.get(1).getStartTime().isBefore(currentTime)) {
                    showError("The event is in progress; you're not allowed to register now.");
                }else if(isDoubleRegistration(loginController.usernameID,liveEvents.get(1).getTitle())) {
                    showError("You have already registered '" + liveEvents.get(1).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,liveEvents.get(1).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else{
                    registerConfirmation(liveEvents.get(1).getTitle());
                }
            }
            if (clickedButton == liveRegister3) {
                if (liveEvents.get(2).getStartTime().isBefore(currentTime)) {
                    showError("The event is in progress; you're not allowed to register now.");
                }else if(isDoubleRegistration(loginController.usernameID,liveEvents.get(2).getTitle())) {
                    showError("You have already registered '" + liveEvents.get(2).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,liveEvents.get(2).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else{
                    registerConfirmation(liveEvents.get(2).getTitle());
                }
            }
            if (clickedButton == upEventRegister1) {
                if(isDoubleRegistration(loginController.usernameID,upcomingEvents.get(0).getTitle())) {
                    showError("You have already registered '" + upcomingEvents.get(0).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,upcomingEvents.get(0).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else {
                    registerConfirmation(upcomingEvents.get(0).getTitle());
                }
            }
            if (clickedButton == upEventRegister2) {
                if(isDoubleRegistration(loginController.usernameID,upcomingEvents.get(1).getTitle())) {
                    showError("You have already registered '" + upcomingEvents.get(1).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,upcomingEvents.get(1).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else{
                    registerConfirmation(upcomingEvents.get(1).getTitle());
                }
            }
            if (clickedButton == upEventRegister3) {
                if(isDoubleRegistration(loginController.usernameID,upcomingEvents.get(2).getTitle())) {
                    showError("You have already registered '" + upcomingEvents.get(2).getTitle() + "'");
                }else if (checkRegisterEventClash(loginController.usernameID,upcomingEvents.get(2).getTitle())){
                    showError("You are not allowed to register the event because event clashes with another event registered.");
                }else{
                    registerConfirmation(upcomingEvents.get(2).getTitle());
                }
            }
        }

    }

    public boolean isDoubleRegistration(String username, String event) {
        // Path to the CSV file
        String csvFile = "src/main/java/Data/registerEvent.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");


                if (data[0].equals(username) && data[1].equals(event)) {
                    // Double registration found
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkRegisterEventClash(String username, String eventToRegister) throws ClassNotFoundException {
        String File = "src/main/java/Data/registerEvent.csv";
        String line;
        LocalDate currentDate = LocalDate.now();
        List<Event> events = getEventsSortedByDate();

        try (BufferedReader br = new BufferedReader(new FileReader(File))) {
            //Store all the registered event first in array list
            List<String[]> userEvents = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    userEvents.add(data); // Add whole row
                }
            }

            for (String[] eventData : userEvents) {
                String registeredEvent = eventData[1];
                if (isEventClash(registeredEvent, eventToRegister)) {
                    return true; // Clash detected
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // No clash found
        return false;
    }

    private boolean isEventClash(String registeredEvent, String eventToRegister) throws ClassNotFoundException {
        List<Event> events = getEventsSortedByDate();

        LocalTime registeredEventStartTime = null;
        LocalTime registeredEventEndTime = null;
        LocalDate registeredEventDate = null;
        for (Event event : events) {
            if (event.getTitle().equals(registeredEvent)) {
                registeredEventStartTime = event.getStartTime();
                registeredEventEndTime = event.getEndTime();
                registeredEventDate = event.getDate();
                break;
            }
        }

        // Find the start and end times of the event to register
        LocalTime eventToRegisterStartTime = null;
        LocalTime eventToRegisterEndTime = null;
        LocalDate eventToRegisterDate = null;
        for (Event event : events) {
            if (event.getTitle().equals(eventToRegister)) {
                eventToRegisterStartTime = event.getStartTime();
                eventToRegisterEndTime = event.getEndTime();
                eventToRegisterDate = event.getDate();
                break;
            }
        }

        // Check if the events occur on the same date
        if (!registeredEventDate.equals(eventToRegisterDate)) {
            return false;
        }

        // Check for time clashes
        if ((eventToRegisterStartTime.isAfter(registeredEventStartTime) && eventToRegisterStartTime.isBefore(registeredEventEndTime)) ||
                (eventToRegisterEndTime.isAfter(registeredEventStartTime) && eventToRegisterEndTime.isBefore(registeredEventEndTime)) ||
                eventToRegisterStartTime.equals(registeredEventStartTime) || eventToRegisterEndTime.equals(registeredEventEndTime) ||
                (eventToRegisterStartTime.isBefore(registeredEventStartTime) && eventToRegisterEndTime.isAfter(registeredEventEndTime))) {
            return true; // Clash found
        }
        return false; // No clash found
    }

    public boolean isYoungStudent(){
        if(homeController.roleCon.equals("Young Student")){
            return true;
        }
        return false;
    }

    public void updatePointsUserCSV() {
        String fileName = "src/main/java/Data/user.csv";
        String username = loginController.usernameID;
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<String> lines = new ArrayList<>();

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String usernameSearch = userData[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                if (usernameSearch.equals(username)) {
                    // Update the numEventCreated value for the current user
                    userData[6] = String.valueOf(points);
                    userData[7] = formattedDateTime;
                    // Reconstruct the line with updated data
                    line = String.join(",", userData);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            showError("Error reading user data from file: " + e.getMessage());
            return;
        }

        //rewrite
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String updatedLine : lines) {
                writer.println(updatedLine);
            }
        } catch (IOException e) {
            showError("Error writing updated data to file: " + e.getMessage());
        }
    }
}