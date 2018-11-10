package pages.edit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import animations.Shake;
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
import objects.Book;
import objects.Status;
import pages.Page;

public class EditController extends Page {

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
    private ChoiceBox<String> genreComboBox;

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

    private Book selectedBook;

    public void initData(Book book) {
        selectedBook = book;
    }

    @FXML
    void initialize() {

        List<String> list = new ArrayList<String>();
        list.add("Science fiction");
        list.add("Satire");
        list.add("Drama");
        list.add("Action");
        list.add("Adventure");
        list.add("Mistery");
        list.add("Horror");
        list.add("Romance");
        list.add("Self help");
        list.add("Another");
        ObservableList<String> obsList = FXCollections.observableList(list);
        genreComboBox.setValue("Select genre..");
        genreComboBox.setItems(obsList);

        aboutButton.setOnMouseClicked(event -> {
            openNewScene(aboutButton, "/pages/about/about.fxml");
        });

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

        cancelButton.setOnMouseClicked(event -> {
            Status status = selectedBook.getStatus();
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/haveread/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/isreading/isreading.fxml");
            else if (status == Status.WILL_READ) openNewScene(willReadButton, "/pages/willread/willread.fxml");
        });

        updateButton.setOnMouseClicked(event -> {
            Status status = updateBook(selectedBook);
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/haveread/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/isreading/isreading.fxml");
            else if (status == Status.WILL_READ) openNewScene(willReadButton, "/pages/willread/willread.fxml");
        });


    }

    private Status updateBook(Book book) {
        Shake shake;
        DatabaseHandler dbHandler = new DatabaseHandler();

        String status = typeRadio.getSelectedToggle().getUserData().toString();
        Status st;
        if (status.equals("Have read")) st = Status.HAVE_READ;
        else if (status.equals("Is reading")) {
            st = Status.IS_READING;
        } else st = Status.WILL_READ;

        book.setStatus(st);

        if (titleField.getText().isEmpty()) {
            shake = new Shake(titleField);
            shake.playShake();
            return Status.INVALID_INFO;
        } else book.setTitle(titleField.getText());

        if (!authorField.getText().isEmpty()) book.setAuthor(authorField.getText());

        if (fromDatePicker.getValue() == null) {
            shake = new Shake(fromDatePicker);
            shake.playShake();
            return Status.INVALID_INFO;
        } else book.setFromDate(fromDatePicker.getValue().toString());

        if (tillDatePicker.getValue() != null)  book.setTillDate(tillDatePicker.getValue().toString());

        book.setAudio(isAudioCheck.isSelected());

        if (genreComboBox.getValue() != null) book.setGenre(genreComboBox.getValue().toString());
        else {
            shake = new Shake(fromDatePicker);
            shake.playShake();
            return Status.INVALID_INFO;
        };

        if (!noteField.getText().isEmpty()) book.setNotes(noteField.getText());

        try {
            dbHandler.updateBookInDB(book);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return st;
    }
}
