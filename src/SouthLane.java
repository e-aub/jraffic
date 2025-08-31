public class SouthLane extends BaseLane {
    public SouthLane(float width, float height, TrafficLights trafficLights) {
        super(width, height, trafficLights);
    }

    @Override
    public void spawnVehicle() {
        Vec2 newPos = new Vec2((width / 2) - Vehicle.vehicleSize, 0);
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
        TrafficLight light = trafficLights.getLight(Route.South);

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            float frontY = (i > 0) ? vehicles.get(i - 1).getPosition().y + Vehicle.vehicleSize : height;

            if (light.getState() == LightState.GREEN || (light.getState() == LightState.RED && v.getPosition().y <= light.getPosition().y)) {
                if (v.getPosition().y < frontY) {
                    v.setPosition(new Vec2(v.getPosition().x, v.getPosition().y + v.getSpeed()));
                }
            }
        }
    }
}
