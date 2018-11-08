package pages.willread;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import pages.Page;

public class WillController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView booksImage;

    @FXML
    private ListView<?> willReadList;

    @FXML
    private ImageView addImage;

    @FXML
    private Button isReadingButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button willReadButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button haveReadButton;

    @FXML
    void initialize() {
        aboutButton.setOnMouseClicked(event -> {
            openNewScene(aboutButton, "/pages/about/about.fxml");
        });

        homeButton.setOnMouseClicked(event -> {
            openNewScene(homeButton, "/pages/mainpage/mainage.fxml");
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

        addImage.setOnMouseClicked(event -> {
            openNewScene(addImage, "/pages/add/add.fxml");
        });
    }
}
