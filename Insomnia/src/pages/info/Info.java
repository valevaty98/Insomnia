package pages.info;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Info {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView booksImage;

    @FXML
    private TextArea noteField;

    @FXML
    private Button doneButton;

    @FXML
    private Text titleText;

    @FXML
    private Text authorText;

    @FXML
    private Text fromDateText;

    @FXML
    private Text tillDateText;

    @FXML
    private Button aboutButton;

    @FXML
    private Button isReadingButton;

    @FXML
    private Button willReadButton;

    @FXML
    private Button haveReadButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button editButton;

    @FXML
    void initialize() {

    }
}
