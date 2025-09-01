package lanes;

import java.util.Iterator;

import utils.Vec2;
import trafficlights.*;
import vehicles.*;
import routing.*;


public class SouthLane extends BaseLane {
    public SouthLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
        spawnPosition = new Vec2((width / 2) - Vehicle.vehicleSize, 0);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()) {
            vehicles.add(new Vehicle(this.spawnPosition, Route.South));
        }
    }

    public void advanceVehicles() {
        TrafficLight light = trafficLights.getLight(Route.South);

        int pv = 0;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            float stoppingPosition = light.getPosition().y - ((i - pv) * Vehicle.vehicleSize * 2);
            if (v.doz()) {
                pv++;
            }
            if (light.getState() == LightState.GREEN || v.doz() ||
                    (light.getState() == LightState.RED && v.getPosition().y + v.getSpeed()<= stoppingPosition)) {
                v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y + v.getSpeed()));
            }
        }
    }

    @Override
    public void TurnVehicles(Routes routes) {
        Iterator<Vehicle> it = this.vehicles.iterator();
        while (it.hasNext()) {
            Vehicle v = it.next();
            if (!v.hasTurned()) {
                if (v.getDirection() == Direction.Right && v.getPosition().y == 700/2 - Vehicle.vehicleSize) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.East).receiveVehicle(v);
                } else if (v.getDirection() == Direction.Left && v.getPosition().y == 700/2) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.West).receiveVehicle(v);
                }
            }
            if (v.getPosition().y > height + Vehicle.vehicleSize) {
                it.remove();
            }
        }
    }
}
