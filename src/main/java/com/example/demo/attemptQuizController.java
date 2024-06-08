package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class attemptQuizController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox quizzesContainer;
    //quizview
    @FXML
    private Text quizTitle1, quizTitle2, quizTitle3, quizTitle4, quizTitle5, quizTitle6,
            quizTitle7, quizTitle8, quizTitle9, quizTitle10,
            quizTitle11, quizTitle12, quizTitle13, quizTitle14, quizTitle15;

    @FXML
    private TextArea quizDescription1, quizDescription2, quizDescription3, quizDescription4, quizDescription5,
            quizDescription6, quizDescription7, quizDescription8, quizDescription9, quizDescription10,
            quizDescription11, quizDescription12, quizDescription13, quizDescription14, quizDescription15;

    @FXML
    private TextField quizTheme1, quizTheme2, quizTheme3, quizTheme4, quizTheme5,
            quizTheme6, quizTheme7, quizTheme8, quizTheme9, quizTheme10,
            quizTheme11, quizTheme12, quizTheme13, quizTheme14, quizTheme15;

    @FXML
    private Button quizAttempt1, quizAttempt2, quizAttempt3, quizAttempt4, quizAttempt5,
            quizAttempt6, quizAttempt7, quizAttempt8, quizAttempt9, quizAttempt10,
            quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15;

    @FXML
    private VBox quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8, quiz9, quiz10, quiz11, quiz12,
            quiz13, quiz14, quiz15;

    @FXML
    private CheckBox Science, Technology, Engineering, Mathematics;

    int pointsQuiz ;

    List<Quiz> selectedQuiz = new ArrayList<>();


    @FXML
    public void attemptQuizStartUp(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("quizAttempt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void quizButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.quizButton(event);
    }

    public void eventButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.eventButton(event);
    }

    public void bookingButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.bookingButton(event);
    }

    public void leaderBoardButton(ActionEvent event) throws Exception {
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.profileButton(event);
    }

    public void logOutButton(ActionEvent event) throws Exception {
        homeController homeController = new homeController();
        homeController.logOutButton(event);

    }

    private static final String DB_URL = "jdbc:sqlite:HackingTheFuture.db";

    public List<Quiz> getAllQuizDetails() {
        List<Quiz> quizzes = new ArrayList<>();

        String selectSQL = "SELECT * FROM quiz";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            // Process the result set
            while (rs.next()) {
                // Retrieve quiz data from the result set
                String title = rs.getString("title");
                String description = rs.getString("description");
                String theme = rs.getString("theme");
                String link = rs.getString("content");

                // Create Quiz object and add it to the list
                Quiz quiz = new Quiz(title, description, theme, link);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving quizzes: " + e.getMessage());
        }

        return quizzes;
    }

    public List<Quiz> getQuizByTheme(String theme) {
        List<Quiz> quizzes = new ArrayList<>();
        String selectSQL = "SELECT * FROM quiz WHERE theme = '" + theme + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            // Process the result set
            while (rs.next()) {
                // Retrieve quiz data from the result set
                String title = rs.getString("title");
                String description = rs.getString("description");
                String link = rs.getString("content");

                // Create Quiz object and add it to the list
                Quiz quiz = new Quiz(title, description, theme, link);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving quizzes: " + e.getMessage());
        }

        return quizzes;
    }

    public List<Quiz> getQuizScienceOnly() {
        return getQuizByTheme("Science");
    }

    public List<Quiz> getQuizTechnologyOnly() {
        return getQuizByTheme("Technology");
    }

    public List<Quiz> getQuizEngineeringOnly() {
        return getQuizByTheme("Engineering");
    }

    public List<Quiz> getQuizMathematicsOnly() {
        return getQuizByTheme("Mathematics");
    }


    private void checkThemeSelected() {
        Text[] quizTitle = {
                quizTitle1, quizTitle2, quizTitle3, quizTitle4, quizTitle5, quizTitle6,
                quizTitle7, quizTitle8, quizTitle9, quizTitle10, quizTitle11, quizTitle12,
                quizTitle13, quizTitle14, quizTitle15
        };
        TextArea[] quizDescription = {
                quizDescription1, quizDescription2, quizDescription3, quizDescription4, quizDescription5,
                quizDescription6, quizDescription7, quizDescription8, quizDescription9, quizDescription10,
                quizDescription11, quizDescription12, quizDescription13, quizDescription14, quizDescription15
        };
        TextField[] quizTheme = {
                quizTheme1, quizTheme2, quizTheme3, quizTheme4, quizTheme5,
                quizTheme6, quizTheme7, quizTheme8, quizTheme9, quizTheme10,
                quizTheme11, quizTheme12, quizTheme13, quizTheme14, quizTheme15
        };
        VBox[] frame = {
                quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8, quiz9, quiz10, quiz11, quiz12,
                quiz13, quiz14, quiz15
        };
        Button[] quizLink = {quizAttempt1, quizAttempt2, quizAttempt3,quizAttempt4, quizAttempt5, quizAttempt6, quizAttempt7,
                quizAttempt8, quizAttempt9, quizAttempt10, quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15
        };
        Platform.runLater(() -> {
            for (int i = 0; i < selectedQuiz.size(); i++) {
                if (isDoubleAttemptQuiz(loginController.usernameID,quizTitle[i].getText())) {
                    quizLink[i].setText("Attempted");
                }else{
                    quizLink[i].setText("Attempt");
                }
            }
                    for(int i=0;i<selectedQuiz.size();i++) {
                        if (quizTheme[i].getText().equals("Science")) {
                            frame[i].setStyle("-fx-background-color: #E9D7FF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #C193FF;" + "-fx-background-radius: 100;");
                        }
                        if (quizTheme[i].getText().equals("Technology")) {
                            frame[i].setStyle("-fx-background-color: #DEEAFF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #B3CFFF;" + "-fx-background-radius: 100;");
                        }
                        if (quizTheme[i].getText().equals("Engineering")) {
                            frame[i].setStyle("-fx-background-color: #E8F8FF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #C3EEFF;" + "-fx-background-radius: 100;");
                        }

                        if (quizTheme[i].getText().equals("Mathematics")) {
                            frame[i].setStyle("-fx-background-color: #E4FFF3;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #9AFFD2;" + "-fx-background-radius: 100;");
                        }
                    }
        });
        selectedQuiz.clear();

        if (Science.isSelected()) {
            selectedQuiz.addAll(getQuizScienceOnly());
        }

        if (Technology.isSelected()) {
            selectedQuiz.addAll(getQuizTechnologyOnly());
        }

        if (Engineering.isSelected()) {
            selectedQuiz.addAll(getQuizEngineeringOnly());
        }

        if (Mathematics.isSelected()) {
            selectedQuiz.addAll(getQuizMathematicsOnly());
        }

        // Hide all frames initially
        for (VBox box : frame) {
            box.setVisible(false);
            box.setManaged(false);
        }

        // Display quizzes
        int numQuizzes = selectedQuiz.size();
        for (int i = 0; i < numQuizzes; i++) {
            quizTitle[i].setText(selectedQuiz.get(i).getTitle());
            quizDescription[i].setText(arrangeLongText(selectedQuiz.get(i).getDescription()));
            quizTheme[i].setText(selectedQuiz.get(i).getTheme());
            frame[i].setVisible(true);
            frame[i].setManaged(true);
            quizLink[i].setOnAction(event -> attemptQuiz(event));
        }

        for (int y = selectedQuiz.size(); y < 15; y++) {
            frame[y].setVisible(false);
        }


        double height = 600 + numQuizzes * 218; // Base height + height per quiz
        double extraSpace = 185; // Adjust as needed
        if (numQuizzes <= 2) {
            height = 681; // Minimum height when there are no or only one quiz

        } else if (numQuizzes == 3) {
            height = 907;
        }
        height += extraSpace; // Add extra space

        quizzesContainer.setPrefHeight(height);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox[] frame = {
                quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8, quiz9, quiz10, quiz11, quiz12,
                quiz13, quiz14, quiz15
        };
        TextField[] quizTheme = {
                quizTheme1, quizTheme2, quizTheme3, quizTheme4, quizTheme5,
                quizTheme6, quizTheme7, quizTheme8, quizTheme9, quizTheme10,
                quizTheme11, quizTheme12, quizTheme13, quizTheme14, quizTheme15
        };
        Text[] quizTitle = {
                quizTitle1, quizTitle2, quizTitle3, quizTitle4, quizTitle5, quizTitle6,
                quizTitle7, quizTitle8, quizTitle9, quizTitle10, quizTitle11, quizTitle12,
                quizTitle13, quizTitle14, quizTitle15
        };
        Button[] attemptButton = {quizAttempt1, quizAttempt2, quizAttempt3,quizAttempt4, quizAttempt5, quizAttempt6, quizAttempt7,
                quizAttempt8, quizAttempt9, quizAttempt10, quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15
        };

        Science.setSelected(true);
        Technology.setSelected(true);
        Engineering.setSelected(true);
        Mathematics.setSelected(true);

        Science.setOnAction(event -> checkThemeSelected());
        Technology.setOnAction(event -> checkThemeSelected());
        Engineering.setOnAction(event -> checkThemeSelected());
        Mathematics.setOnAction(event -> checkThemeSelected());

        checkThemeSelected();
        Platform.runLater(() -> {
                    for (int i = 0; i < selectedQuiz.size(); i++) {
                        if (isDoubleAttemptQuiz(loginController.usernameID,quizTitle[i].getText())) {
                            attemptButton[i].setText("Attempted");
                        }else{
                            attemptButton[i].setText("Attempt");
                        }
                    }

                    for(int i=0;i<selectedQuiz.size();i++){
                        if (quizTheme[i].getText().equals("Science")) {
                            frame[i].setStyle("-fx-background-color: #E9D7FF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #C193FF;" + "-fx-background-radius: 100;");
                        }
                        if (quizTheme[i].getText().equals("Technology")) {
                            frame[i].setStyle("-fx-background-color: #DEEAFF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #B3CFFF;" + "-fx-background-radius: 100;");
                        }
                        if (quizTheme[i].getText().equals("Engineering")) {
                            frame[i].setStyle("-fx-background-color: #E8F8FF;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #C3EEFF;" + "-fx-background-radius: 100;");
                        }

                        if (quizTheme[i].getText().equals("Mathematics")) {
                            frame[i].setStyle("-fx-background-color: #E4FFF3;" + "-fx-background-radius: 20;");
                            quizTheme[i].setStyle("-fx-background-color: #9AFFD2;" + "-fx-background-radius: 100;");
                        }

                    }
        });
        scroll.setFitToHeight(true);
    }

    public String arrangeLongText(String longText) {
        final int maxLineLength = 160;

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

    public void attemptQuiz(ActionEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                String quizTitle = getAttemptQuizTitle(event);
                if (isDoubleAttemptQuiz(loginController.usernameID, quizTitle)) {
                    showError("You have already attempted the quiz and cannot attempt it again!");
                } else {
                    if (attemptQuizConfirmation(quizTitle)) {
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
                                    pointsQuiz = Integer.parseInt(userData[6].trim());
                                    // Reconstruct the line with updated data
                                    line = String.join(",", userData);
                                }
                                lines.add(line);
                            }
                        } catch (IOException e) {
                            showError("Error reading user data from file: " + e.getMessage());
                            return;
                        }
                        pointsQuiz+=2;
                        updateUserCSV();
                        desktop.browse(new URI(getButtonURL(event)));
                    }
                }
            } else {
                System.err.println("Desktop browsing not supported.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getButtonURL(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        Button[] quizLink = {
                quizAttempt1, quizAttempt2, quizAttempt3, quizAttempt4, quizAttempt5, quizAttempt6, quizAttempt7,
                quizAttempt8, quizAttempt9, quizAttempt10, quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15
        };

        checkThemeSelected();
        int numButton = selectedQuiz.size();
        for (int i = 0; i < numButton; i++) {
            Button quizButton = quizLink[i];
            if (clickedButton == quizButton) {
                return selectedQuiz.get(i).getLink();
            }
        }
        return null;
    }

    public String getAttemptQuizTitle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        Button[] quizLink = {
                quizAttempt1, quizAttempt2, quizAttempt3, quizAttempt4, quizAttempt5, quizAttempt6, quizAttempt7,
                quizAttempt8, quizAttempt9, quizAttempt10, quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15
        };

        checkThemeSelected();
        int numButton = selectedQuiz.size();
        for (int i = 0; i < numButton; i++) {
            Button quizButton = quizLink[i];
            if (clickedButton == quizButton) {
                return selectedQuiz.get(i).getTitle();
            }
        }
        return null;
    }

    public boolean attemptQuizConfirmation(String quizTitle) {
        TextField[] quizTheme = {
                quizTheme1, quizTheme2, quizTheme3, quizTheme4, quizTheme5,
                quizTheme6, quizTheme7, quizTheme8, quizTheme9, quizTheme10,
                quizTheme11, quizTheme12, quizTheme13, quizTheme14, quizTheme15
        };
        Text[] quizTitles = {
                quizTitle1, quizTitle2, quizTitle3, quizTitle4, quizTitle5, quizTitle6,
                quizTitle7, quizTitle8, quizTitle9, quizTitle10, quizTitle11, quizTitle12,
                quizTitle13, quizTitle14, quizTitle15
        };
        Button[] attemptButton = {quizAttempt1, quizAttempt2, quizAttempt3,quizAttempt4, quizAttempt5, quizAttempt6, quizAttempt7,
                quizAttempt8, quizAttempt9, quizAttempt10, quizAttempt11, quizAttempt12, quizAttempt13, quizAttempt14, quizAttempt15
        };
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to attempt quiz '" + quizTitle + "'? Only one attempt is allowed.");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        String usernamelogin = loginController.usernameID;

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        int updatedPoint=0;
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Platform.runLater(() -> {
                for (int i = 0; i < selectedQuiz.size(); i++) {
                    if (isDoubleAttemptQuiz(loginController.usernameID,quizTitle)) {
                        if(quizTitles[i].getText().equals(quizTitle)) {
                            attemptButton[i].setText("Attempted");
                        }else{
                            attemptButton[i].setText("Attempt");
                        }
                    }
                }
            });
            try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/Data/attemptQuizRecord.csv", true))) {
                try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] userData = line.split(",");
                        String usernameSearch = userData[0];
                        if (usernameSearch.equals(loginController.usernameID)) {
                            // Update the numEventCreated value for the current user
                            updatedPoint = Integer.parseInt(userData[6].trim())+2;

                        }
                    }
                } catch (IOException e) {
                    showError("Error reading user data from file: " + e.getMessage());
                }

                writer.println(loginController.usernameID + "," + quizTitle + "," + formattedDateTime + "," + updatedPoint);
                writer.flush();
                return true; // Confirmation received
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showError("Attempt Quiz Failed");
        }
        return false; // Confirmation denied
    }

    public boolean isDoubleAttemptQuiz(String username, String quizTitle) {
        String csvFile = "src/main/java/Data/attemptQuizRecord.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(quizTitle)) {
                    return true; // Double attempt quiz found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        alertError.showAndWait();
    }

    public void updateUserCSV() {
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
                    userData[6] = String.valueOf(pointsQuiz);
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