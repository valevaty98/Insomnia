package pages.signuppage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import db.DatabaseHandler;
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
            openNewScene(backButton, "/pages/loginpage/login.fxml");
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
        DatabaseHandler dbHandler = new DatabaseHandler();

        String nameText = nameField.getText();
        String surnameText = surnameField.getText();
        String emailText = emailField.getText();
        String loginText = loginField.getText();
        String passwordText = passwordField.getText();
        User user = new User(nameText, surnameText, emailText, loginText, passwordText);

        if (!loginText.equals("") && !passwordText.equals("") && !nameText.equals("") && !emailText.equals("")) {
            try {
                dbHandler.signUpUser(user);
                openNewScene(signUpButton, "/pages/mainpage/mainpage.fxml");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Shake shakeLoginField = new Shake(loginField);
            Shake shakePasswordField = new Shake(passwordField);
            Shake shakeNameField = new Shake(nameField);
            Shake shakeEmailField = new Shake(emailField);

            shakeLoginField.playShake();
            shakePasswordField.playShake();
            shakeEmailField.playShake();
            shakeNameField.playShake();
        }
    }
}
