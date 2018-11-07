package pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Page {
    protected void openNewScene(Node node, String window) {
        node.getScene().getWindow().hide();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(window));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }
}
