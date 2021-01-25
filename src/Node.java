//Created by Gnurtys

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
    private double x;
    private double y;

    private List<TrafficLight> light;
    private int tick;


    public Node(double x, double y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.tick = rand.nextInt(5000)+10000;
        this.light = createFeu(x,y);
    }

    public List<TrafficLight> createFeu(double x, double y){
        List<TrafficLight> listLight = new ArrayList<>();
        State horState = new State();
        State verState = new State();
        if (this.tick > 12500){
            horState.setColor(Color.GREEN);
            verState.setColor(Color.RED);
        }else{
            verState.setColor(Color.GREEN);
            horState.setColor(Color.RED);
        }
        int size = 15;
       /* TrafficLight lighttl = new TrafficLight(x-size*0.5,y-size,"top-left", verState);
        TrafficLight lighttr = new TrafficLight(x+size,y-size*0.5,"top-right", horState);*/
        TrafficLight lightbl = new TrafficLight(x-size,y+size*0.5,"bottom-left", horState);
        TrafficLight lightbr = new TrafficLight(x+size*0.5,y+size,"bottom-right", verState);
/*        listLight.add(lighttl);
        listLight.add(lighttr);*/
        listLight.add(lightbl);
        listLight.add(lightbr);
        return listLight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public List<TrafficLight> getLight() {
        return light;
    }

    public void draw(Group root){
        for (TrafficLight light: this.getLight()){
            light.draw(root);
        }
        Rectangle ver = new Rectangle(10,40, Color.BLACK);
        Rectangle hor = new Rectangle(40,10, Color.BLACK);
        ver.setX(this.getX()-5);
        ver.setY(this.getY()-20);
        hor.setX(this.getX()-20);
        hor.setY(this.getY()-5);
        root.getChildren().add(ver);
        root.getChildren().add(hor);
    }
}
