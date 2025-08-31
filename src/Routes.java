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
        eastLane = new EastLane(width, height, lights);
        westLane = new WestLane(width, height, lights);

        lanes = Arrays.asList(northLane, southLane, westLane, eastLane);
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

    public void update() {
       for (int i = 0; i < lanes.size(); i++) {
        BaseLane lane = lanes.get(i);
        // System.out.println("Updating lane " + i + ": " + lane.getClass().getSimpleName());
        lane.updateVehicles();
    }
        // Iterator<Vehicle> it = northLane.getVehicles().iterator();
        // while (it.hasNext()) {
        //     Vehicle v = it.next();
        //     if (!v.hasTurned()) {
        //         if (v.getDirection() == Direction.Right && v.getPosition().y == 500) {
        //             v.setTurned(true);
        //             it.remove();
        //             westLane.receiveVehicle(v);
        //         } else if (v.getDirection() == Direction.Left && v.getPosition().y == 450) {
        //             v.setTurned(true);
        //             it.remove();
        //             eastLane.receiveVehicle(v);
        //         }
        //     }
        //         if (v.getPosition().y < Vehicle.vehicleSize * -1) {
        //             it.remove();
        //         }
        // }

        // it = southLane.getVehicles().iterator();
        // while (it.hasNext()) {
        //     Vehicle v = it.next();
        //     if (!v.hasTurned()) {
        //         if (v.getDirection() == Direction.Right && v.getPosition().y == 450) {
        //             v.setTurned(true);
        //             it.remove();
        //             eastLane.receiveVehicle(v);
        //         } else if (v.getDirection() == Direction.Left && v.getPosition().y == 500) {
        //             v.setTurned(true);
        //             it.remove();
        //             westLane.receiveVehicle(v);
        //         }
        //     }
        //     if (v.getPosition().y > height + Vehicle.vehicleSize) {
        //         it.remove();
        //     }
        // }

        // it = eastLane.getVehicles().iterator();
        // while (it.hasNext()) {
        //     Vehicle v = it.next();
        //     if (!v.hasTurned()) {
        //         if (v.getDirection() == Direction.Right && v.getPosition().x == 500) {
        //             v.setTurned(true);
        //             it.remove();
        //             northLane.receiveVehicle(v);
        //         } else if (v.getDirection() == Direction.Left && v.getPosition().x == 450) {
        //             v.setTurned(true);
        //             it.remove();
        //             southLane.receiveVehicle(v);
        //         }
        //     }
        //     if (v.getPosition().x > width + Vehicle.vehicleSize){
        //         it.remove();
        //     }
        // }

        // it = westLane.getVehicles().iterator();
        // while (it.hasNext()) {
        //     Vehicle v = it.next();
        //     if (!v.hasTurned()) {
        //         if (v.getDirection() == Direction.Right && v.getPosition().x == 450) {
        //             v.setTurned(true);
        //             it.remove();
        //             southLane.receiveVehicle(v);
        //         } else if (v.getDirection() == Direction.Left && v.getPosition().x == 500) {
        //             v.setTurned(true);
        //             it.remove();
        //             northLane.receiveVehicle(v);
        //         }
        //     }
        //     if (v.getPosition().x < Vehicle.vehicleSize * -1){
        //         it.remove();
        //     }
        // }
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
        for (BaseLane lane : lanes)
            lane.drawVehicles(app);
    }
}
