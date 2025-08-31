import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLane {
    public Vec2 spawnPosition;
    protected List<Vehicle> vehicles = new ArrayList<>();
    public TrafficLights trafficLights;
    public final float width;
    public final float height;

    public BaseLane(float width, float height, TrafficLights lights) {
        this.width = width;
        this.height = height;
        this.trafficLights = lights;
    }

    public List<Vehicle> getVehicles() { return vehicles; }

    public Integer vehiclesCount(){
        return this.vehicles.size();
    }

    public abstract void spawnVehicle();

    public abstract void updateVehicles();

    public void receiveVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void drawVehicles(PApplet app) {
    for (Vehicle v : vehicles) {
        switch (v.getDirection()) {
            case Right -> app.fill(0, 0, 255);   
            case Left  -> app.fill(0, 255, 0);   
            case Straight -> app.fill(255, 255, 0); 
        }
        app.rect((float) v.getPosition().x, (float) v.getPosition().y, Vehicle.vehicleSize, Vehicle.vehicleSize);

    }
    
    }

    public boolean canSpawnAt() {
            float minDistance = Vehicle.vehicleSize * 2;
            for (Vehicle v : vehicles) {
                float d = (float) Math.hypot(this.spawnPosition.x - v.getPosition().x, this.spawnPosition.y - v.getPosition().y);
                if (d < minDistance) {
                    return false;
                }
            }
            return true;
        }
}
