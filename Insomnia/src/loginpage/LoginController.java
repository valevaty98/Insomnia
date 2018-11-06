package loginpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
                System.out.println("Invalid data!");
            }
        });

        signUpButton.setOnMouseClicked(event -> {
            signUpButton.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/signuppage/signup.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 700, 500));
            stage.showAndWait();
        });
    }

    private void loginUser(String loginText, String passwordText) {

    }
}
