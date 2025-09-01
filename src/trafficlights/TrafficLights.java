package trafficlights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vehicles.*;
import routing.*;


import processing.core.PApplet;
import utils.Vec2;

public class TrafficLights {
    private Map<Route, TrafficLight> lights = new HashMap<>();

    public TrafficLights(Integer width, Integer height) {
        lights.put(Route.North,
                new TrafficLight(new Vec2((width / 2) + Vehicle.vehicleSize, (height / 2) + Vehicle.vehicleSize),
                        LightState.RED, Route.North));
        lights.put(Route.South,
                new TrafficLight(
                        new Vec2((width / 2) - Vehicle.vehicleSize * 2, (height / 2) - Vehicle.vehicleSize * 2),
                        LightState.RED, Route.South));
        lights.put(Route.East,
                new TrafficLight(new Vec2((width / 2) + Vehicle.vehicleSize, (height / 2) - Vehicle.vehicleSize * 2),
                        LightState.RED, Route.East));
        lights.put(Route.West,
                new TrafficLight(new Vec2((width / 2) - Vehicle.vehicleSize * 2, (height / 2) + Vehicle.vehicleSize),
                        LightState.RED, Route.West));
    }

    public Map<Route, TrafficLight> getLights() {
        return lights;
    }

    private long lastUpdate;
    private long calculatedTime;
    private int current_idx = 4;
    private int time = 5000;
    private boolean all_red = true;
    private List<TrafficLight> turns;

    public void update(Routes routes) {
        long now = System.currentTimeMillis();

        if (turns == null) {
            turns = new ArrayList<>(lights.values());
            current_idx = 0;
            lastUpdate = now;
            all_red = true;
        }

        if (now - lastUpdate < calculatedTime
                || routes.getLane(turns.get(current_idx).getRoute()).vehicles.size() < 0) {
            return;
        }

        if (all_red) {
            TrafficLight currentLight = turns.get(current_idx);

            int carCount = routes.getLane(currentLight.getRoute()).vehiclesCount();
            int totalCars = turns.stream()
                    .mapToInt(l -> routes.getLane(l.getRoute()).vehiclesCount())
                    .sum();

            calculatedTime = (carCount > 0) ? time * carCount / Math.max(1, totalCars) : 0;

            for (TrafficLight light : turns) {
                light.setState(light.equals(currentLight) && calculatedTime > 0
                        ? LightState.GREEN
                        : LightState.RED);
            }

            current_idx = (current_idx + 1) % turns.size();
        } else {
            for (TrafficLight light : turns) {
                light.setState(LightState.RED);
            }
            calculatedTime = 1000;
        }

        all_red = !all_red;
        lastUpdate = now;
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
            app.rect((float) light.getPosition().x, (float) light.getPosition().y, Vehicle.vehicleSize,
                    Vehicle.vehicleSize);
        }
    }

}
