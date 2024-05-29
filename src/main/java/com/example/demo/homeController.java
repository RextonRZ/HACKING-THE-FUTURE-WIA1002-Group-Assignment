package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.util.Optional;
import java.net.URL;
import java.util.ResourceBundle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class homeController implements Initializable {

    private Stage stage;

    @FXML
    private Text role;
    @FXML
    private Text coordinate;
    @FXML
    private Text point;
    @FXML
    private Text username;
    @FXML
    private TextArea discussionSpace;
    @FXML
    private Text DiscussionErrorMessage;
    @FXML
    private TextArea forum1;
    @FXML
    private TextArea forum2;
    @FXML
    private TextArea forum3;
    @FXML
    private TextArea forum4;
    @FXML
    private TextArea forum5;
    @FXML
    private TextArea forum6;
    @FXML
    private TextArea forum7;
    @FXML
    private TextArea forum8;
    @FXML
    private TextArea forum9;
    @FXML
    private TextArea forum10;

    String usernamelogin = loginController.usernameID;
    public static String roleCon;
    String discussion;
    boolean discussionvalid = false;
    boolean enter = false;
    public static int numOfEntry;
    String fileNameDiscussion = "src/main/java/Data/discussion.txt";

    public static String getUserID1, getUserID2, getUserID3, getUserID4, getUserID5, getUserID6, getUserID7, getUserID8, getUserID9, getUserID10;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fileName = "src/main/java/Data/user.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line, usernameSet, latitude, longitude, pointSet, roleSet;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                usernameSet = userData[0].trim();
                roleSet = userData[3].trim();
                latitude = userData[4].trim();
                longitude = userData[5].trim();

                if(usernameSet.equals(usernamelogin)) {
                    role.setText("\uD83D\uDC64  " + roleSet);
                    username.setText(usernameSet);
                    coordinate.setText("\uD83D\uDCCD  " + latitude + ", " + longitude);

                    if (roleSet.equals("Parent")||roleSet.equals("Educator")){
                        point.setText("");
                    }
                    else {
                        pointSet= userData[6].trim();
                        point.setText("⛀  "+pointSet);
                    }

                    roleCon = roleSet;

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        adjustTextAreaHeight(forum1);
        adjustTextAreaHeight(forum2);
        adjustTextAreaHeight(forum3);
        adjustTextAreaHeight(forum4);
        adjustTextAreaHeight(forum5);
        adjustTextAreaHeight(forum6);
        adjustTextAreaHeight(forum7);
        adjustTextAreaHeight(forum8);
        adjustTextAreaHeight(forum9);
        adjustTextAreaHeight(forum10);

        printForums();
    }

    @FXML
    void refresh(ActionEvent event) throws Exception{
        Parent root3 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root3, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);

        printForums();
    }

    private void adjustTextAreaHeight(TextArea textArea) {
        textArea.textProperty().addListener((obs, oldValue, newValue) -> {
            // Calculate the number of lines in the TextArea
            int numLines = newValue.split("\n").length;

            // Adjust the height based on the number of lines
            double defaultHeight = 24.0; // Default height of one row
            double lineHeight = 1.2; // Line height factor
            double minHeight = 30.0; // Minimum height
            double maxHeight = 200.0; // Maximum height
            double newHeight = Math.min(Math.max(minHeight, numLines * defaultHeight * lineHeight), maxHeight);

            // Set the new height to the TextArea
            textArea.setPrefHeight(newHeight);
        });
    }


    @FXML
    public void homeStartUp(ActionEvent event) throws Exception  {
        Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void quizButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Young Student")) {
            attemptQuizController attemptQuizController = new  attemptQuizController();
            attemptQuizController.attemptQuizStartUp(event);
        }

        else if(roleCon.equals("Educator")) {
            createQuizController createQuizController = new  createQuizController();
            createQuizController.createQuizStartUp(event);
        }

        else {
            showError("You have no access to this page.");
        }
    }

    public void eventButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Educator")) {
            chooseEventController chooseEventController = new chooseEventController();
            chooseEventController.chooseEventStartUp(event);
        }

        else {
            viewEventController viewEventController = new viewEventController();
            viewEventController.viewEventStartUp(event);
        }
    }

    public void bookingButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Parent")) {
            bookingController bookingController = new bookingController();
            bookingController.bookingStartUp(event);
        }

        else {
            showError("You have no access to this page.");
        }

    }

    public void leaderBoardButton(ActionEvent event) throws Exception{
        leaderBoardController leaderBoardController = new leaderBoardController();
        leaderBoardController.leaderBoardStartUp(event);
    }

    public void profileButton(ActionEvent event) throws Exception{
        if(roleCon.equals("Young Student")){
            personalProfileYSController personalProfileYSController = new personalProfileYSController();
            personalProfileYSController.personalProfileStartUp(event);
        }

        else if(roleCon.equals("Educator")){
            personalProfileEduController personalProfileEduController = new personalProfileEduController();
            personalProfileEduController.personalProfileStartUp(event);
        }

        else{
            personalProfilePaController personalProfilePaController = new personalProfilePaController();
            personalProfilePaController.personalProfileStartUp(event);
        }

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

    public void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }

    public void discussionValidation() throws Exception{
        discussion = discussionSpace.getText();
        String[] words = discussion.split("\\s+");
        checkEnter();

        if (words.length > 100) {
            DiscussionErrorMessage.setText("Discussion should not contain more than 100 words");
            discussionvalid = false;
        } else if (discussion.isEmpty()) {
            DiscussionErrorMessage.setText("Discussion should not be empty");
            discussionvalid = false;
        } else if (enter){
            DiscussionErrorMessage.setText("'Enter' should not be included in your discussion!");
            discussionvalid = false;
        } else {
            DiscussionErrorMessage.setText("");
            discussionvalid = true;
        }
    }

    public void checkEnter(){
        discussionSpace.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                enter = true;
            }else{
                enter = false;
            }
        });
    }

    @FXML
    public void addDiscussion(ActionEvent event) throws Exception {
        discussion = discussionSpace.getText();

        if (!discussionvalid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeDiscussion(event);
        }
    }

    public void storeDiscussion(ActionEvent event){

        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        try{
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, true))){
                writer.println(usernamelogin + "\n" + currentDateTime + "\n" + discussion.trim() + "\n");
                writer.flush();
                showDiscussionSuccess();

                homeController homeController = new homeController();
                homeController.homeStartUp(event);
            }catch (IOException e){
                showError("Error appending new event data to file: " + e.getMessage());
            }
        }catch (Exception e){
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    private void showDiscussionSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Discussion successfully added!");

        alert.showAndWait();
    }

    public void getTotalDiscussion() {
        numOfEntry = 0;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
            while ((line = reader.readLine()) != null) {
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        numOfEntry++;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            showError("Error reading user data from file: " + e.getMessage());
        }
    }

    public void printForums(){
        getTotalDiscussion();
        printForum1();
        printForum2();
        printForum3();
        printForum4();
        printForum5();
        printForum6();
        printForum7();
        printForum8();
        printForum9();
        printForum10();
    }

    public void printForum1() {
        int index = numOfEntry;
        if (index <= 0) {
            forum1.setText("");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum1.setText(entryBuilder.toString().trim());
                    getUserID1 = entryBuilder.toString().trim();
                }
            } catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }


    public void printForum2() {
        int index = numOfEntry - 1;
        if (index <= 0) {
            forum2.setText("");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum2.setText(entryBuilder.toString().trim());
                    getUserID2 = entryBuilder.toString().trim();
                }
            } catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum3(){
        int index = numOfEntry - 2;
        if (index <= 0){
            forum3.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum3.setText(entryBuilder.toString().trim());
                    getUserID3 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum4(){
        int index = numOfEntry - 3;
        if (index <= 0){
            forum4.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum4.setText(entryBuilder.toString().trim());
                    getUserID4 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum5(){
        int index = numOfEntry - 4;
        if (index <= 0){
            forum5.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum5.setText(entryBuilder.toString().trim());
                    getUserID5 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum6(){
        int index = numOfEntry - 5;
        if (index <= 0){
            forum6.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum6.setText(entryBuilder.toString().trim());
                    getUserID6 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum7(){
        int index = numOfEntry - 6;
        if (index <= 0){
            forum7.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum7.setText(entryBuilder.toString().trim());
                    getUserID7 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum8(){
        int index = numOfEntry - 7;
        if (index <= 0){
            forum8.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum8.setText(entryBuilder.toString().trim());
                    getUserID8 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum9(){
        int index = numOfEntry - 8;
        if (index <= 0){
            forum9.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum9.setText(entryBuilder.toString().trim());
                    getUserID9 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum10(){
        int index = numOfEntry - 9;
        if (index <= 0){
            forum10.setText("");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (int i = 0; i < index; i++) {
                    StringBuilder entryBuilder = new StringBuilder();
                    String line;
                    // Read lines until encountering an empty line or reaching the end of the file
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        entryBuilder.append(line).append("\n");
                    }
                    // Set the text in the forum1 TextField
                    forum10.setText(entryBuilder.toString().trim());
                    getUserID10 = entryBuilder.toString().trim();
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void replyButton1(ActionEvent event) throws Exception {
       replyController replyController = new replyController();
       replyController.replyStartUp1(event);
    }

    public void replyButton2(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp2(event);
    }

    public void replyButton3(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp3(event);
    }

    public void replyButton4(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp4(event);
    }

    public void replyButton5(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp5(event);
    }

    public void replyButton6(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp6(event);
    }

    public void replyButton7(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp7(event);
    }

    public void replyButton8(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp8(event);
    }

    public void replyButton9(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp9(event);
    }

    public void replyButton10(ActionEvent event) throws Exception {
        replyController replyController = new replyController();
        replyController.replyStartUp10(event);
    }

}
