package pages.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import animations.Shake;
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
import javafx.scene.layout.AnchorPane;
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
    private ToggleGroup statusRadio;

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

    @FXML
    private AnchorPane deletePane;

    @FXML
    private AnchorPane infoPane;

    @FXML
    private Button sureButton;

    @FXML
    private Button noButton;

    private Book selectedBook;

    public void initData(Book book) {
        selectedBook = book;

        titleField.setText(selectedBook.getTitle());
        authorField.setText(selectedBook.getAuthor());

        if (selectedBook.getStatus() == Status.HAVE_READ) statusRadio.selectToggle(haveReadRadio);
        else if (selectedBook.getStatus() == Status.IS_READING) statusRadio.selectToggle(isReadingRadio);
        else statusRadio.selectToggle(willReadRadio);

        fromDatePicker.setValue(LocalDate.parse(selectedBook.getFromDate()));
        if (!selectedBook.getTillDate().equals(""))
            tillDatePicker.setValue(LocalDate.parse(selectedBook.getTillDate()));

        isAudioCheck.setSelected(selectedBook.isAudio());
        noteField.setText(selectedBook.getNotes());
        genreComboBox.getSelectionModel().select(selectedBook.getGenre());
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
            openNewScene(aboutButton, "/pages/scenes/about.fxml");
        });

        homeButton.setOnMouseClicked(event -> {
            openNewScene(homeButton, "/pages/scenes/mainpage.fxml");
        });

        willReadButton.setOnMouseClicked(event -> {
            openNewScene(willReadButton, "/pages/scenes/willread.fxml");
        });

        isReadingButton.setOnMouseClicked(event -> {
            openNewScene(isReadingButton, "/pages/scenes/isreading.fxml");
        });

        haveReadButton.setOnMouseClicked(event -> {
            openNewScene(haveReadButton, "/pages/scenes/haveread.fxml");
        });

        cancelButton.setOnMouseClicked(event -> {
            Status status = selectedBook.getStatus();
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/scenes/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/scenes/isreading.fxml");
            else if (status == Status.WILL_READ) openNewScene(willReadButton, "/pages/scenes/willread.fxml");
        });

        updateButton.setOnMouseClicked(event -> {
            Status status = updateBook(selectedBook);
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/scenes/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/scenes/isreading.fxml");
            else if (status == Status.WILL_READ) openNewScene(willReadButton, "/pages/scenes/willread.fxml");
        });

        deleteButton.setOnMouseClicked(event -> {
            deletePane.setVisible(true);
            infoPane.setDisable(true);
        });

        sureButton.setOnMouseClicked(event -> {
            try {
                dbHandler.deleteBookFromDB(selectedBook);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Status status = selectedBook.getStatus();
            if (status == Status.HAVE_READ) openNewScene(haveReadButton, "/pages/scenes/haveread.fxml");
            else if (status == Status.IS_READING) openNewScene(isReadingButton, "/pages/scenes/isreading.fxml");
            else if (status == Status.WILL_READ) openNewScene(willReadButton, "/pages/scenes/willread.fxml");
        });

        noButton.setOnMouseClicked(event -> {
            deletePane.setVisible(false);
            infoPane.setDisable(false);
        });
    }

    private Status updateBook(Book book) {
        Shake shake;

        String status = statusRadio.getSelectedToggle().getUserData().toString();
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

        if (tillDatePicker.getValue() != null) book.setTillDate(tillDatePicker.getValue().toString());

        book.setAudio(isAudioCheck.isSelected());

        if (genreComboBox.getValue() != null) book.setGenre(genreComboBox.getValue().toString());
        else {
            shake = new Shake(fromDatePicker);
            shake.playShake();
            return Status.INVALID_INFO;
        }

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
