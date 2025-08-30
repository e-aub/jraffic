import processing.core.PApplet;
import java.util.*;

public class Jraffic extends PApplet {
    List<Vehicle> vehicles = new ArrayList<Vehicle>();
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
        if (vehicles.size() != 0) {
            update();
            for (Vehicle v : vehicles) {
                switch (v.getDirection()) {
                    case Right: {
                        fill(255);
                        break;
                    }
                    case Left: {
                        fill(0, 255, 0);
                        break;
                    }
                    default: {
                        fill(0, 0, 255);
                    }

                }
                v.checkTourne();
                rect((float) v.getPosition().x, (float) v.getPosition().y, 50, 50);
            }
        }
        road();

    }

    public void update() {
        for (Vehicle v : vehicles) {
            if (v.getRoute() == Route.North) {
                v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y - v.getSpeed()));
            } else if (v.getRoute() == Route.South) {
                v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y + v.getSpeed()));
            } else if (v.getRoute() == Route.East) {
                v.setPosition(new Vec2(v.getPosition().x - v.getSpeed(), v.getPosition().y));
            } else if (v.getRoute() == Route.West) {
                v.setPosition(new Vec2(v.getPosition().x + v.getSpeed(), v.getPosition().y));
            }
        }
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

    public void keyPressed() {

        Route route = null;
        if (key == CODED || key == ' ') {
            if (key == ' ') {
                Random rand = new Random();
                int r = rand.nextInt(4);
                if (r == 0)
                    route = Route.North;
                else if (r == 1)
                    route = Route.South;
                else if (r == 2)
                    route = Route.East;
                else
                    route = Route.West;
            }

            if (key == CODED) {
                switch (keyCode) {
                    case UP:
                        route = Route.North;
                        break;
                    case DOWN:
                        route = Route.South;
                        break;
                    case LEFT:
                        route = Route.East;
                        break;
                    case RIGHT:
                        route = Route.West;
                        break;
                }
            }

            if (route != null) {
                Vec2 newPos = null;
                switch (route) {
                    case North:
                        newPos = new Vec2(width / 2, height - 50);
                        break;
                    case South:
                        newPos = new Vec2((width / 2) - 50, 0);
                        break;
                    case East:
                        newPos = new Vec2(width - 50, height / 2 - 50);
                        break;
                    case West:
                        newPos = new Vec2(0, height / 2);
                        break;
                }

                boolean canAdd = true;
                float minDistance = 100;
                for (Vehicle v : vehicles) {
                    float d = dist((float) newPos.x, (float) newPos.y, (float) v.getPosition().x,
                            (float) v.getPosition().y);

                    if (d < minDistance&&route==v.getRoute()) {
                        canAdd = false;
                        break;
                    }
                }

                if (canAdd) {
                    vehicles.add(new Vehicle(newPos, route));
                }
            }
        }

    }
}
