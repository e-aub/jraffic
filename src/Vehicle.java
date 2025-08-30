import java.util.Random;

import java.util.Random;

public class Vehicle {
    private Vec2 position;
    private Route route;
    private final double speed = 1.0;
    private boolean isMoving = true;
    private Direction direction;
    private boolean hasTurned = false;

    public Vehicle(Vec2 position, Route route) {
        this.position = position;
        this.route = route;
        Random rand = new Random();
        int index = rand.nextInt(3);

        switch (index) {
            case 0:
                direction = Direction.Left;
                break;
            case 1:
                direction = Direction.Right;
                break;
            default:
                direction = Direction.Straight;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void checkTourne() {
        if (hasTurned) {
            return;
        }
        Vec2 pos = getPosition();
        double x = pos.x;
        double y = pos.y;

        switch (direction) {
            case Right: {
                switch (route) {
                    case North:
                        if (y == 500) {
                            route = Route.West;
                            hasTurned = true;
                        }
                        break;
                    case South:
                        if (y == 450) {
                            route = Route.East;
                            hasTurned = true;
                        }
                        break;
                    case East:
                        if (x == 500) {
                            route = Route.North;
                            hasTurned = true;
                        }
                        break;
                    default:
                        if (x == 450) {
                            route = Route.South;
                            hasTurned = true;
                        }
                        break;
                }
                break;
            }
            case Left: {
                switch (route) {
                    case North:
                        if (y == 450) {
                            route = Route.East;
                            hasTurned = true;
                        }
                        break;
                    case South:
                        if (y == 500) {
                            route = Route.West;
                            hasTurned = true;
                        }
                        break;
                    case East:
                        if (x == 450) {
                            route = Route.South;
                            hasTurned = true;
                        }
                        break;
                    default:
                        if (x == 500) {
                            route = Route.North;
                            hasTurned = true;
                        }
                        break;
                }
                break;
            }

        }

    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
