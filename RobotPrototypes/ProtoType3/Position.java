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

    public void setY(int yArg){
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

    // Delta Methods.

    public void rotateRight(int quantity){
        int newIndex = (direction.getIndex() + quantity) % 4;
        this.direction = Direction.getDirectionName(newIndex);
    }

    public void rotateleft(int quantity){
        int newIndex = (4 + direction.getIndex() + quantity) % 4;
        this.direction = Direction.getDirectionName(newIndex);
    }

    public void moveForward(int distance){
        this.xPos += distance * direction.getXunitChange();
        this.yPos += distance * direction.getYunitChange();
    }

    public void moveBackward(int distance){
        this.xPos -= distance * direction.getXunitChange();
        this.yPos -= distance * direction.getYunitChange();
    }


}
