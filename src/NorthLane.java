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
                    (light.getState() == LightState.RED && v.getPosition().y >= stoppingPosition)) {
                        // System.out.println(stoppingPosition);
                        // System.out.println(v.getPosition().y);
                v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y - v.getSpeed()));
            }
        }
    }
}
