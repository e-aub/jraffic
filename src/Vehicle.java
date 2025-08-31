import java.util.Random;

public class Vehicle {
    public final static Integer vehicleSize = 50;
    private final static float speed =1 ;
    private Vec2 position;
    private boolean isMoving = true;
    private Direction direction;
    private Route route;
    private boolean hasTurned = false;
    private boolean doz = false;

    public boolean doz() {
        return doz;
    }

    public void setDoz(boolean doz) {
        this.doz = doz;
    }

    public Vehicle(Vec2 position, Route route) {
        this.position = position;
        Random rand = new Random();
        int index = rand.nextInt(3);
        this.route = route;

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

    public Route getRoute() {
        return this.route;
    }

    public Direction getDirection() {
        return direction;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        // Square A (50px)
        float sx = 1000 / 2;
        float sy = 1000 / 2;
        float aLeft = position.x;
        float aRight = position.x + 50;
        float aTop = position.y;
        float aBottom = position.y + 50;

        float bLeft = sx - 49;
        float bRight = sx + 49;
        float bTop = sy - 49;
        float bBottom = sy + 49;

        boolean intersects = aLeft < bRight &&
                aRight > bLeft &&
                aTop < bBottom &&
                aBottom > bTop;

        if (intersects) {
            setDoz(true);
        }
        this.position = position;

    }

    public float getSpeed() {
        return speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean hasTurned() {
        return this.hasTurned;
    }

    public void setTurned(boolean bool) {
        this.hasTurned = bool;
    }
}
