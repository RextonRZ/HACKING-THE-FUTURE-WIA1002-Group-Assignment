package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

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
    ObservableList<String> roleList = FXCollections.observableArrayList("Young Student", "Educator", "Parent");

    @FXML
    private Text login;

    private boolean emailValid, usernameValid, passwordValid, passwordConfirmationValid;
    String usernameSU, emailSU, passwordSU, passwordConfirmationSU;

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
            else
                usernameErrorMessage.setText("");
                usernameValid = true;
        }

    }

    //Email Validation
    public void emailValidation() throws Exception {
        emailSU = emailSignUp.getText();

        //Check email format
        if (!emailSU.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") )
            emailErrorMessage.setText("Invalid email address");
        else {
            emailErrorMessage.setText("");
            emailValid = true;
        }
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

        else {
            loginController loginController = new loginController();
            loginController.loginStartUp(event);
        }
    }

    @FXML
    public void login(MouseEvent event) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root2, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(homeScene);
    }


}