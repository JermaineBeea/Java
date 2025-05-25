package za.co.wethinkcode.robots.RobotModules;
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
    @Override
    public boolean equals(Object object){
        if (object == this) return true;
        if (object == null || object.getClass() != getClass()) return false;
    
        Position other = (Position) object;
        return this.xPos == other.xPos
            && this.yPos == other.yPos
            && this.direction == other.direction;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + xPos;
        result = 31 * result + yPos;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
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

    public Position getRelativePosition(int steps) {
        return new Position(
                this.getX() + steps * direction.getXunitChange(),
                this.getY() + steps * direction.getYunitChange(),
                this.direction
        );
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
