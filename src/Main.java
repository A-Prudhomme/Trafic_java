import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {

    long tempo = 100;
    long traffic_light_change = 12000;
    Node[][] node;
    int nbNode = 4;
    int nbSubnode =50;
    TrafficNetwork network = new TrafficNetwork(nbNode, nbSubnode);

    public static void main(String[] args) {
        launch(args);
    }

    public void animDeplacement(Group root){
        /*reset(root);*/
        Timeline lights = new Timeline();
        KeyFrame bougeVoiture = new KeyFrame(new Duration(12000), event-> {network.change_light(root);});
        lights.getKeyFrames().add(bougeVoiture);
        lights.play();
        network.vehicleForward(nbNode,nbSubnode);
        print_cars(root);

    }
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Traffic Management");
        //Définition des noeuds

        Group root = new Group();

        print_nodes(root);
        print_subnodes(root);
        stage.setScene(new Scene(root, 600, 600, Color.DARKGREY));
        stage.show();

        Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo),
                event-> {animDeplacement(root);
                }) );
        littleCycle.setCycleCount(Timeline.INDEFINITE);
        littleCycle.play();


    }

    private void print_cars(Group root) {
        for (Vehicle vehicleToDraw: network.getVehicles()) {
            Timeline timeline = new Timeline();
            KeyFrame bougeVoiture = new KeyFrame(new Duration(tempo), event-> {vehicleToDraw.draw(root);});
            timeline.getKeyFrames().add(bougeVoiture);
            timeline.play();
            vehicleToDraw.setAnimation(timeline);
        }

        /*network.vehicleForward(nbNode,nbSubnode);
        for (Vehicle vehicleToDraw: network.getVehicles()){
            vehicleToDraw.draw(root);
        }*/
    }

    private void print_subnodes(Group root) {
        for (SubNode subnodeToDraw: network.getSubnodes()){
            subnodeToDraw.draw(root);

        }
    }

    private void print_nodes(Group root) {
        node = network.getNodes();
        for (Node[] listToDraw: node) {
            for (Node nodeToDraw: listToDraw) {
                nodeToDraw.draw(root);
            }
        }
    }

/*    private void reset(Group root) {
        Rectangle back = new Rectangle(0,0,1000,600);
        root.getChildren().add(back);
    }*/


}
