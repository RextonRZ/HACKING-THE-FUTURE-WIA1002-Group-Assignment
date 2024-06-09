package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class replyController {

    @FXML
    private Stage stage;

    @FXML
    private Label replyto;

    @FXML
    private TextArea replySpace;

    @FXML
    private Text ReplyErrorMessage;

    boolean replyValid = false;

    boolean enter = false;
    String reply, hostUsername, hostTime, hostContent;
    String fileNameDiscussion = "src/main/java/Data/discussion.txt";
    String usernamelogin = loginController.HostUsername;

    public void replyStartUp1(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply1.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID1.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp2(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply2.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID2.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp3(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply3.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID3.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp4(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply4.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID4.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp5(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply5.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID5.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp6(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply6.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID6.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp7(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply7.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID7.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp8(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply8.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID8.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp9(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply9.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID9.split("\n");
        controller.setUserId(user[0]);
    }

    public void replyStartUp10(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reply10.fxml"));
        Parent root = loader.load();
        replyController controller = loader.getController();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.setTitle("Reply");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
        stage.setScene(scene);
        stage.show();

        String[] user = homeController.getUserID10.split("\n");
        controller.setUserId(user[0]);
    }

    public void setUserId(String userId) {
        if (userId != null) {
            replyto.setText(userId);
        } else {
            showError("User ID is null");
        }
    }

    public void showError(String errorMessage){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);

        alertError.showAndWait();
    }

    public void replyValidation() throws Exception{
        reply = replySpace.getText();
        String[] words = reply.split("\\s+");
        checkEnter();

        if (words.length > 100) {
            ReplyErrorMessage.setText("Reply should not contain more than 100 words");
            replyValid = false;
        } else if (reply.isEmpty()) {
            ReplyErrorMessage.setText("Reply should not be empty");
            replyValid = false;
        } else if (enter){
            ReplyErrorMessage.setText("'Enter' should not be included in your discussion!");
            replyValid = false;
        }  else {
            ReplyErrorMessage.setText("");
            replyValid = true;
        }
    }

    public void checkEnter(){
        replySpace.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                enter = true;
            }else{
                enter = false;
            }
        });
    }

    @FXML
    public void addReply1(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply1(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply2(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply2(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply3(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply3(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply4(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply4(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply5(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply5(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply6(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply6(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply7(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply7(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply8(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply8(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply9(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply9(event);
            replySpace.clear();
            closeWindow();
        }
    }

    @FXML
    public void addReply10(ActionEvent event) throws Exception {
        reply = replySpace.getText();

        if (!replyValid) {
            showError("The discussion field cannot be blank and more than 100 words!");
        } else {
            // store event or proceed further
            storeReply10(event);
            replySpace.clear();
            closeWindow();
        }
    }

    private void closeWindow(){
        Stage stage = (Stage)replySpace.getScene().getWindow();
        stage.close();
    }

    public void storeReply1(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                current++;

                for (; current < homeController.numOfEntry; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID1.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID1);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply2(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 1;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID2.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID2);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply3(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 2;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID3.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID3);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply4(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 3;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID4.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID4);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply5(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 4;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID5.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID5);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply6(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 5;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID6.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID6);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply7(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 6;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID7.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID7);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply8(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 7;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID8.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID8);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply9(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 8;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID9.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID9);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    public void storeReply10(ActionEvent event) {
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = DateTime.format(format);
        int index = homeController.numOfEntry - 9;
        int current;
        StringBuilder content = new StringBuilder();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDiscussion))) {
                for (current = 0; current < index - 1; current++) {
                    String line;

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }

                    if (current != index - 2)
                        content.append("\n");
                }

                current ++;

                if (current == index) {
                    String line;
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append("");
                    }
                }

                for (current += 1; current <= homeController.numOfEntry; current++) {
                    String line;
                    content.append("\n");
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        content.append(line).append("\n");
                    }
                }
            }catch (IOException e) {
                showError("Error reading user data from file: " + e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameDiscussion, false))) {
                int indentationLevel = (int) Math.sqrt((double)homeController.getUserID10.split("\t").length);

                StringBuilder indentationBuilder = new StringBuilder();
                for (int i = 0; i < indentationLevel - 1; i++) {
                    indentationBuilder.append("\t");
                }
                String indentation = indentationBuilder.toString();

                String contentEXOri = content.toString();

                writer.println(contentEXOri);

                writer.println(indentation + homeController.getUserID10);
                writer.println(indentation + "\t" + usernamelogin + "\n" + indentation + "\t" + currentDateTime + "\n" + indentation + "\t" + reply.trim() + "\n");
                writer.flush();
                showReplySuccess();
            }
        } catch (IOException e) {
            showError("Error appending new event data to file: " + e.getMessage());
        } catch (Exception e) {
            showError("Error storing new event data: " + e.getMessage());
        }
    }

    private void showReplySuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Reply successfully added!");

        alert.showAndWait();
    }
}