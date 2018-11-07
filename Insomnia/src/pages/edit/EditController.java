package pages.edit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private RadioButton haveReadRadio;

    @FXML
    private ToggleGroup typeRadio;

    @FXML
    private RadioButton isReadingRadio;

    @FXML
    private RadioButton willReadRadio;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker tillDatePicker;

    @FXML
    private CheckBox isAudioCheck;

    @FXML
    private ChoiceBox<?> genreComboBox;

    @FXML
    private TextArea noteField;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

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
    private Button deleteButton;

    @FXML
    void initialize() {

    }
}
