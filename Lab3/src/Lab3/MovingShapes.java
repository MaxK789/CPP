package Lab3;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MovingShapes extends Application {

    private static final int SCREEN_WIDTH = 750;
    private static final int SCREEN_HEIGHT = 400;
    private static final int NUM_CIRCLES = 10;
    private static final int NUM_RECTANGLES = 10;

    private final List<Circle> circles = new ArrayList<>();
    private final List<Rectangle> rectanglesLeft = new ArrayList<>();
    private final List<Rectangle> rectanglesRight = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Moving Shapes");

        for (int i = 0; i < NUM_CIRCLES; i++) {
            Circle circle = new Circle(20);
            circle.setFill(Color.BLUE);
            circle.setTranslateX(50 + i * 70);
            circle.setTranslateY(50);
            circles.add(circle);
            root.getChildren().add(circle);
        }

        for (int i = 0; i < NUM_RECTANGLES / 2; i++) {
            Rectangle rectangle = new Rectangle(50, 30);
            rectangle.setFill(Color.RED);
            rectangle.setTranslateX(50);
            rectangle.setTranslateY(50 + i * 70);
            rectanglesLeft.add(rectangle);
            root.getChildren().add(rectangle);
        }

        for (int i = NUM_RECTANGLES / 2; i < NUM_RECTANGLES; i++) {
            Rectangle rectangle = new Rectangle(50, 30);
            rectangle.setFill(Color.RED);
            rectangle.setTranslateX(SCREEN_WIDTH - 50);
            rectangle.setTranslateY(50 + (i - (double) NUM_RECTANGLES / 2) * 70);
            rectanglesRight.add(rectangle);
            root.getChildren().add(rectangle);
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Move circles vertically
                for (Circle circle : circles) {
                    circle.setTranslateY(circle.getTranslateY() + 3);
                    if (circle.getTranslateY() > SCREEN_HEIGHT) {
                        circle.setTranslateY(-50);
                    }
                }

                for (Rectangle rectangle : rectanglesLeft) {
                    rectangle.setTranslateX(rectangle.getTranslateX() + 3);
                    if (rectangle.getTranslateX() > SCREEN_WIDTH) {
                        rectangle.setTranslateX(-rectangle.getWidth());
                    }
                }

                for (Rectangle rectangle : rectanglesRight) {
                    rectangle.setTranslateX(rectangle.getTranslateX() - 3);
                    if (rectangle.getTranslateX() < -rectangle.getWidth()) {
                        rectangle.setTranslateX(SCREEN_WIDTH);
                    }
                }
            }
        };
        timer.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
