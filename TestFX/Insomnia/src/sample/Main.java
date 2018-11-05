package sample;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
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

        Image image = new Image("http://www.compline-ufa.ru/assets/images/0-4(2).png");
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

        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotateTransition.stop();
            }
        });
        line.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        text.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerText);

        Group root = new Group(line, text, path, imageView,circle);
        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.YELLOW);


        primaryStage.setTitle("Insomnia");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
