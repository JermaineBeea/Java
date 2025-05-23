package PositionModules;

enum Direction2{
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    public int xUnitChange;
    public int yUnitChange;

    Direction2(int xUnitChange, int yUnitChange){
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }
}

public class Position2 {
    
    private double xPos;
    private double yPos;
    private Direction2 direction = Direction2.NORTH;

    public Position2(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Position2(){
        this(0, 0);
    }

    @Override
    public String toString(){
        return "Robot is at (" + xPos + "," + yPos + ")";
    }

    public void setDirection(Direction2 direction){
        this.direction = direction;
    }
    
    public void changeCoordinates(double distance, int xTranslation, int yTranslation){
        this.xPos += distance * xTranslation;
        this.yPos += distance * yTranslation;
    }

    public void moveForward(double distance) {
       changeCoordinates(distance, direction.xUnitChange, direction.yUnitChange);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, -direction.xUnitChange, -direction.yUnitChange);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, -direction.yUnitChange, direction.xUnitChange);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, direction.yUnitChange, -direction.xUnitChange);
    }

}
