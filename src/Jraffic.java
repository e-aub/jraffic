import processing.core.PApplet;
import java.util.*;

public class Jraffic extends PApplet {
    // List<Vehicle> vehicles = new ArrayList<Vehicle>();
    List<TrafficLight> trafficLights = new ArrayList<TrafficLight>();

    public static void main(String[] args) {
        PApplet.main("Jraffic");
    }

    public void settings() {
        size(1000, 1000);

    }

    public void setup() {
        background(0);
        rectM();
    }

    public void draw() {
        background(0);
        road();

    }

    public void rectM() {
        trafficLights.add(new TrafficLight(new Vec2((width / 2) + 50, (height / 2) + 50), LightState.RED, Route.North));
        trafficLights
                .add(new TrafficLight(new Vec2((width / 2) - 100, (height / 2) - 100), LightState.RED, Route.South));
        trafficLights.add(new TrafficLight(new Vec2((width / 2) + 50, (height / 2) - 100), LightState.RED, Route.East));
        trafficLights.add(new TrafficLight(new Vec2((width / 2) - 100, (height / 2) + 50), LightState.RED, Route.West));

    }

    public void road() {
        stroke(255);
        line(0, height / 2, width, height / 2);
        line(width / 2, 0, width / 2, height);
        line(0, (height / 2) + 50, width, (height / 2) + 50);
        line(0, (height / 2) - 50, width, (height / 2) - 50);
        line((width / 2) + 50, 0, (width / 2) + 50, height);
        line((width / 2) - 50, 0, (width / 2) - 50, height);
        fill(255, 0, 0);
        for (TrafficLight tl : trafficLights) {
            if (tl.getState() == LightState.RED) {
                fill(255, 0, 0);
            } else {
                fill(0, 255, 0);
            }
           
            rect((float) tl.getPosition().x, (float) tl.getPosition().y, 50, 50);
        }
    }
}
