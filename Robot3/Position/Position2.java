package Position;

enum Direction2{
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    public int xVector;
    public int yVector;

    Direction2(int xVector, int yVector){
        this.xVector = xVector;
        this.yVector = yVector;
    }
}

public class Position1 {
    
    private double xPos;
    private double yPos;
    private Direction1 direction = Direction1.NORTH;

    public Position1(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Position1(){
        this(0, 0);
    }

    @Override
    public String toString(){
        return "Robot is at (" + xPos + "," + yPos + ")";
    }

    public void setDirection(Direction1 direction){
        this.direction = direction;
    }
    
    private int xUnitChange(double radians){
        return (int) Math.cos(radians);
    }

    private int yUnitChange(double radians){
        return (int) Math.sin(radians);
    }

    public void changeCoordinates(double distance, double movementArc){
        double radians = (direction.arc + movementArc) * 2 * Math.PI;
        this.xPos += distance * xUnitChange(radians);
        this.yPos += distance * yUnitChange(radians);
    }

    public void moveForward(double distance) {
        changeCoordinates(distance, 0);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, 0.5);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, 0.25);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, 0.75);
    }

}
