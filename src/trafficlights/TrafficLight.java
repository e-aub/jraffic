package trafficlights;

import utils.Vec2;
import routing.*;


public class TrafficLight {
    private Vec2 position;
    private LightState state;
    private Route route;
    public Route getRoute() {
        return route;
    }

    public TrafficLight(Vec2 position, LightState state,Route route) {
        this.position = position;
        this.state = state;
        this.route = route;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public LightState getState() {
        return state;
    }

    public void setState(LightState state) {
        this.state = state;
    }
}
