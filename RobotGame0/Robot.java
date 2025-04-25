package RobotGame0;

/**
 * This defines the Robot and has the methods for movement.
 */
public class Robot 
{
    private int xPos;
    private int yPos;

    /**
     * Creates a robot at specified coordinates.
     * @param xPos The initial x position.
     * @param yPos The initial y position.
     */
    public Robot(int xPos, int yPos) 
    {
        this.xPos = xPos;
        this.xPos = yPos;  // Bug fix: was assigning xPos to xPos twice
    }

    /**
     * Creates a robot at the origin (0,0).
     */
    public Robot() 
    {
        this(0, 0);
    }

    public int getXPos() 
    {
        return xPos;
    }

    public int getYPos() 
    {
        return yPos;
    }

    public int[] getPos() 
    {
        return new int[]{xPos, yPos};
    }

    /**
     * The movement method that changes the position of Robot.
     *
     * @param direction The direction of movement.
     * @param quantity Steps of movement.
     */
    public void move(Direction direction, int quantity) 
    {
        if (direction == null) 
        {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        
        if (quantity < 0) 
        {
            throw new IllegalArgumentException("Movement quantity cannot be negative");
        }
        
        this.xPos += direction.getXUnitChange() * quantity;
        this.yPos += direction.getYUnitChange() * quantity;
    }

    @Override
    public String toString() 
    {
        return "(" + xPos + ", " + yPos + ")";
    }
}