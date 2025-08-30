import processing.core.PApplet;
import java.util.*;

public class Jraffic extends PApplet {
    // List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public static void main(String[] args) {
        PApplet.main("Jraffic");
    }

    public void settings() {
        size(1000, 1000);

    }

    public void setup() {
        background(0);
    }

    public void draw() {
        background(0);
        road();
        rectM();
    }

    public void rectM() {
        noFill();
        stroke(255, 0, 0);
        rect((width / 2) + 50, (height / 2) + 50, 50, 50);
        rect((width / 2) - 100, (height / 2) - 100, 50, 50);
        rect((width / 2) + 50, (height / 2) - 100, 50, 50);
        rect((width / 2) - 100, (height / 2) + 50, 50, 50);
    }

    public void road() {
        stroke(255);
        line(0, height / 2, width, height / 2);
        line(width / 2, 0, width / 2, height);
        line(0, (height / 2) + 50, width, (height / 2) + 50);
        line(0, (height / 2) - 50, width, (height / 2) - 50);
        line((width / 2) + 50, 0, (width / 2) + 50, height);
        line((width / 2) - 50, 0, (width / 2) - 50, height);
    }
}
