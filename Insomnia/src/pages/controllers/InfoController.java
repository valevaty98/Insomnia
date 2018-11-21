package pages.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import objects.Book;
import objects.Status;
import pages.Page;

public class InfoController extends Page{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label noteLabel;

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

    private Book selectedBook;

    public void initData(Book book) {
        selectedBook = book;
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        fromDateText.setText(book.getFromDate());
        tillDateText.setText(book.getTillDate());
        noteLabel.setText(book.getNotes());
    }

    @FXML
    void initialize() {

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

        doneButton.setOnMouseClicked(event -> {
            if (selectedBook.getStatus() == Status.HAVE_READ) openNewScene(doneButton, "/pages/scenes/haveread.fxml");
            else if (selectedBook.getStatus() == Status.IS_READING) openNewScene(doneButton, "/pages/scenes/isreading.fxml");
            else if (selectedBook.getStatus() == Status.WILL_READ) openNewScene(doneButton, "/pages/scenes/willread.fxml");
        });

        editButton.setOnMouseClicked(event -> {
            openNewEditScene(editButton, "/pages/scenes/edit.fxml", selectedBook);
        });
    }
}
