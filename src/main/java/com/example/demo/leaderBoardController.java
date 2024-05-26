package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class leaderBoardController {
    @FXML
    private Stage stage;

    @FXML
    public void leaderBoardStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("leaderBoard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception{
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

    @FXML
    private ListView<String> leaderboardListView;

    private List<Student> students;

    private static final String CSV_FILE = "students.csv";

    @FXML
    public void initialize() {
        students = loadStudentsFromCSV();
        updateLeaderboard();
    }

    private List<Student> loadStudentsFromCSV() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                int points = Integer.parseInt(parts[1]);
                LocalDateTime pointsLastUpdated = LocalDateTime.parse(parts[2]);
                students.add(new Student(username, points, pointsLastUpdated));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private void saveStudentsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Student student : students) {
                bw.write(String.format("%s,%d,%s%n", student.getUsername(), student.getPoints(), student.getPointsLastUpdated().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPointsToStudent(String username, int points) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                student.addPoints(points);
                saveStudentsToCSV();
                updateLeaderboard();
                return;
            }
        }
    }

    private void updateLeaderboard() {
        students.sort(Comparator.comparing(Student::getPoints).reversed().thenComparing(Student::getPointsLastUpdated));
        leaderboardListView.getItems().clear();
        for (Student student : students) {
            leaderboardListView.getItems().add(student.getUsername() + " - " + student.getPoints() + " points");
        }
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