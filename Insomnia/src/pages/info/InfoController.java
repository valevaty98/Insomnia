package pages.info;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
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

    private Book selectedBook;

    public void initData(Book book) {
        selectedBook = book;
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        fromDateText.setText(book.getFromDate());
        tillDateText.setText(book.getTillDate());
        noteField.setText(book.getNotes());
    }

    @FXML
    void initialize() {

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

        doneButton.setOnMouseClicked(event -> {
            if (selectedBook.getStatus() == Status.HAVE_READ) openNewScene(doneButton, "/pages/haveread/haveread.fxml");
            else if (selectedBook.getStatus() == Status.IS_READING) openNewScene(doneButton, "/pages/isreading/isreading.fxml");
            else if (selectedBook.getStatus() == Status.WILL_READ) openNewScene(doneButton, "/pages/willread/willread.fxml");
        });

        editButton.setOnMouseClicked(event -> {
            openNewSceneWithInfo(editButton, "/pages/edit/edit.fxml", selectedBook);
        });
    }
}
