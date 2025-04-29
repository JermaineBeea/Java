package RobotModules;
public class Position{

    private int xPos;
    private int yPos;
    private Direction direction;

    Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection(){
        return direction;
    }

    public int getXPos(){
        return xPos;
    }
   
    public int getYPos(){
        return yPos;
    }

    /**
     * Forward unit changes are the same as Direction invoked.
     * @param distance Distance moved in direction.
     */
    public void forward(int distance){
        this.xPos += direction.getXUnitChange() * distance;
        this.yPos += direction.getYUnitChange() * distance;
    }
    /**
     * Backward unit changes are the opposite of Direction invoked.
     * @param distance Distance moved in direction.
     */
    public void backward(int distance){
        this.xPos -= direction.getXUnitChange() * distance;
        this.yPos -= direction.getYUnitChange() * distance;
    }
    /**
     * Right invets the axis, swapping x and y, then negating the y value.
     * @param distance Distance moved in direction.
     */
    public void left(int distance){
        this.xPos -= direction.getYUnitChange() * distance;
        this.yPos += direction.getXUnitChange() * distance;
    }
    /**
     * Left invets the axis, swapping x and y, then negating the y value.
     * @param distance Distance moved in direction.
     */
    public void right(int distance){
        this.xPos += direction.getYUnitChange() * distance;
        this.yPos -= direction.getXUnitChange() * distance;
    }
}

