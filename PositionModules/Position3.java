package PositionModules;

enum Direction3 {
    NORTH(1), EAST(0), SOUTH(3), WEST(2);

    public int rotation;
    Direction3(int rotation){
        this.rotation = rotation;
    }
}

public class Position3 {
    
    private double xPos;
    private double yPos;
    private Direction3 direction = Direction3.NORTH;

    public Position3(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Position3(){
        this(0, 0);
    }

    @Override
    public String toString(){
        return "Robot is at (" + xPos + "," + yPos + ")";
    }

    public void setDirection(Direction3 direction){
        this.direction = direction;
    }
    
    private int xUnitChange(int rotation){
        return (int) (Math.pow(-1, rotation/2) * (Math.pow(-1, rotation) + 1)/2);
    }

    private int yUnitChange(int rotation){
        return (int) (Math.pow(-1, (rotation-1)/2) * (Math.pow(-1, rotation+1) + 1)/2);
    }

    public void changeCoordinates(double distance, int movementRotation){
        int rotation = (direction.rotation + movementRotation) % 4;
        this.xPos += distance * xUnitChange(rotation);
        this.yPos += distance * yUnitChange(rotation);
    }

    public void moveForward(double distance) {
        changeCoordinates(distance, 0);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, 2);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, 1);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, -1);
    }

}
