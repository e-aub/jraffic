public class SouthLane extends BaseLane {
    public SouthLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
        spawnPosition = new Vec2((width / 2) - Vehicle.vehicleSize, 0);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()){
            vehicles.add(new Vehicle(this.spawnPosition, Route.South));
        } 
    }

   public void updateVehicles() {
    TrafficLight light = trafficLights.getLight(Route.South);
    
    
    for (int i = 0; i < vehicles.size(); i++) {
        Vehicle v = vehicles.get(i);
        
        float stoppingPosition = light.getPosition().y - (i * Vehicle.vehicleSize * 2);
        
        if (light.getState() == LightState.GREEN || v.doz()||
            (light.getState() == LightState.RED && v.getPosition().y <= stoppingPosition)) {
            v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y + v.getSpeed()));
        }
    }
}
}
