package pages.about;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import pages.Page;

public class AboutController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button haveReadButton;

    @FXML
    private Button isReadingButton;

    @FXML
    private Button willReadButton;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        homeButton.setOnMouseClicked(event -> {
            openNewScene(homeButton, "/pages/mainpage/mainpage.fxml");
        });

        willReadButton.setOnMouseClicked(event -> {
            openNewScene(willReadButton, "/pages/willread/willread.fxml");
        });

        isReadingButton.setOnMouseClicked(event -> {
            openNewScene(isReadingButton, "/pages/isreading/isreading.fxml");
        });

        haveReadButton.setOnMouseClicked(event -> {
            openNewScene(haveReadButton, "/pages/haveread/haveread.fxml");
        });
    }
}
