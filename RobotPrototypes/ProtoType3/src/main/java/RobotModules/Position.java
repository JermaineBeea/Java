package RobotModules;
public class Position {
    
    private int xPos;
    private int yPos;
    private Direction direction;
    private int xUnitChange;
    private int yUnitChange;

    public Position(int xArg, int yArg, Direction directionArg){
        this.xPos =xArg;
        this.yPos = yArg;
        this.direction = directionArg;
        this.xUnitChange = direction.getXunitChange();
        this.yUnitChange = direction.getYunitChange();
    }

    // Assignment  Methods.

    public void setX(int xArg){
        this.xPos = xArg;
    }

    public void setY(int yArg){
        this.yPos = yArg;
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

    // Helper functions.
    public void move(int distance, int xUnitChange, int yUnitChange){
        this.xPos += distance * xUnitChange;
        this.yPos += distance * yUnitChange;
    }

    // Delta Methods.

    public void rotateRight(int quantity){
        int newIndex = (direction.getIndex() + quantity) % 4;
        direction = Direction.getDirectionName(newIndex);
    }

    public void rotateleft(int quantity){
        int newIndex = (4 + direction.getIndex() + quantity) % 4;
        direction = Direction.getDirectionName(newIndex);
    }

    public void moveForward(int distance){
       move(distance, xUnitChange, yUnitChange);
    }

    public void moveBackward(int distance){
        move(distance, -xUnitChange, -yUnitChange);
    }

    public void moveRight(int distance){
        move(distance, -yUnitChange, xUnitChange);
    }

    public void moveLeft(int distance){
        move(distance, yUnitChange, -xUnitChange);
    }
}
