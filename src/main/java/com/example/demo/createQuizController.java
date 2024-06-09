package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class createQuizController {
    private Stage stage;

    @FXML
    private TextField quizContent;

    @FXML
    private Text quizContentErrorMessage;

    @FXML
    private TextField quizDescription;

    @FXML
    private Text quizDescriptionErrorMessage;

    @FXML
    private SplitMenuButton quizTheme;

    @FXML
    private Text quizThemeErrorMessage;

    @FXML
    private TextField quizTitle;

    @FXML
    private Text quizTitleErrorMessage;

    private boolean titleValid = false, descriptionValid = false, themeValid = false, contentValid = false;
    private String title, description, theme, content;

    public int quizCreated;
    @FXML
    public void initialize() {
        MenuItem science = new MenuItem("Science");
        MenuItem technology = new MenuItem("Technology");
        MenuItem engineering = new MenuItem("Engineering");
        MenuItem mathematics = new MenuItem("Mathematics");

        quizTheme.getItems().addAll(science, technology, engineering, mathematics);

        science.setOnAction(event -> quizTheme.setText(science.getText()));
        technology.setOnAction(event -> quizTheme.setText(technology.getText()));
        engineering.setOnAction(event -> quizTheme.setText(engineering.getText()));
        mathematics.setOnAction(event -> quizTheme.setText(mathematics.getText()));
    }

    @FXML
    public void createQuizStartUp(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("quizCreate.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void restartQuizCreate() throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("quizCreate.fxml"));

        // Get the current stage
        Stage stage = (Stage) quizTitle.getScene().getWindow(); // Assuming eventTitle is part of the old scene

        // Set the scene to the new content, maintaining the previous stage size
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void quizTitleValidation() {
        title = quizTitle.getText();
        String[] words = title.split("\\s+");
        if (words.length > 20) {
            quizTitleErrorMessage.setText("Quiz Title should not have more than 20 words");
            titleValid = false;
        } else if (title.isBlank()) {
            quizTitleErrorMessage.setText("Quiz Title should not be empty");
            titleValid = false;
        } else {
            quizTitleErrorMessage.setText("");
            titleValid = true;
        }
    }

    @FXML
    public void quizDescriptionValidation() {
        description = quizDescription.getText();
        String[] words = description.split("\\s+");

        if (words.length > 50) {
            quizDescriptionErrorMessage.setText("Quiz description should not contain more than 50 words");
            descriptionValid = false;
        } else if (description.isEmpty()) {
            quizDescriptionErrorMessage.setText("Quiz description should not be empty");
            descriptionValid = false;
        } else {
            quizDescriptionErrorMessage.setText("");
            descriptionValid = true;
        }
    }

    @FXML
    public void quizThemeValidation() {
        theme = quizTheme.getText();
        if (theme == null || theme.isEmpty() || theme.equals("Select Theme")) {
            quizThemeErrorMessage.setText("Please select a quiz theme !");
            themeValid = false;
        } else {
            quizThemeErrorMessage.setText("");
            themeValid = true;
        }
    }

    @FXML
    public void quizContentValidation() {
        content = quizContent.getText();

        if (content.isEmpty()) {
            quizContentErrorMessage.setText("Please enter a Quiziz URL !");
            contentValid = false;
        } else if (!isValidURL(content)) {
            quizContentErrorMessage.setText("Please enter a valid Quiziz URL !");
            contentValid = false;
        } else {
            quizContentErrorMessage.setText("");
            contentValid = true;
        }
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    @FXML
    public void createQuiz(ActionEvent event) throws Exception {
        quizTitleValidation();
        quizDescriptionValidation();
        quizThemeValidation();
        quizContentValidation();

        if (!titleValid || !descriptionValid || !themeValid || !contentValid) {
            showError("Please correct all fields.");
            if (title.isBlank()) quizTitleErrorMessage.setText("Quiz Title should not be empty");
            if (description.isBlank()) quizDescriptionErrorMessage.setText("Quiz Description should not be empty");
            if (theme.isBlank()) quizThemeErrorMessage.setText("Quiz Theme should not be empty");
            if (content.isBlank()) quizContentErrorMessage.setText("Please enter a Quiziz URL !");
        } else {
            addQuizToDatabase(title, description, theme, content);
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


    private void showCreateQuizSuccess() {
        Alert alertSU = new Alert(Alert.AlertType.INFORMATION);
        alertSU.setTitle("Success");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Successfully created Quiz '" + title +"'");
        alertSU.showAndWait();

        try {
            restartQuizCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        alertError.showAndWait();
    }

    public void addQuizToDatabase(String quizTitle, String quizDescription, String quizTheme, String quizContent) throws SQLException {
        String DB_URL = "jdbc:sqlite:HackingTheFuture.db";
        String insertSQL = "INSERT INTO quiz (title, description, theme, content) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, quizTitle);
            pstmt.setString(2, quizDescription);
            pstmt.setString(3, quizTheme);
            pstmt.setString(4, quizContent);

            pstmt.executeUpdate();

            showCreateQuizSuccess();

            String fileName = "src/main/java/Data/user.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    String usernameSearch = userData[0];
                    if (usernameSearch.equals(loginController.usernameID)) {
                        // Update the numEventCreated value for the current user
                        quizCreated = Integer.parseInt(userData[7].trim());
                    }
                }
            } catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
                return;
            }
            quizCreated++;
            updateQuizCreatedCSV();
        } catch (SQLException e) {
            if (e.getMessage().contains("no such table: quiz")) {
                Load load = new Load();
                load.createTables();
                showError(e.getMessage());
            } else {
                showError("Error storing new quiz data: " + e.getMessage());
            }
        }
    }

    public void updateQuizCreatedCSV() {
        String fileName = "src/main/java/Data/user.csv";
        String username = loginController.usernameID;

        List<String> lines = new ArrayList<>();

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String usernameSearch = userData[0];
                if (usernameSearch.equals(username)) {
                    // Update the numEventCreated value for the current user
                    userData[7] = String.valueOf(quizCreated);
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