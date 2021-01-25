//Created by Gnurtys

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrafficLight {
    private double x;
    private double y;
    private String position;
    private State state;

    public TrafficLight(double x, double y, String position, State state) {
        this.x = x;
        this.y = y;
        this.position = position;
        this.state = state;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public State getState() {
        return state;
    }

    public void draw(Group root) {
        Circle white_circle = new Circle(this.getX(), this.getY(), 4, Color.WHITE);
        Circle colored_circle = new Circle(this.getX(), this.getY(), 3, this.getState().getColor());
        root.getChildren().add(white_circle);
        root.getChildren().add(colored_circle);
    }
}
