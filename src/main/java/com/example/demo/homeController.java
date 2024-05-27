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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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
    int numOfEntry;
    String fileNameDiscussion = "src/main/java/Data/discussion.txt";

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

        printForums();
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

        if (words.length > 100) {
            DiscussionErrorMessage.setText("Discussion should not contain more than 100 words");
            discussionvalid = false;
        } else if (discussion.isEmpty()) {
            DiscussionErrorMessage.setText("Discussion should not be empty");
            discussionvalid = false;
        } else {
            DiscussionErrorMessage.setText("");
            discussionvalid = true;
        }
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

    public void getTotalDiscussion(){
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
            while ((line = reader.readLine()) != null){
                numOfEntry++;
                reader.readLine();
                reader.readLine();
                reader.readLine();
            }
        }catch (IOException e) {
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

    public void printForum1(){
        int index = numOfEntry;
        if (index <= 0){
            forum1.setText("");
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum1.setText(user + "\n" + time + "\n" + discussion);
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum2() {
        int index = numOfEntry - 1;
        if (index <= 0){
            forum2.setText("");
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum2.setText(user + "\n" + time + "\n" + discussion);
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }

    public void printForum3(){
        int index = numOfEntry - 2;
        if (index <= 0){
            forum3.setText("");
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum3.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum4.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum5.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum6.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum7.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum8.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum9.setText(user + "\n" + time + "\n" + discussion);
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
            try(BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))){
                for (int i = 1; i < index; i++){
                    for (int j = 0; j < 4; j++){
                        reader.readLine();
                    }
                }
                String user = reader.readLine();
                String time = reader.readLine();
                String discussion = reader.readLine();

                forum10.setText(user + "\n" + time + "\n" + discussion);
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }
        }
    }
}
