import java.util.Random;

public class Vehicle {
    public final static Integer vehicleSize = 50;
    private final static float speed = 1;
    private Vec2 position;
    private boolean isMoving = true;
    private Direction direction;
    private boolean hasTurned = false;

    public Vehicle(Vec2 position) {
        this.position = position;
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

   
    public void setPosition(Vec2 position) {
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

    public boolean hasTurned(){
        return this.hasTurned;
    }

    public void setTurned(boolean bool){
        this.hasTurned = bool;
    }
}
