import java.util.Iterator;

public class EastLane extends BaseLane {
    public EastLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
        spawnPosition = new Vec2(width - Vehicle.vehicleSize, height / 2 - Vehicle.vehicleSize);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()) {
            vehicles.add(new Vehicle(this.spawnPosition, Route.East));
        }
    }

    @Override
    public void advanceVehicles() {
        TrafficLight light = trafficLights.getLight(Route.East);
        int pv = 0;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            float stoppingPosition = light.getPosition().x + ((i - pv) * Vehicle.vehicleSize * 2);
            if (v.doz()) {
                pv++;
            }
            if (light.getState() == LightState.GREEN || v.doz() ||
                    (light.getState() == LightState.RED && v.getPosition().x- v.getSpeed() >= stoppingPosition)) {
                v.setPosition(new Vec2(v.getPosition().x - v.getSpeed(), v.getPosition().y));
            }
        }
    }

    @Override
    public void TurnVehicles(Routes routes){
        Iterator<Vehicle> it = this.vehicles.iterator();
        while (it.hasNext()) {
            Vehicle v = it.next();
            if (!v.hasTurned()) {
                if (v.getDirection() == Direction.Right && v.getPosition().x == width/2 ) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.North).receiveVehicle(v);
                } else if (v.getDirection() == Direction.Left && v.getPosition().x == width/2 - Vehicle.vehicleSize) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.South).receiveVehicle(v);
                }
            }
            if (v.getPosition().x > width + Vehicle.vehicleSize) {
                it.remove();
            }
        }
    }
}
