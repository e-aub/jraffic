import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;

public class TrafficLights {
    private Map<Route, TrafficLight> lights = new HashMap<>();

    public TrafficLights(Integer width, Integer height) {
        lights.put(Route.North,
                new TrafficLight(new Vec2((width / 2) + 50, (height / 2) + 50), LightState.RED, Route.North));
        lights.put(Route.South,
                new TrafficLight(new Vec2((width / 2) - 100, (height / 2) - 100), LightState.RED, Route.South));
        lights.put(Route.East,
                new TrafficLight(new Vec2((width / 2) + 50, (height / 2) - 100), LightState.RED, Route.East));
        lights.put(Route.West,
                new TrafficLight(new Vec2((width / 2) - 100, (height / 2) + 50), LightState.RED, Route.West));
    }

    public Map<Route, TrafficLight> getLights() {
        return lights;
    }

    private long lastUpdate;
    private long claculatedTime;
    private int current_idx = 4;
    private int time = 1000;
    private boolean all_red = true;
    private List<TrafficLight> turns;

    public void update(Routes routes) {
        if (current_idx == 4) {
            turns = new ArrayList<>();
            getLights().values().forEach((v) -> {
                turns.add(v);
            });
            current_idx = 0;
        } else if ((System.currentTimeMillis() - lastUpdate) >= claculatedTime) {
            if (all_red) {
                TrafficLight currentLight = turns.get(current_idx);
                int carCount = routes.getBaseLane(currentLight.getRoute()).vehiclesCount();
                int[] all_cars = new int[] { 0 };
                turns.forEach(t -> {
                    all_cars[0] += routes.getBaseLane(t.getRoute()).vehiclesCount();
                });
                claculatedTime = time * carCount / ((all_cars[0] == 0) ? 1 : all_cars[0]);
                current_idx++;
                turns.forEach(l -> {
                    l.setState((claculatedTime != 0 && l.equals(currentLight)) ? LightState.GREEN : LightState.RED);
                });
            } else if (claculatedTime!=0) {
                turns.forEach(l -> {
                    l.setState(LightState.RED);
                });
                claculatedTime = 100;
            }
            all_red = !all_red;
            lastUpdate = System.currentTimeMillis();

        }
    }

    public TrafficLight getLight(Route route) {
        return lights.get(route);
    }

    public LightState getLightState(Route route) {
        return this.lights.get(route).getState();
    }

    public void turnGreenFor(Route route) {
        this.turnRedForAll();
        TrafficLight greenLight = lights.get(route);
        if (greenLight != null) {
            greenLight.setState(LightState.GREEN);
        }
    }

    public void turnRedForAll() {
        for (Map.Entry<Route, TrafficLight> entry : lights.entrySet()) {
            entry.getValue().setState(LightState.RED);
        }

    }

    public void draw(PApplet app) {
        for (Map.Entry<Route, TrafficLight> entry : this.lights.entrySet()) {
            TrafficLight light = entry.getValue();
            if (light.getState() == LightState.RED) {
                app.fill(255, 0, 0);
            } else {
                app.fill(0, 255, 0);
            }
            app.rect((float) light.getPosition().x, (float) light.getPosition().y, 50, 50);
        }
    }

}
