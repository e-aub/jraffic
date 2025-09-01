package routing;

import java.util.*;
import processing.core.PApplet;
import lanes.*;
import trafficlights.*;
import vehicles.*;


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
        eastLane = new EastLane(width, height, lights);
        westLane = new WestLane(width, height, lights);

        lanes = Arrays.asList(northLane, southLane, westLane, eastLane);
    }

    public BaseLane getLane(Route r) {
        switch (r) {
            case South:
                return southLane;
            case West:
                return westLane;
            case East:
                return eastLane;
            default:
                return northLane;
        }
    }

    public void spawnVehicle(Route route) {
        switch (route) {
            case North -> northLane.spawnVehicle();
            case South -> southLane.spawnVehicle();
            case East -> eastLane.spawnVehicle();
            case West -> westLane.spawnVehicle();
        }
    }

    public void spawnVehicle(Vehicle vehicle, Route route) {
        switch (route) {
            case North -> northLane.spawnVehicle();
            case South -> southLane.spawnVehicle();
            case East -> eastLane.spawnVehicle();
            case West -> westLane.spawnVehicle();
        }
    }

    public List<BaseLane> getRoutes() {
        return this.lanes;
    }

    public void update() {
        for (int i = 0; i < lanes.size(); i++) {
            BaseLane lane = lanes.get(i);
            // System.out.println("Updating lane " + i + ": " +
            // lane.getClass().getSimpleName());
            lane.advanceVehicles();
            lane.TurnVehicles(this);
        }
        // Here we should delete the turning logic and implement for each lane its
        // turning logic;
    }

    public void draw(PApplet app) {
        app.stroke(255);
        app.line(0, height / 2, width, height / 2);
        app.line(width / 2, 0, width / 2, height);
        app.line(0, (height / 2) + Vehicle.vehicleSize, width, (height / 2) + Vehicle.vehicleSize);
        app.line(0, (height / 2) - Vehicle.vehicleSize, width, (height / 2) - Vehicle.vehicleSize);
        app.line((width / 2) + Vehicle.vehicleSize, 0, (width / 2) + Vehicle.vehicleSize, height);
        app.line((width / 2) - Vehicle.vehicleSize, 0, (width / 2) - Vehicle.vehicleSize, height);
        app.fill(255, 0, 0);
        for (BaseLane lane : lanes)
            lane.drawVehicles(app);
    }
}
