package loginpage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import db.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.User;

public class LoginController {

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
        });

        signUpButton.setOnMouseClicked(event -> {
            openNewScene(signUpButton, "/signuppage/signup.fxml");
        });
    }

    private void loginUser(String loginText, String passwordText) {
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);

        DatabaseHandler dbHandler = new DatabaseHandler();
        try {
            ResultSet userSet = dbHandler.getUser(user);
            int counter = 0;
            while (userSet.next()) {
                counter++;
            }

            if (counter > 0) {
                openNewScene(signInButton, "/mainpage/mainpage.fxml");
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

    private void openNewScene(Node node, String window) {
        node.getScene().getWindow().hide();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(window));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 700, 500));
        stage.showAndWait();
    }
}
