package sample;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

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

        //Creating a hexagon
        Polygon hexagon = new Polygon();

        //Adding coordinates to the hexagon
        hexagon.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
        });
        //Setting the fill color for the hexagon
        hexagon.setFill(Color.BLUE);

        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();

        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(1000));

        //Setting the node for the transition
        rotateTransition.setNode(hexagon);

        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);

        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(50);

        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);

        //Playing the animation
        rotateTransition.play();

        Group root = new Group(line, text, path, imageView, hexagon);
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
