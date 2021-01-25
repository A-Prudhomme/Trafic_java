import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Vehicle {
    private double x;
    private double y;
    private double speed;
    private Boolean breaking;
    private Boolean horizontal;
    public SubNode subnode;
    private int[] node;
    private Boolean done = false;

    public Vehicle(double x, double y, double speed, boolean horizontal, int[] node) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.horizontal = horizontal;
        this.node = node;
        this.breaking = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw(Group root) {
        if (this.horizontal) {
            Rectangle car = new Rectangle(20, 10, Color.BLUE);
            Rectangle light = new Rectangle(2, 10, Color.YELLOW);
            car.setX(this.getX() - 10);
            car.setY(this.getY()-5);
            light.setX(this.getX() + 10);
            light.setY(this.getY()-5);
            root.getChildren().add(car);
            root.getChildren().add(light);
        }
        else{
            Rectangle car = new Rectangle(10, 20, Color.BLUE);
            Rectangle light = new Rectangle(10, 2, Color.YELLOW);
            car.setX(this.getX() - 5);
            car.setY(this.getY() - 10);
            light.setX(this.getX() - 5);
            light.setY(this.getY() - 10);
            root.getChildren().add(car);
            root.getChildren().add(light);
        }
    }

    public Boolean getHorizontal() {
        return horizontal;
    }

    public SubNode getSubnode() {
        return subnode;
    }

    public int[] getNode() {
        return node;
    }

    public void setNode(int[] node) {
        this.node = node;
    }

    public void done(){
        this.done = true;
    }

    public void setPosition(){
        if(this.subnode != null){
            this.x = this.subnode.getX();
            this.y = this.subnode.getY();
        }
    }

    public void setPosition(Node node){
        this.x = node.getX();
        this.y = node.getY();
    }

    public Boolean getDone() {
        return done;
    }
}
