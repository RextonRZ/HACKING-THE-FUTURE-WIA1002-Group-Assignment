package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class viewEventController {
    @FXML
    private Stage stage;

    //Upcoming event 1
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
    public void viewEventStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("eventView.fxml"));
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

    public List<Event> getEventsSortedByDate() throws ClassNotFoundException {
        List<Event> events = new ArrayList<>();

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String DB_URL = "jdbc:mysql://localhost:3306/eventhtf";
        String USERNAME = "root";
        String PASSWORD = "HackingTheFuture!5"; // Replace with your actual password

        String selectSQL = "SELECT * FROM events ORDER BY date ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
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

    public boolean checkLiveEvent() throws ClassNotFoundException {
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

    public List<Event> getUpcomingEvents() throws ClassNotFoundException {
        List<Event> upcomingEvents = new ArrayList<>();
        List<Event> allEvents = getEventsSortedByDate();
        LocalDate currentDate = LocalDate.now();
        int count = 0;

        for (Event event : allEvents) {
            if (!event.getDate().isEqual(currentDate) ) {
                upcomingEvents.add(event);
                count++;
            }
            if (count == 3) {
                break;
            }
        }

        return upcomingEvents;
    }

    public void setEventData(List<Event> upcomingEvents) {
        upEventTitle1.setText(upcomingEvents.get(0).getTitle());
        upEventDescription1.setText(upcomingEvents.get(0).getDescription());
        upEventTimeStart1.setText(upcomingEvents.get(0).getStartTime().toString());
        upEventTimeEnd1.setText(upcomingEvents.get(0).getEndTime().toString());
        upEventVenue1.setText(upcomingEvents.get(0).getVenue());
        upEventDate1.setText(upcomingEvents.get(0).getDate().toString());

        upEventTitle2.setText(upcomingEvents.get(1).getTitle());
        upEventDescription2.setText(upcomingEvents.get(1).getDescription());
        upEventTimeStart2.setText(upcomingEvents.get(1).getStartTime().toString());
        upEventTimeEnd2.setText(upcomingEvents.get(1).getEndTime().toString());
        upEventVenue2.setText(upcomingEvents.get(1).getVenue());
        upEventDate2.setText(upcomingEvents.get(1).getDate().toString());

        upEventTitle3.setText(upcomingEvents.get(2).getTitle());
        upEventDescription3.setText(upcomingEvents.get(2).getDescription());
        upEventTimeStart3.setText(upcomingEvents.get(2).getStartTime().toString());
        upEventTimeEnd3.setText(upcomingEvents.get(2).getEndTime().toString());
        upEventVenue3.setText(upcomingEvents.get(2).getVenue());
        upEventDate3.setText(upcomingEvents.get(0).getDate().toString());
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Event> upcomingEvents = getUpcomingEvents();
            setEventData(upcomingEvents);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }


}