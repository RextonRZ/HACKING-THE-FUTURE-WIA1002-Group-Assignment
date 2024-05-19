package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class loginController {
    @FXML
    private Stage stage;
    @FXML
    private TextField useremailLogin;
    @FXML
    private TextField passwordLogin;
    @FXML
    private TextField forgetUsermailField;


    @FXML
    public void loginStartUp(ActionEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    @FXML
    public void loginButton(ActionEvent event) throws Exception{
        
        if (authenticate()){
            Parent root2 = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(homeScene);
        }else{
            displayLoginError();
        }
        
    }
    
    private boolean authenticate(){
        String fileName = "src/main/java/Data/user.csv";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line, username, password;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (useremailLogin.getText().endsWith(".com")){
                    username = userData[1].trim();
                }else{
                    username = userData[0].trim();
                }
                password = userData[2].trim();
                
                if (username.equals(useremailLogin.getText().trim()) && password.equals(passwordLogin.getText().trim())) {
                    return true; 
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }
    
    private void displayLoginError(){
        Alert alertLogin = new Alert(AlertType.ERROR);
        alertLogin.setTitle("Login Failed");
        alertLogin.setHeaderText(null);
        alertLogin.setContentText("Invalid username or password. Please try again later!");
        
        alertLogin.showAndWait();
    }

    @FXML
    public void forgotpassword(MouseEvent event) throws Exception{
        String email = forgetUsermailField.getText().trim();
        
        if (email.isEmpty()){
            showError("Please enter your username or email.");
            return;
        }
        
        if (emailExists(email)){
            String tempPassword = generateTemporaryPassword();
                
            updateTemporaryPassword(email, tempPassword);
            
            sendTemporaryPassword(email, tempPassword);
            
            showNotification();
        }else{
            showError("The username or email entered is not registered!");
        }
    }

    @FXML
    public void cAAButton(ActionEvent event) throws Exception{
        signUpController signUpController = new signUpController();
        signUpController.signUP(event);
    }

    @FXML
    public void closeButton(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void showError(String errorMessage){
        Alert alertError = new Alert(AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        
        alertError.showAndWait();
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
        Alert alertNoti = new Alert(AlertType.INFORMATION);
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
}

    
