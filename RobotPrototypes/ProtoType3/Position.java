public class Position {
    
    private int xPos;
    private int yPos;
    private Direction direction;

    public Position(int xArg, int yArg, Direction directionArg){
        this.xPos =xArg;
        this.yPos = yArg;
        this.direction = directionArg;
    }

    // Assignment  Methods.
    public void setX(int xArg){
        this.xPos = xArg;
    }

    public void setT(int yArg){
        this.xPos = yArg;
    }

    public void setDirection(Direction directionArg){
        this.direction = directionArg;
    }

    // Access Methods.
    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public Direction getDirection(){
        return direction;
    }


}
