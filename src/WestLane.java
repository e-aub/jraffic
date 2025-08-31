public class WestLane extends BaseLane {
    public WestLane(float width, float height, TrafficLights trafficLights) {
        super(width, height,trafficLights);
        spawnPosition = new Vec2(0, height / 2);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()){
            vehicles.add(new Vehicle(this.spawnPosition));
        } 
    }

    @Override
    public void updateVehicles() {
        TrafficLight light = trafficLights.getLight(Route.West);

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
             if (light.getState() == LightState.GREEN || (light.getState() == LightState.RED && v.getPosition().x >= light.getPosition().x)) {
                v.setPosition(new Vec2(v.getPosition().x + v.getSpeed(), v.getPosition().y));
            }
        }
    }
}
