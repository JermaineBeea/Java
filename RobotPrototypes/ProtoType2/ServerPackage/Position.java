package ServerPackage;
public class Position {
    
    private double xPos;
    private double yPos;
    private Direction direction;

    public Position(){
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Direction.NORTH;
    }

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

    // Detlta methods.
    public void move(double distance, int xUnitChange, int yUnitChange){
        this.xPos += xUnitChange * distance;
        this.yPos += yUnitChange * distance;
    }

    public void rotate(int indexDirection){
        this.direction = Direction.getDirectionName(indexDirection);
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
