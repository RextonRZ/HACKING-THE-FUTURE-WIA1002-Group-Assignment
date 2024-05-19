package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class signUpController {
    protected Stage stage;
    @FXML
    private Text emailErrorMessage;

    @FXML
    private TextField emailSignUp;

    @FXML
    private PasswordField passwordConfirmationSignUp;

    @FXML
    private Text passwordConfirmationErrorMessage;

    @FXML
    private Text passwordErrorMessage;

    @FXML
    private PasswordField passwordSignUp;

    @FXML
    private Text usernameErrorMessage;

    @FXML
    private TextField usernameSignUp;

    @FXML
    private ChoiceBox role;

    @FXML
    private Text roleErrorMessage;

    @FXML
    private TextField latitude;
    
    @FXML
    private TextField longitude;

    ObservableList<String> roleList = FXCollections.observableArrayList("Young Student", "Educator", "Parent");

    private boolean emailValid = false, usernameValid = false, passwordValid = false, passwordConfirmationValid = false;
    String usernameSU, emailSU, passwordSU, passwordConfirmationSU;

    public void signUP(ActionEvent event) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene signUpScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(signUpScene);
        
        // Find the TextTField named "latitude" in the loaded FXML file
        latitude = (TextField) root2.lookup("#latitude");
        if (latitude != null){
            latitude.setText(generateCoordinates()[0]);
        }else{
            System.err.println("Error: TextField 'latitude' not found.");
        }
        
        longitude = (TextField) root2.lookup("#longitude");
        if (longitude != null){
            longitude.setText(generateCoordinates()[1]);
        }else{
            System.err.println("Error: TextField 'longitude' not found.");
        }
    }

    //Username Validation
    public void usernameValidation() throws Exception {
        usernameSU = usernameSignUp.getText();

        //Check username format
        for(int i = 0; i < usernameSU.length(); i++) {
            char unChar = usernameSU.charAt(i);
            if (usernameSU.toLowerCase().contains("admin"))
                usernameErrorMessage.setText("Admin is a reserved id");
            else if (usernameSU.length() < 8)
                usernameErrorMessage.setText("Username should contain at least 8 characters");
            else if (usernameSU.length() > 15)
                usernameErrorMessage.setText("Username should contain not more than 15 characters");
            else if (unChar == 32)
                usernameErrorMessage.setText("Username should not contain SPACE");
            else if (!usernameSU.matches("[A-Za-z0-9]{8,}"))
                usernameErrorMessage.setText("Username should not contain special characters");
            else if (duplicatedUsername(usernameSU))
                usernameErrorMessage.setText("Username already exists");
            else
                usernameErrorMessage.setText("");
                usernameValid = true;
        }
    }
    
    private boolean duplicatedUsername(String username){
        String fileName = "src/main/java/Data/user.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String usernameExist = userData[0];
                if(usernameExist.equals(username)){
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    //Email Validation
    public void emailValidation() throws Exception {
        emailSU = emailSignUp.getText().trim();

        //Check email format
        if (!emailSU.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") )
            emailErrorMessage.setText("Invalid email address");
        else if (duplicatedEmail(emailSU))
            emailErrorMessage.setText("This email is already registered to another account!");
        else {
            emailErrorMessage.setText("");
            emailValid = true;
        }
    }
    
    private boolean duplicatedEmail(String email){
        String fileName = "src/main/java/Data/user.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String emailExist = userData[1].trim();
                if(emailExist.equals(email)){
                    return true;
                }
            }
        }catch (IOException e){
            showError("Error reading user data from file: " + e.getMessage());
        }
        return false;
    }

    //Password Validation
    public void passwordValidation(){
        passwordSU = passwordSignUp.getText();
        passwordConfirmationSU = passwordConfirmationSignUp.getText();
        boolean lowerCase = false, upperCase = false, specialChar = false, numericalChar = false;
        byte strengthPassword = 0;

        //Check password format
        if (passwordSU.length() < 6)
            passwordErrorMessage.setText("Password should contain at least 6 characters");
        else {
            for(int i = 0; i < passwordSU.length();i++){
                char pwChar = passwordSU.charAt(i);
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
            passwordValid = true;
        }
    }
    //Password Confirmation
    public void passwordConfirmationValidation(){
        passwordSU = passwordSignUp.getText();
        passwordConfirmationSU = passwordConfirmationSignUp.getText();

        //Check whether both password match
        if (!passwordSU.equals(passwordConfirmationSU))
            passwordConfirmationErrorMessage.setText("Password does not match");
        else {
            passwordConfirmationErrorMessage.setText("");
            passwordConfirmationValid = true;
        }
    }

    @FXML
    //Choose Role
    public void initialize() {
        role.setItems(roleList);
        role.setValue("");
        
        latitude.setEditable(false);
        longitude.setEditable(false);
    }

    @FXML
    public void signUpButton(ActionEvent event) throws Exception{
        usernameSU = usernameSignUp.getText();
        emailSU = emailSignUp.getText();
        passwordSU = passwordSignUp.getText();
        passwordConfirmationSU = passwordConfirmationSignUp.getText();

        //Check whether every input meet the requirements
        if(!usernameValid || !emailValid || !passwordValid || !passwordConfirmationValid) {
            if(usernameSU.isBlank())
                usernameErrorMessage.setText("Username should not be empty");
            if(emailSU.isBlank())
                emailErrorMessage.setText("Email should not be empty");
            if(passwordSU.isBlank())
                passwordErrorMessage.setText("Password should not be empty");
            if(passwordConfirmationSU.isBlank())
                passwordConfirmationErrorMessage.setText("Confirm password should not be empty");
        }
        
        if (role.getValue() == null || role.getValue().toString().isEmpty()) {
            showError("Please select a role!");
            return;
        }
        
        if (usernameValid && emailValid && passwordValid && passwordConfirmationValid) {
            storeUser(event);
        }
    }

    @FXML
    public void login(MouseEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }

    public void storeUser(ActionEvent event){
        String fileName = "src/main/java/Data/user.csv";
        
        if (!usernameErrorMessage.getText().isEmpty()){
            showError(usernameErrorMessage.getText());
            return;
        }else if(!emailErrorMessage.getText().isEmpty()){
            showError(emailErrorMessage.getText());
            return;
        }

        try{
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
                writer.println(usernameSU + "," + emailSU + "," + passwordSU + "," + role.getValue() 
                        + "," + latitude.getText() + "," + longitude.getText());
                writer.flush();
                showSignUpSuccess();
                loginController loginController = new loginController();
                loginController.loginStartUp(event);

            }catch (IOException e){
                showError("Error appending user data to file: " + e.getMessage());
            }
        }catch (Exception e){
            showError("Error storing user data: " + e.getMessage());
        }
        
    }
    
    private void showSignUpSuccess(){
        Alert alertSU = new Alert(AlertType.INFORMATION);
        alertSU.setTitle("Sign-Up Successful");
        alertSU.setHeaderText(null);
        alertSU.setContentText("Your account has been successfully created!");
        
        alertSU.showAndWait();
    }
    
    public void showError(String errorMessage){
        Alert alertError = new Alert(AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(errorMessage);
        
        alertError.showAndWait();
    }
    
    public static String[] generateCoordinates(){
        Random a = new Random();
        
        String[] coordination = new String[2];
        
        for (int i = 0; i < coordination.length; i++){
            double coords = -500.0 + (1000.0 * a.nextDouble());
            coordination[i] = String.format("%.2f", coords);
        }
        
        return coordination;
    }
}