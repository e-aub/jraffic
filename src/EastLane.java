public class EastLane extends BaseLane {
    public EastLane(float width, float height, TrafficLights trafficLights) {
        super(width, height,trafficLights);
        spawnPosition = new Vec2(width - 50, height / 2 - Vehicle.vehicleSize);
    }

    @Override
    public void spawnVehicle() {
        if (canSpawnAt()){
            vehicles.add(new Vehicle(this.spawnPosition));
        } 
    }


    @Override
   public void updateVehicles() {
    TrafficLight light = trafficLights.getLight(Route.East);
    
    for (int i = 0; i < vehicles.size(); i++) {
        Vehicle v = vehicles.get(i);
        
        float stoppingPosition = light.getPosition().x + (i * Vehicle.vehicleSize * 2);
        
        if (light.getState() == LightState.GREEN || 
            (light.getState() == LightState.RED && v.getPosition().x >= stoppingPosition)) {
            v.setPosition(new Vec2(v.getPosition().x - v.getSpeed(), v.getPosition().y));
        }
    }
}
}
