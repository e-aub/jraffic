import java.util.HashMap;
import java.util.Map;

public class TrafficLights {
    private Map<Route, TrafficLight> lights = new HashMap<>();

    public TrafficLights(Integer width, Integer height) {
        lights.put(Route.North, new TrafficLight(new Vec2((width / 2) + 50, (height / 2) + 50), LightState.RED));
        lights.put(Route.South, new TrafficLight(new Vec2((width / 2) - 100, (height / 2) - 100), LightState.RED));
        lights.put(Route.East, new TrafficLight(new Vec2((width / 2) + 50, (height / 2) - 100), LightState.RED));
        lights.put(Route.West, new TrafficLight(new Vec2((width / 2) - 100, (height / 2) + 50), LightState.RED));
    }

    public Map<Route, TrafficLight> getLights() {
        return lights;
    }

    public TrafficLight getLight(Route route){
        return lights.get(route);
    }

    public LightState getLightState(Route route){
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

}
