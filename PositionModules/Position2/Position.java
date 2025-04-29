package PositionModules.Position2;

public class Position {
    
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
     * Forward orientation is sets to East which is 0.
     * @param distance
     */
    public void forward(int distance){
        double radians = 2 * Math.PI * (0 + direction.getRotation());
        this.xPos += Math.cos(radians) * distance;
        this.yPos += Math.sin(radians) * distance;
    }
    /**
     * Right Rotation is sets to North which is 0.25
     * @param distance
     */
    public void left(int distance){
        double radians = 2 * Math.PI * (0.25 + direction.getRotation());
        this.xPos += Math.cos(radians) * distance;
        this.yPos += Math.sin(radians) * distance;
    }


    /**
     * Backward Rotation is sets to West which is 0.5
     * @param distance
     */
    public void backward(int distance){
        double radians = 2 * Math.PI * (0.5 + direction.getRotation());
        this.xPos += Math.cos(radians) * distance;
        this.yPos += Math.sin(radians) * distance;
    }
    /**
     * Right Rotation is sets to South which is 0.75
     * @param distance
     */
    public void right(int distance){
        double radians = 2 * Math.PI * (0.75 + direction.getRotation());
        this.xPos += Math.cos(radians) * distance;
        this.yPos += Math.sin(radians) * distance;
    }
}
