package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class createQuizController {
    @FXML
    private Stage stage;

    @FXML
    private TextField quizTitle;

    @FXML
    private Text quizTitleErrorMessage;

    @FXML
    private TextField quizDescription;

    @FXML
    private Text quizDescriptionErrorMessage;

    @FXML
    private SplitMenuButton quizTheme;

    @FXML
    private Text quizThemeErrorMessage;

    @FXML
    private TextField quizContent;

    @FXML
    private Text quizContentErrorMessage;

    private boolean titleValid = false, descriptionValid = false, themeValid = false, contentValid = false;
    private String title, description, theme, content;

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
            quizThemeErrorMessage.setText("Please select a quiz theme!");
            themeValid = false;
        } else {
            quizThemeErrorMessage.setText("");
            themeValid = true;
        }
    }

    @FXML
    public void quizContentValidation() {
        content = quizContent.getText();

        if (!isValidURL(content)) {
            quizContentErrorMessage.setText("Please enter a valid Quiziz URL!");
            contentValid = false;
        } else if (content.isEmpty()) {
            quizContentErrorMessage.setText("Please enter a Quiziz URL!");
            contentValid = false;
        } else {
            quizContentErrorMessage.setText("");
            contentValid = true;
        }
    }

    private boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
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
            if (content.isBlank()) quizContentErrorMessage.setText("Please enter a Quiziz URL!");
        } else {
            showCreateQuizSuccess();
            // Proceed with storing the quiz or further actions
        }
    }

    @FXML
    public void homeButton(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void quizButton(ActionEvent event) throws Exception {
        attemptQuizController attemptQuizController = new attemptQuizController();
        attemptQuizController.attemptQuizStartUp(event);
    }

    @FXML
    public void eventButton(ActionEvent event) throws Exception {
        viewEventController viewEventController = new viewEventController();
        viewEventController.viewEventStartUp(event);
    }

    @FXML
    public void bookingButton(ActionEvent event) throws Exception {
        bookingController bookingController = new bookingController();
        bookingController.bookingStartUp(event);
    }

    @FXML
    public void leaderBoardButton(ActionEvent event) throws Exception {
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    @FXML
    public void profileButton(ActionEvent event) throws Exception {
        personalProfileController personalProfileController = new personalProfileController();
        personalProfileController.personalProfileStartUp(event);
    }

    @FXML
    public void logOutButton(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure you want to log out?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Alert closed");
        } else if (result.get() == ButtonType.OK) {
            loginController loginController = new loginController();
            loginController.loginStartUp(event);
        }
    }

    @FXML
    public void loginButton(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void cAAButton(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene signUpScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(signUpScene);
    }

    private void showCreateQuizSuccess() {
        Alert alertSU = new Alert(Alert.AlertType.INFORMATION);
        alertSU.setTitle("Success");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Quiz " + title + " successfully created.");
        alertSU.showAndWait();
    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        alertError.showAndWait();
    }
}