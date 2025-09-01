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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Integer vehiclesCount() {
        return fillter().size();
    }

    public List<Vehicle> fillter() {
        if (vehicles.size() == 0) {
            return vehicles;
        }

        List<Vehicle> carrr = new ArrayList<>();
        Route r = vehicles.get(0).getRoute();
        TrafficLight l = trafficLights.getLight(r);

        for (Vehicle vehicle : vehicles) {
            if (!vehicle.doz()) {
                carrr.add(vehicle);
            }
        }
        if (carrr.size() == 0) {
            return new ArrayList<>();
        }
        switch (r) {
            case South:
                if (carrr.get(0).getPosition().y < l.getPosition().y) {
                    return new ArrayList<>();
                }
                break;
            case North:
                if (carrr.get(0).getPosition().y > l.getPosition().y) {
                    return new ArrayList<>();
                }
                break;
            case East:
            System.out.println(carrr.get(0).getPosition().x );
                if (carrr.get(0).getPosition().x > l.getPosition().x) {
                    return new ArrayList<>();
                }
                break;
            default:
            System.out.println(carrr.get(0).getPosition().x );

                if (carrr.get(0).getPosition().x < l.getPosition().x) {
                    return new ArrayList<>();
                }
                break;
        }
        return carrr;
    }

    public abstract void spawnVehicle();

    public abstract void advanceVehicles();

    public abstract void TurnVehicles(Routes routes);

    public void receiveVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void drawVehicles(PApplet app) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            switch (v.getDirection()) {
                case Right -> app.fill(0, 0, 255);
                case Left -> app.fill(0, 255, 0);
                case Straight -> app.fill(255, 255, 0);
            }
            float x = v.getPosition().x;
            float y = v.getPosition().y;
            if (x < -50 || y < -50 || y > 700*1.02 || x > 700*1.02) {
                vehicles.remove(i);
                i--;
                continue;
            }
            app.rect((float) x, (float) y, Vehicle.vehicleSize, Vehicle.vehicleSize);

        }

    }

    public boolean canSpawnAt() {
        float minDistance = Vehicle.vehicleSize * 2;
        for (Vehicle v : vehicles) {
            float d = (float) Math.hypot(this.spawnPosition.x - v.getPosition().x,
                    this.spawnPosition.y - v.getPosition().y);
            if (d < minDistance) {
                return false;
            }
        }
        return true;
    }
}
