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

    @FXML
    public void initialize() {
    }

    String line, usernameSet, roleSet, pointSet;
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

                        data = new String[]{usernameSet, pointSet};
                        leaderBoard.add(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private void updateLeaderboard() {
        loadStudentsFromCSV();

        leaderBoard.sort(Comparator.comparingInt(a -> Integer.parseInt(a[1])));
        Collections.reverse(leaderBoard);

        // Example: Displaying the sorted leaderboard
        for (int i = 0; i < Math.min(leaderBoard.size(), 9); i++) {
            String[] data = leaderBoard.get(i);
            switch (i) {
                case 0:
                    user1.setText(data[0]);
                    point1.setText(data[1]);
                    break;
                case 1:
                    user2.setText(data[0]);
                    point2.setText(data[1]);
                    break;
                case 2:
                    user3.setText(data[0]);
                    point3.setText(data[1]);
                    break;
                case 3:
                    user4.setText(data[0]);
                    point4.setText(data[1]);
                    break;
                case 4:
                    user5.setText(data[0]);
                    point5.setText(data[1]);
                    break;
                case 5:
                    user6.setText(data[0]);
                    point6.setText(data[1]);
                    break;
                case 6:
                    user7.setText(data[0]);
                    point7.setText(data[1]);
                    break;
                case 7:
                    user8.setText(data[0]);
                    point8.setText(data[1]);
                    break;
                case 8:
                    user9.setText(data[0]);
                    point9.setText(data[1]);
                    break;
            }

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
        updateLeaderboard();
    }
}