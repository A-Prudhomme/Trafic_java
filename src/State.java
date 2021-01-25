//Created by Gnurtys

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class State {

    public State(Color color) {
        this.color = color;
    }

    private Color color;

    public State() {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
