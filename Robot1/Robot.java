package Robot1;

/**
 * This class defines a Robot and provides methods for movement in a 2D coordinate system.
 * The robot maintains its position as (x,y) coordinates and can move in four directions.
 */
public class Robot 
{
    private int xPos; // X-coordinate position of the robot
    private int yPos; // Y-coordinate position of the robot

    /**
     * Creates a robot at specified coordinates.
     * @param xPos The initial x position (horizontal axis).
     * @param yPos The initial y position (vertical axis).
     */
    public Robot(int xPos, int yPos) 
    {
        this.xPos = xPos;
        this.yPos = yPos;  
    }

    /**
     * Creates a robot at the origin (0,0).
     * This is a convenience constructor that delegates to the main constructor.
     */
    public Robot() 
    {
        this(0, 0);
    }

    /**
     * Gets the current X coordinate of the robot.
     * @return The x-coordinate position.
     */
    public int getXPos() 
    {
        return xPos;
    }

    /**
     * Gets the current Y coordinate of the robot.
     * @return The y-coordinate position.
     */
    public int getYPos() 
    {
        return yPos;
    }

    /**
     * Gets both the X and Y coordinates as an array.
     * @return An integer array where index 0 is the x-coordinate and index 1 is the y-coordinate.
     */
    public int[] getPos() 
    {
        return new int[]{xPos, yPos};
    }

    /**
     * Moves the robot in the specified direction by the given number of steps.
     * The movement is calculated based on the unit change vectors defined in the Direction enum.
     *
     * @param direction The direction of movement (LEFT, RIGHT, UP, DOWN).
     * @param quantity Number of steps to move in the specified direction.
     * @throws IllegalArgumentException If direction is null or quantity is negative.
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

    /**
     * Provides a string representation of the robot's position.
     * @return A string in the format "(x, y)" representing the robot's coordinates.
     */
    @Override
    public String toString() 
    {
        return "(" + xPos + ", " + yPos + ")";
    }
}