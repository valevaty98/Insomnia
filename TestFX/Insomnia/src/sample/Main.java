package sample;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        Line line = new Line(10,10,100,100);
        line.setFill(Color.RED);
        line.setStrokeWidth(3);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        text.setFill(Color.RED);
        text.setStroke(Color.GREEN);
        text.setStrokeWidth(1);
        text.setX(400);
        text.setY(200);
        text.setText("You're welcome!");
        text.setStrikethrough(true);
        text.setUnderline(true);

        Image image = new Image(new FileInputStream("D:\\smoking.png"));
        ImageView imageView = new ImageView(image);

        imageView.setX(400);
        imageView.setY(20);

        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        imageView.setPreserveRatio(true);

        Glow glow = new Glow(0.8);

        imageView.setEffect(glow);

        //Creating a Path
        Path path = new Path();

        //Moving to the starting point
        MoveTo moveTo = new MoveTo(108, 71);

        //Creating 1st line
        LineTo line1 = new LineTo(321, 161);

        //Creating 2nd line
        LineTo line2 = new LineTo(126,232);

        //Creating 3rd line
        LineTo line3 = new LineTo(232,52);

        //Creating 4th line
        LineTo line4 = new LineTo(269, 250);

        //Creating 4th line
        LineTo line5 = new LineTo(108, 71);

        //Adding all the elements to the path
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4, line5);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        rotateTransition.setNode(imageView);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(50);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        Circle circle = new Circle();
        circle.setCenterX(300);
        circle.setCenterY(150);
        circle.setRadius(30);
        circle.setFill(Color.BROWN);
        circle.setStrokeWidth(3);

        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(1000));
        scaleTransition.setNode(circle);
        scaleTransition.setByX(1.5);
        scaleTransition.setByY(1.5);
        scaleTransition.setCycleCount(50);
        scaleTransition.setAutoReverse(false);
        scaleTransition.play();

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(circle);
        translateTransition.setByX(300);
        translateTransition.setCycleCount(50);
        translateTransition.setAutoReverse(false);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (line.getStroke() == Color.GREEN)
                    line.setStroke(Color.RED);
                else
                    line.setStroke(Color.GREEN);
                line.setStrokeWidth(3);
                rotateTransition.play();
            }
        };

        EventHandler<MouseEvent> eventHandlerText= new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                rotateTransition.stop();
            }
        };

        line.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        text.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerText);

        Text label1 = new Text("Email");
        Text label2 = new Text("Password");
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button btn1 = new Button("Submit");
        Button btn2 = new Button("Clear");

        GridPane gp = new GridPane();
        gp.setMinSize(400, 200);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setAlignment(Pos.CENTER);

        gp.add(label1,0,0);
        gp.add(emailField, 1, 0);
        gp.add(label2, 0, 1);
        gp.add(passwordField, 1, 1);
        gp.add(btn1, 0, 2);
        gp.add(btn2, 1, 2);

        //Styling nodes
        btn1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        label1.setStyle("-fx-font: normal bold 20px 'serif' ");
        label2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gp.setStyle("-fx-background-color: BEIGE;");

        Scene scene2 = new Scene(gp);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotateTransition.stop();
                primaryStage.setScene(scene2);
            }
        });

        Group root = new Group(line, text, path, imageView,circle);
        Scene scene1 = new Scene(root, 600, 300);
        scene1.setFill(Color.YELLOW);

        primaryStage.setTitle("Insomnia");
        primaryStage.setScene(scene1);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
