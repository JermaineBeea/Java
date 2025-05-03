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
    

    public void moveForward(double distance) {
        this.xPos += direction.xVector;
        this.yPos += direction.yVector;
    }

    public void moveBackward(double distance){
        this.xPos -= direction.xVector;
        this.yPos -= direction.yVector;
    }
    
    public void moveLeft(double distance){
        this.xPos -= direction.yVector;
        this.yPos += direction.xVector;
    }

    public void moveRight(double distance){
        this.xPos += direction.yVector;
        this.yPos -= direction.xVector;
    }

}
