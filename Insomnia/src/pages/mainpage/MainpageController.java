package pages.mainpage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import objects.Quote;
import pages.Page;

public class MainpageController extends Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text quoteText;

    @FXML
    private Text authorText;

    @FXML
    private Button isReadingButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button willReadButton;

    @FXML
    private Button haveReadButton;

    @FXML
    void initialize() {
        quoteText.setText(getQuoteFromDB().getQuote());
        authorText.setText(getQuoteFromDB().getAuthor());

        aboutButton.setOnMouseClicked(event -> {
            openNewScene(aboutButton, "/pages/about/about.fxml");
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

        homeButton.setOnMouseClicked(event -> {
            openNewScene(homeButton, "/pages/mainpage/mainpage.fxml");
        });
    }

    public Quote getQuoteFromDB(){
        try {
            return dbHandler.getRandomQuote();
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
