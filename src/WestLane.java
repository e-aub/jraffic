import java.util.Iterator;

public class WestLane extends BaseLane {
    public WestLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
        spawnPosition = new Vec2(0, height / 2);
    }

    @Override
    public void spawnVehicle() {
        System.out.println("WestLane.spawnVehicle() called!");
        System.out.println("canSpawnAt(): " + canSpawnAt());
        System.out.println("Current vehicles count: " + vehicles.size());

        if (canSpawnAt()) {
            vehicles.add(new Vehicle(this.spawnPosition, Route.West));
            System.out.println("Vehicle added! New count: " + vehicles.size());
        } else {
            System.out.println("Cannot spawn - blocked by canSpawnAt()");
        }
    }

    @Override
    public void advanceVehicles() {
        try {
            TrafficLight light = trafficLights.getLight(Route.West);

            int pv = 0;
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle v = vehicles.get(i);
                float stoppingPosition = light.getPosition().x - ((i - pv) * Vehicle.vehicleSize * 2);
                if (v.doz()) {
                    pv++;
                }
                if (light.getState() == LightState.GREEN || v.doz() ||
                        (light.getState() == LightState.RED && v.getPosition().x + v.getSpeed()<= stoppingPosition)) {
                    v.setPosition(new Vec2(v.getPosition().x + v.getSpeed(), v.getPosition().y));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void TurnVehicles(Routes routes) {
        Iterator<Vehicle> it = this.vehicles.iterator();
        while (it.hasNext()) {
            Vehicle v = it.next();
            if (!v.hasTurned()) {
                if (v.getDirection() == Direction.Right && v.getPosition().x == width/2 - Vehicle.vehicleSize) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.South).receiveVehicle(v);
                } else if (v.getDirection() == Direction.Left && v.getPosition().x == width/2 ) {
                    v.setTurned(true);
                    it.remove();
                    routes.getLane(Route.North).receiveVehicle(v);
                }
            }
            if (v.getPosition().x < Vehicle.vehicleSize * -1) {
                it.remove();
            }
        }
    }
}
