import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLane {
    protected List<Vehicle> vehicles = new ArrayList<>();
    public final double width;
    public final double height;

    public BaseLane(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public List<Vehicle> getVehicles() { return vehicles; }

    public Integer vehiclesCount(){
        return this.vehicles.size();
    }

    public abstract void spawnVehicle();

    public abstract void updateVehicles();

    public abstract void drawVehicles(PApplet app);
}
