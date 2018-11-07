package pages.signuppage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
            openNewScene(signUpButton, "/pages/mainpage/mainpage.fxml");
        });

        backButton.setOnMouseClicked(event -> {
            openNewScene(backButton, "/pages/loginpage/login.fxml");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

           User user = new User(nameField.getText(), surnameField.getText(), emailField.getText(),
                    loginField.getText(), passwordField.getText());

            try {
                dbHandler.signUpUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
}
