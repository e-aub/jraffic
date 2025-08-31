import processing.core.PApplet;
import java.util.Random;

public class Jraffic extends PApplet {
    protected Routes routes;
    protected TrafficLights trafficLights;

    public static void main(String[] args) {
        PApplet.main("Jraffic");
    }

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        background(0);
        trafficLights = new TrafficLights(width, height);
        routes = new Routes(width, height, trafficLights);
    }

    public void draw() {
        background(0);
        // this.trafficLights.update();
        routes.update();
        routes.draw(this);

        trafficLights.draw(this);
    }


    public void keyPressed() {
        Route route = null;

        if (key == ' ') {
            route = Route.values()[new Random().nextInt(4)];
        } else if (key == CODED) {
            System.out.println(key);
            switch (keyCode) {
                case UP -> route = Route.North;
                case DOWN -> route = Route.South;
                case LEFT -> route = Route.East;
                case RIGHT -> route = Route.West;
            }
        }

        if (route != null) {
            routes.spawnVehicle(route);
        }
    }
}
