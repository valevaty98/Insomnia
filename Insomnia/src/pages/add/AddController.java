package pages.add;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import objects.Book;
import objects.Status;
import pages.Page;

public class AddController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView booksImage;

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
    private ChoiceBox<String> genreComboBox;

    @FXML
    private TextArea noteField;

    @FXML
    private Button addBookButton;

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
    void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ObservableList<String> obsList = FXCollections.observableList(list);
        genreComboBox.setItems(obsList);

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

        addBookButton.setOnMouseClicked(event -> {
            Status status = addNewBookToUser();
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/haveread/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/isreading/isreading.fxml");
            else openNewScene(willReadButton, "/pages/willread/willread.fxml");

        });
    }

    private Status addNewBookToUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String status = typeRadio.getSelectedToggle().toString();
        Status st;
        if (status == "Have read") st = Status.HAVE_READ;
        else if (status == "Is Reading") {
            st = Status.IS_READING;
        } else st = Status.WILL_READ;

        Book book = new Book(titleField.getText(), authorField.getText(), fromDatePicker.getValue(), tillDatePicker.getValue(), isAudioCheck.isSelected(), genreComboBox.getValue().toString(), st, noteField.getText());

        try {
            dbHandler.addBookToUser(book);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return st;
    }
}
