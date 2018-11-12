package pages;

import db.Const;
import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Book;
import objects.Status;
import pages.edit.EditController;
import pages.info.InfoController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Page {
    protected DatabaseHandler dbHandler;

    public Page() {
        dbHandler = new DatabaseHandler();
    }

    protected void openNewScene(Node node, String window) {
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(window));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    protected void openNewSceneWithInfo(Node node, String window, Book book) {
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InfoController infoController = loader.getController();
        infoController.initData(book);

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    protected void openNewEditScene(Node node, String window, Book book) {
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditController editController = loader.getController();
        editController.initData(book);

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    protected ObservableList<Book> getBooksProperties(Status status) throws SQLException, ClassNotFoundException {
        ObservableList<Book> books = FXCollections.observableArrayList();

        ResultSet bookSet = dbHandler.getBooks(status);

        while (bookSet.next()) {
            books.add(new Book(bookSet.getInt(Const.BOOKS_ID), status, bookSet.getString(Const.BOOKS_TITLE), bookSet.getString(Const.BOOKS_AUTHOR), bookSet.getString(Const.BOOKS_FROMDATE), bookSet.getString(Const.BOOKS_TILLDATE), Boolean.valueOf(bookSet.getString(Const.BOOKS_AUDIO)), bookSet.getString(Const.BOOKS_GENRE), bookSet.getString(Const.BOOKS_NOTES)));
        }

        return books;
    }
}
