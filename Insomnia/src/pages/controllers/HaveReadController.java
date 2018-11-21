package pages.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.Const;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TableColumn<Book, String> fromDateColumn;

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

        titleColumn.setCellValueFactory(new PropertyValueFactory<>(Const.BOOKS_TITLE));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>(Const.BOOKS_AUTHOR));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>(Const.BOOKS_FROMDATE));
        haveReadTable.setItems(books);

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

        addImage.setOnMouseClicked(event -> {
            openNewScene(addImage, "/pages/scenes/add.fxml");
        });

        haveReadTable.setOnMouseClicked(event -> {
            if (haveReadTable.getSelectionModel().getSelectedItem() != null)
                openNewSceneWithInfo(haveReadTable, "/pages/scenes/info.fxml", haveReadTable.getSelectionModel().getSelectedItem());
        });
    }
}
