package ClientPackage;

public class Position {
    
    private double xPos;
    private double yPos;
    private Direction direction;

    // Reassignment methods.
    public void setX(double xArg){
        this.xPos = xArg;
    }

    public void setY(double yArg){
        this.yPos = yArg;
    }

    public void setDirection(Direction directionArg){
        this.direction = directionArg;
    }

    // Retrieval methods.
    public double getX(){
        return xPos;
    }

    public double getY(){
        return yPos;
    }

    public Direction getDirection(){
        return direction;
    }
    
}
