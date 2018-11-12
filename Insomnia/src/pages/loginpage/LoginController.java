package pages.loginpage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import db.DatabaseHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.User;
import pages.Page;

public class LoginController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        signInButton.setOnMouseClicked(event -> {
            checkLogin();
        });

        signUpButton.setOnMouseClicked(event -> {
            openNewScene(signUpButton, "/pages/signuppage/signup.fxml");
        });

       loginField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r"))
                checkLogin();
        });

        passwordField.setOnKeyTyped(event -> {
            if (event.getCharacter().equals("\r"))
                checkLogin();
        });
    }

    private void checkLogin() {
        String loginText = loginField.getText().trim();
        String passwordText = passwordField.getText().trim();

        if (!loginText.equals("") && !passwordText.equals("")) {
            loginUser(loginText, passwordText);
        } else {
            Shake shakeLoginField = new Shake(loginField);
            Shake shakePasswordField = new Shake(passwordField);
            shakeLoginField.playShake();
            shakePasswordField.playShake();
        }
    }

    private void loginUser(String loginText, String passwordText) {
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);

        try {
            ResultSet userSet = dbHandler.getUser(user);
            int counter = 0;
            if (userSet != null) {
                openNewScene(signInButton, "/pages/mainpage/mainpage.fxml");
            } else {
                Shake shakeLoginField = new Shake(loginField);
                Shake shakePasswordField = new Shake(passwordField);
                shakeLoginField.playShake();
                shakePasswordField.playShake();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
