package pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Book;
import pages.info.InfoController;

import java.io.IOException;

public abstract class Page {
    protected void openNewScene(Node node, String window) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(window));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)node.getScene().getWindow();

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    protected void openNewSceneWithInfo(Node node, String window, Book book) {
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

        Stage stage = (Stage)node.getScene().getWindow();

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }
}
