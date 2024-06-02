package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class viewProfileYSControllerWDelete implements Initializable {
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

    String viewProfileUsername;

    String fileName = "src/main/java/Data/friend_requests.csv";

    public void profileStartUp(ActionEvent event) throws Exception {
        Parent root3 = FXMLLoader.load(getClass().getResource("ProfileYSWithDelete.fxml"));
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
        usernameTitle.setText(friendRequestController.UsernamePU);
        username.setText(friendRequestController.UsernamePU);
        email.setText(friendRequestController.EmailPU);
        coords.setText(friendRequestController.CoordinatePU);
        point.setText(friendRequestController.PointsPU);
        role.setText(friendRequestController.RolePU);
    }

    @FXML
    void deleteFriend(ActionEvent event) throws Exception{
        deleteEntry();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void deleteEntry(){
        String line;
        StringBuilder entries = new StringBuilder();
        viewProfileUsername = friendRequestController.UsernamePU;
        String check = viewProfileUsername + "," + loginController.HostUsername + ",1";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.equals(check)){
                    entries.append("");
                }else{
                    entries.append(line + "\n");
                }
            }
        } catch (IOException e) {
            personalProfileYSController.showError("Error reading user data from file: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            String entry = entries.toString();
            writer.print(entry);
            writer.flush();
            showDeleteSuccess();

        }catch (IOException e) {
            personalProfileYSController.showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            personalProfileYSController.showError("Error storing new event data: " + e.getMessage());
        }
    }

    private void showDeleteSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Successfully deleted " + viewProfileUsername + " as friend!");

        alert.showAndWait();
    }
}

