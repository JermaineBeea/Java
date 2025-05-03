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
       changeCoordinates(distance, direction.xVector, direction.yVector);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, -direction.xVector, -direction.yVector);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, -direction.yVector, direction.xVector);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, direction.yVector, -direction.xVector);
    }

}
