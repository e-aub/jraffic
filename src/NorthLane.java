import java.util.Iterator;

public class NorthLane extends BaseLane {
    public NorthLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
        this.spawnPosition = new Vec2(width / 2, height - Vehicle.vehicleSize);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()) {
            vehicles.add(new Vehicle(this.spawnPosition, Route.North));
        }
    }

    @Override
    public void updateVehicles() {
        TrafficLight light = trafficLights.getLight(Route.North);
        int pv = 0;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            float stoppingPosition = light.getPosition().y + ((i - pv) * Vehicle.vehicleSize * 2);
            if (v.doz()) {
                pv++;
            }
            if (light.getState() == LightState.GREEN || v.doz() ||
                    (light.getState() == LightState.RED && v.getPosition().y - v.getSpeed() >= stoppingPosition)) {
                        // System.out.println(stoppingPosition);
                        // System.out.println(v.getPosition().y);
                v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y - v.getSpeed()));
            }
        }
    }

    @Override
    public void handleTurns(Routes routes) {
        Iterator<Vehicle> it = this.vehicles.iterator();
        while (it.hasNext()) {
            Vehicle v = it.next();
            if (!v.hasTurned()) {
                if (v.getDirection() == Direction.Right && v.getPosition().y ==700 / 2) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.West).receiveVehicle(v);
                } else if (v.getDirection() == Direction.Left && v.getPosition().y == 700/2 - Vehicle.vehicleSize) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.East).receiveVehicle(v);
                }
            }
            if (v.getPosition().y < Vehicle.vehicleSize * -1) {
                it.remove();
            }
        }   
    }
}
