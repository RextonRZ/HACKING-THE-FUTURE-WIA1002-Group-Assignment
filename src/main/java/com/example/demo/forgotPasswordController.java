package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;

public class forgotPasswordController {

    @FXML
    private Stage stage;

    @FXML
    private TextField forgetUsermailField;

    @FXML
    public void forgotPasswordStartUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Forgot Password");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void confirmButton(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("updatePassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public String generateTemporaryPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random a = new Random();
        int pwLength = 8;
        int length = characters.length();
        for (int i = 0; i < pwLength; i ++){
            int index = a.nextInt(length);
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public void sendTemporaryPassword(String email, String password){

    }

    public void showNotification(){
        Alert alertNoti = new Alert(Alert.AlertType.INFORMATION);
        alertNoti.setTitle("Sent Successful");
        alertNoti.setHeaderText(null);
        alertNoti.setContentText("An email with your new temporary password is sent to the linked email!");

        alertNoti.showAndWait();
    }

    private boolean emailExists(String usermail){
        String fileName = "src/main/java/Data/user.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line, username;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (usermail.endsWith(".com")){
                    username = userData[1].trim();
                }else{
                    username = userData[0].trim();
                }

                if (username.equals(usermail)) {
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public void updateTemporaryPassword(String usermail, String tempPassword){
        String fileName = "src/main/java/Data/user.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            StringBuilder fileContent = new StringBuilder();
            String line, reference;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (usermail.endsWith(".com")){
                    reference = userData[1].trim();
                }else{
                    reference = userData[0].trim();
                }

                String username = userData[0].trim();
                String userEmail = userData[1].trim();
                String password = userData[2].trim();
                String role = userData[3].trim();
                String latitude = userData[4].trim();
                String longitude = userData[5].trim();

                if (reference.equals(usermail)){
                    password = tempPassword;
                }

                fileContent.append(username).append(",").append(userEmail).append(",").append(password)
                        .append(",").append(role).append(",").append(latitude).append(",").append(longitude).append("\n");

                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
                    writer.write(fileContent.toString());
                }catch (IOException e){
                    showError("Error appending user data to file: " + e.getMessage());
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
    }

    @FXML
    public void sendEmail(ActionEvent event) throws Exception {
        String email = forgetUsermailField.getText().trim();

        if (email.isEmpty()) {
            showError("Please enter your username or email.");
            return;
        }

        if (emailExists(email)) {
            String tempPassword = generateTemporaryPassword();

            updateTemporaryPassword(email, tempPassword);

            sendTemporaryPassword(email, tempPassword);

            showNotification();
        } else {
            showError("The username or email entered is not registered!");
        }
    }

    public void showError(String errorMessage) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }
}
