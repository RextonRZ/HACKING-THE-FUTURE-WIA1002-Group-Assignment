package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class leaderBoardController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private Text user1;
    @FXML
    private Text user2;
    @FXML
    private Text user3;
    @FXML
    private Text user4;
    @FXML
    private Text user5;
    @FXML
    private Text user6;
    @FXML
    private Text user7;
    @FXML
    private Text user8;
    @FXML
    private Text user9;

    @FXML
    private Text point1;
    @FXML
    private Text point2;
    @FXML
    private Text point3;
    @FXML
    private Text point4;
    @FXML
    private Text point5;
    @FXML
    private Text point6;
    @FXML
    private Text point7;
    @FXML
    private Text point8;
    @FXML
    private Text point9;

    private Text[] user;
    private Text[] point;




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

    private ListView<String> leaderboardListView;

    private List<Student> students;

    private ArrayList<String[]> leaderBoard;

    private static final String CSV_FILE = "students.csv";

    String line, usernameSet, roleSet, pointSet, pointLastUpdate;
    String[] data;
    private void loadStudentsFromCSV() {
        String fileName = "src/main/java/Data/user.csv";

        leaderBoard = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                roleSet = userData[3].trim();

                    if (roleSet.equals("Young Student")){
                        usernameSet = userData[0].trim();
                        pointSet= userData[6].trim();
                        pointLastUpdate= userData[7].trim();

                        data = new String[]{usernameSet, pointSet, pointLastUpdate};
                        leaderBoard.add(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private void updateLeaderboard() {
        loadStudentsFromCSV();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        leaderBoard.sort(Comparator.<String[]>comparingInt(a -> Integer.parseInt(a[1])).reversed()
                .thenComparing(Comparator.comparing(a -> LocalDateTime.parse(a[2], formatter))));

        // Displaying the sorted leaderboard
        for (int i = 0; i < Math.min(leaderBoard.size(), 9); i++) {
            String[] data = leaderBoard.get(i);
            user[i].setText(data[0]);
            point[i].setText(data[1]);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new Text[]{user1,user2,user3,user4,user5,user6,user7,user8,user9};
        point = new Text[]{point1,point2,point3,point4,point5,point6,point7,point8,point9};
        updateLeaderboard();
    }

}