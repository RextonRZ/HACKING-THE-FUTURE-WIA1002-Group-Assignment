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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.*;
import java.util.Random;

public class forgotPasswordController {

    @FXML
    private Stage stage;

    @FXML
    private TextField forgetUsermailField;

    @FXML
    private TextField tempPass;

    @FXML
    private TextField newPass;

    @FXML
    private TextField newConPass;

    @FXML
    private Text passwordErrorMessage;

    @FXML
    private Text confirmPasswordErrorMessage;

    protected boolean newPassValid = false;
    protected boolean newConPassValid = false;

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
        if (authenticate()){
            Parent root2 = FXMLLoader.load(getClass().getResource("updatePassword.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(homeScene);
        }else{
            showError("The username and temporary password does not match!");
        }
    }

    private boolean authenticate(){
        String fileName = "src/main/java/Data/user.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line, username, password;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if (forgetUsermailField.getText().endsWith(".com")){
                    username = userData[1].trim();
                }else{
                    username = userData[0].trim();
                }
                password = userData[2].trim();

                if (username.equals(forgetUsermailField.getText().trim()) && password.equals(tempPass.getText().trim())) {
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
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

        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "hackingthefuture2324@gmail.com";
        String senderPassword = "nqvyuckofmvjqovk";

        String recipientEmail = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Temporary Password to Hacking The Future");

            String emailContent = "<html><body>";
            emailContent += "<p>Dear " + forgetUsermailField.getText() + ",</p>";
            emailContent += "<p>Your temporary password is: <b>" + password + "</b></p>";
            emailContent += "</body></html>";

            message.setContent(emailContent, "text/html");

            Transport.send(message);

            showNotification();
        } catch (MessagingException e) {
            System.out.println("Failed to send temporary password. Error: " + e.getMessage());
        }
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
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String username = userData[0].trim();
                String userEmail = userData[1].trim();
                if (username.equals(usermail) || userEmail.equals(usermail)) {
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public void updatePassword(String usermail, String tempPassword){
        String fileName = "src/main/java/Data/user.csv";
        StringBuilder fileContent = new StringBuilder();
        boolean userFound = false; // Flag to track if user is found

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[0].trim();
                String userEmail = userData[1].trim();
                String password = userData[2].trim();
                String role = userData[3].trim();
                String latitude = userData[4].trim();
                String longitude = userData[5].trim();

                // Check if user exists
                if (username.equals(usermail) || userEmail.equals(usermail)) {
                    password = tempPassword;
                    userFound = true; // User found
                }

                // Append user data to fileContent
                fileContent.append(username).append(",").append(userEmail).append(",").append(password)
                        .append(",").append(role).append(",").append(latitude).append(",").append(longitude).append("\n");
            }

            // If user not found, add new entry
            if (!userFound) {
                fileContent.append(usermail).append(",").append(usermail).append(",").append(tempPassword)
                        .append(",").append("role").append(",").append("latitude").append(",").append("longitude").append("\n");
            }
        } catch (IOException e) {
            showError("Error reading user data from file: " + e.getMessage());
            return;
        }

        // Write fileContent back to the CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            showError("Error writing user data to file: " + e.getMessage());
        }
    }

    @FXML
    public void sendEmail(ActionEvent event) throws Exception{
        String fileName = "src/main/java/Data/user.csv";
        String usermail = forgetUsermailField.getText().trim();

        if (usermail.isEmpty()){
            showError("Please enter your username or email.");
            return;
        }

        String email = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[0].trim();
                String userEmail = userData[1].trim();

                if (username.equals(usermail) || userEmail.equals(usermail)) {
                    email = userData[1].trim() ;
                }
            }
        }

        if (!email.isEmpty() && emailExists(email)){
            String tempPassword = generateTemporaryPassword();

            updatePassword(email, tempPassword);

            sendTemporaryPassword(email, tempPassword);


        }else{
            showError("The username or email entered is not registered!");
        }
    }

    public void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }

    public void confirmUpdatePassword(){
        boolean passwordValid = passwordValidation();
        if (passwordValid){
            boolean passwordCValid = passwordConfirmationValidation();
            if (passwordCValid) {
                updatePassword(forgetUsermailField.getText(), newPass.getText());
                showUpdateSuccessful();
            }
        }
    }

    public void updatePasswordButton(ActionEvent event) throws Exception {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void showUpdateSuccessful(){
        Alert alertNoti = new Alert(Alert.AlertType.INFORMATION);
        alertNoti.setTitle("Update Successful");
        alertNoti.setHeaderText(null);
        alertNoti.setContentText("Your new password is successfully updated!");

        alertNoti.showAndWait();
    }

    public boolean passwordValidation(){
        String passwordFP = newPass.getText();
        boolean lowerCase = false, upperCase = false, specialChar = false, numericalChar = false;
        byte strengthPassword = 0;

        //Check password format
        if (passwordFP.length() < 6)
            passwordErrorMessage.setText("Password should contain at least 6 characters");
        else {
            for(int i = 0; i < passwordFP.length();i++){
                char pwChar = passwordFP.charAt(i);
                if(pwChar >='a' && pwChar <= 'z')
                    lowerCase = true;
                if(pwChar >='A' && pwChar <= 'Z')
                    upperCase = true;
                if(pwChar >='0' && pwChar <= '9')
                    specialChar = true;
                if(pwChar >= 32 && pwChar <= 47 || pwChar >= 58 && pwChar <= 64 || pwChar >= 91 && pwChar <= 96 ||pwChar >= 123 && pwChar <= 126)
                    numericalChar = true;
            }

            if(lowerCase)
                strengthPassword++;
            if(upperCase)
                strengthPassword++;
            if(specialChar)
                strengthPassword++;
            if(numericalChar)
                strengthPassword++;

            switch (strengthPassword){
                case 0: passwordErrorMessage.setText("Strength: Weak");break;
                case 1: passwordErrorMessage.setText("Strength: Weak");break;
                case 2: passwordErrorMessage.setText("Strength: Moderate");break;
                case 3: passwordErrorMessage.setText("Strength: Moderate");break;
                case 4: passwordErrorMessage.setText("Strength: Strong");break;
            }
            newPassValid = true;
        }
        return newPassValid;
    }

    public boolean passwordConfirmationValidation() {
        String passwordFP = newPass.getText();
        String passwordConfirmationFP = newConPass.getText();

        //Check whether both password match
        if (newPassValid) {
            if (!passwordFP.equals(passwordConfirmationFP) && !passwordConfirmationFP.isEmpty())
                confirmPasswordErrorMessage.setText("Password does not match");
            else {
                confirmPasswordErrorMessage.setText("");
                newConPassValid = true;
            }
        }
        return newConPassValid;
    }
}
