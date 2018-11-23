package pages.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import objects.User;
import pages.Page;

public class SignUpController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        signUpButton.setOnMouseClicked(event -> {
            signUpNewUser();
        });

        backButton.setOnMouseClicked(event -> {
            openNewScene(backButton, "/pages/scenes/login.fxml");
        });

        nameField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r")) signUpNewUser();
        });

        surnameField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r")) signUpNewUser();
        });

        emailField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r")) signUpNewUser();
        });

        loginField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r")) signUpNewUser();
        });

        passwordField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r")) signUpNewUser();
        });
    }

    private void signUpNewUser() {
        String nameText = nameField.getText();
        String surnameText = surnameField.getText();
        String emailText = emailField.getText();
        String loginText = loginField.getText();
        String passwordText = passwordField.getText();
        User user = new User(nameText, surnameText, emailText, loginText, passwordText);

        if (testLogin(loginText) && testPassword(passwordText) && testName(nameText) && testEmail(emailText)) {
            try {
                dbHandler.signUpUser(user);
                openNewScene(signUpButton, "/pages/scenes/mainpage.fxml");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            if (!testLogin(loginText)) new Shake(loginField).playShake();
            if (!testPassword(passwordText)) new Shake(passwordField).playShake();
            if (!testName(nameText)) new Shake(nameField).playShake();
            if (!testEmail(emailText))new Shake(emailField).playShake();
        }

    }

    private boolean testPassword(String testString) {
        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$";
        return testString.matches(passPattern);
    }

    private boolean testEmail(String testString) {
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$";
        return testString.matches(emailPattern);
    }

    private boolean testLogin(String testString) {
        String loginPattern = "^[a-zA-Z0-9._-]{3,}$";
        try {
            if (!dbHandler.doesLoginExist(testString)) {
                return testString.matches(loginPattern);
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean testName(String testString) {
        String namePattern = "^[a-zA-Z]{2,}$";
        return testString.matches(namePattern);
    }

}
