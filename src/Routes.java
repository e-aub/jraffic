import java.util.*;
import processing.core.PApplet;

public class Routes {
    private NorthLane northLane;
    private SouthLane southLane;
    private EastLane eastLane;
    private WestLane westLane;

    private List<BaseLane> lanes;

    private float width, height;

    public Routes(float width, float height, TrafficLights lights) {
        this.width = width;
        this.height = height;

        northLane = new NorthLane(width, height, lights);
        southLane = new SouthLane(width, height, lights);
        eastLane  = new EastLane(width, height, lights);
        westLane  = new WestLane(width, height, lights);

        lanes = Arrays.asList(northLane, southLane, eastLane, westLane);
    }

    public void spawnVehicle(Route route) {
        switch (route) {
            case North -> northLane.spawnVehicle();
            case South -> southLane.spawnVehicle();
            case East  -> eastLane.spawnVehicle();
            case West  -> westLane.spawnVehicle();
        }
    }

    public void spawnVehicle(Vehicle vehicle, Route route) {
        switch (route) {
            case North -> northLane.spawnVehicle();
            case South -> southLane.spawnVehicle();
            case East  -> eastLane.spawnVehicle();
            case West  -> westLane.spawnVehicle();
        }
    }

    public void update() {
        for (BaseLane lane : lanes) lane.updateVehicles();
    }

    public void draw(PApplet app) {
        app.stroke(255);
        app.line(0, height / 2, width, height / 2);
        app.line(width / 2, 0, width / 2, height);
        app.line(0, (height / 2) + 50, width, (height / 2) + 50);
        app.line(0, (height / 2) - 50, width, (height / 2) - 50);
        app.line((width / 2) + 50, 0, (width / 2) + 50, height);
        app.line((width / 2) - 50, 0, (width / 2) - 50, height);
        app.fill(255, 0, 0);
        for (BaseLane lane : lanes) lane.drawVehicles(app);
    }
}
