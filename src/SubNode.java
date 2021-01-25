//Created by Gnurtys

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SubNode {
    private double x;
    private double y;
    private Node est;
    private Node north;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public SubNode(double x, double y, Node est, Node north) {
        this.x = x;
        this.y = y;
        this.est = est;
        this.north = north;
    }


    public void draw(Group root){
        Circle subnode = new Circle(this.getX(), this.getY(), 4, Color.BISQUE);
        root.getChildren().add(subnode);
    }
}
