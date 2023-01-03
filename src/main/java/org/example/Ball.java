package org.example;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class Ball {
    public Entity globalBall;
    public Ball(Integer x, Integer y, Integer BALL_SIZE, Integer BALL_SPEED, Color color) {
        globalBall = entityBuilder()
                .at(x, y)
                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, color))
                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .buildAndAttach();
    }

    public Entity object() {
        return globalBall;
    }
}
