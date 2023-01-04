package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    public ArrayList<Entity> balls = new ArrayList<Entity>(); // Create an ArrayList object
    public static Integer ballCount;
    private static final int BALL_SIZE = 15;
    private static final int BALL_SPEED = 5;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("FXGL Benchmark");
        settings.setGameMenuEnabled(false);
        settings.setWidth((int)screenWidth); //set it to the proper screen size
        settings.setHeight((int)screenHeight);
        settings.setManualResizeEnabled(false);
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setProfilingEnabled(true);
        settings.setPauseMusicWhenMinimized(false);
    }

    @Override
    protected void initGame() {
        IntStream.range(0, ballCount).forEachOrdered(n -> {
            balls.add(new Ball(generateInt(0, 2560), generateInt(0, 1080), BALL_SIZE, BALL_SPEED, Color.rgb(generateInt(1,233), generateInt(1,233), generateInt(1,233))).object());
        });

        getGameScene().getRoot().setCursor(Cursor.DEFAULT);
    }



    public static int generateInt(Integer min, Integer max) {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    @Override
    protected void onUpdate(double tpf) {
        for (int i = 0; i < balls.size(); i++) {
            Entity ball = balls.get(i);
            Point2D velocity = ball.getObject("velocity");
            ball.translate(velocity);

            if (ball.getX() <= 0) {
                ball.setX(0);
                ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
            }

            if ((int)ball.getRightX() >= getAppWidth()) {
                ball.setX(getAppWidth() - (int)ball.getWidth());
                ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
            }

            if (ball.getY() <= 0) {
                ball.setY(0);
                ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
            }

            if (ball.getBottomY() >= getAppHeight()) {
                ball.setY(getAppHeight() - BALL_SIZE);
                ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
            }
        }



    }

    public static void main(String[] args) {
        //Ask how many balls
        System.out.println("How many balls?");
        Scanner in = new Scanner(System.in);
        String ballChoice = in.nextLine();
        ballCount = Integer.parseInt(ballChoice);
        launch(args);
    }
}