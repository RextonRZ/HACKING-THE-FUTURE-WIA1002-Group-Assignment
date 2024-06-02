package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class viewProfileYSController implements Initializable {
    @FXML
    private Label coords;
    @FXML
    private Label email;
    @FXML
    private Label point;
    @FXML
    private Label role;
    @FXML
    private Label username;
    @FXML
    private Label usernameTitle;

    String fileName = "src/main/java/Data/friend_requests.csv";

    public void profileStartUp(ActionEvent event) throws Exception {
        Parent root3 = FXMLLoader.load(getClass().getResource("ProfileYS.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Profile");
        Scene homeScene = new Scene(root3);
        stage.setScene(homeScene);
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
    }

    public void reset(){
        usernameTitle.setText(personalProfileYSController.Username);
        username.setText(personalProfileYSController.Username);
        email.setText(personalProfileYSController.Email);
        coords.setText(personalProfileYSController.Coordinate);
        point.setText(personalProfileYSController.Points);
        role.setText(personalProfileYSController.Role);
    }

    @FXML
    void addFriend(ActionEvent event) throws Exception{
        if (checkSentRequest()){
            personalProfileYSController.showError("You have already sent " + personalProfileYSController.Username + " a friend request!");
        } else if (checkPendingRequest()) {
            personalProfileYSController.showError(personalProfileYSController.Username + " has sent you a friend request! Please accept or reject in the 'Friend Request' page! ");
        } else if (alreadyFriends()){
            personalProfileYSController.showError("You are already friends with " + personalProfileYSController.Username + "!");
        } else if (overLimit()){
            personalProfileYSController.showError(personalProfileYSController.Username + " have reached the maximum number of pending requests! Please try again later!");
        }else {
            storeRequest();
            sendNotification(personalProfileYSController.Email);
            friendRequestSent();
        }
    }

    private void storeRequest(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
            writer.println(loginController.HostUsername + "," + personalProfileYSController.Username + ",0"); // 0 when the request is still pending
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void friendRequestSent() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Friend request successfully sent!");

        alert.showAndWait();
    }

    private void sendNotification(String email){
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
            message.setSubject("New Incoming Friend Request");

            String emailContent = "<html><body>";
            emailContent += "<p>Dear <b>" + personalProfileYSController.Username + "</b>,</p>";
            emailContent += "<p><b>" + loginController.HostUsername + "</b> sent you a friend request!</p>";
            emailContent += "<p>Please login to Hacking The Future to process with the friend request...</p>";
            emailContent += "</body></html>";

            message.setContent(emailContent, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Failed to send notification. Error: " + e.getMessage());
        }
    }

    public boolean checkSentRequest(){
        String line;
        String check = loginController.HostUsername + "," + personalProfileYSController.Username + ",0";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.equals(check))
                    return true;
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public boolean checkPendingRequest(){
        String line;
        String check = personalProfileYSController.Username + "," + loginController.HostUsername + ",0";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.equals(check))
                    return true;
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public boolean alreadyFriends(){
        String line;
        String check1 = personalProfileYSController.Username + "," + loginController.HostUsername + ",1";
        String check2 = loginController.HostUsername + "," + personalProfileYSController.Username + ",1";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if ( (line.equals(check1)) || (line.equals(check2)))
                    return true;
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    public boolean overLimit(){
        String line;
        System.out.println(personalProfileYSController.Username);
        int pending = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                byte status = Byte.parseByte(userData[2]);
                if ((userData[1].equals(personalProfileYSController.Username)) && (status == 0)){
                    pending ++;
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        if (pending < 10){
            return false;
        }else{
            return true;
        }
    }
}
