package pages.haveread;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import db.Const;
import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import objects.Book;
import objects.Status;
import pages.Page;

public class HaveReadController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Book> haveReadTable;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> tillDateColumn;

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
        ObservableList<Book> books = null;
        try {
            books = getBooksProperties(Status.HAVE_READ);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        tillDateColumn.setCellValueFactory(new PropertyValueFactory<>("tillDate"));
        haveReadTable.setItems(books);

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

        addImage.setOnMouseClicked(event -> {
            openNewScene(addImage, "/pages/add/add.fxml");
        });
    }

    private ObservableList<Book> getBooksProperties(Status status) throws SQLException, ClassNotFoundException {
        ObservableList<Book> books = FXCollections.observableArrayList();

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet bookSet = dbHandler.getBooks(status);

        while(bookSet.next()) {
            books.add(new Book(bookSet.getString(Const.BOOKS_TITLE), bookSet.getString(Const.BOOKS_AUTHOR),
                    bookSet.getString(Const.BOOKS_TILLDATE)));
        }

        return books;
    }
}
