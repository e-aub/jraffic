public class EastLane extends BaseLane {
    public EastLane(float width, float height, TrafficLights trafficLights) {
        super(width, height,trafficLights);
    }

   @Override
    public void spawnVehicle() {
        Vec2 newPos = new Vec2(width - 50, height / 2 - Vehicle.vehicleSize); 
        boolean canAdd = true;
        float minDistance = Vehicle.vehicleSize * 2;

        for (Vehicle v : vehicles) {
                float d = (float) Math.hypot(newPos.x - v.getPosition().x, newPos.y - v.getPosition().y);
                if (d < minDistance) {
                    canAdd = false;
                    break;
                }
        }

        if (canAdd) vehicles.add(new Vehicle(newPos));
    }


    @Override
    public void updateVehicles() {
        TrafficLight light = trafficLights.getLight(Route.East);

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            float frontX = (i > 0) ? vehicles.get(i - 1).getPosition().x - Vehicle.vehicleSize : 0;

            if (light.getState() == LightState.GREEN || (light.getState() == LightState.RED && v.getPosition().x >= light.getPosition().x)) {
                if (v.getPosition().x > frontX) {
                    v.setPosition(new Vec2(v.getPosition().x - v.getSpeed(), v.getPosition().y));
                }
            }
        }
    }
}
