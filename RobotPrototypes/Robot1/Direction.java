package RobotPrototypes.Robot1;

/**
 * Represents possible movement directions for the robot in a 2D coordinate system.
 * Each direction defines unit changes in the x and y coordinates:
 * - LEFT: Move 1 unit left (-1, 0)
 * - RIGHT: Move 1 unit right (1, 0)
 * - UP: Move 1 unit up (0, 1)
 * - DOWN: Move 1 unit down (0, -1)
 */
public enum Direction 
{
    LEFT(-1, 0),   // Move left (negative x direction)
    RIGHT(1, 0),   // Move right (positive x direction)
    UP(0, 1),      // Move up (positive y direction)
    DOWN(0, -1);   // Move down (negative y direction)

    private final int xUnitChange;  // The change in x-coordinate for one step in this direction
    private final int yUnitChange;  // The change in y-coordinate for one step in this direction

    /**
     * Constructor for Direction enum.
     * 
     * @param xUnitChange The change in x-coordinate for one step in this direction
     * @param yUnitChange The change in y-coordinate for one step in this direction
     */
    Direction(int xUnitChange, int yUnitChange) 
    {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }

    /**
     * Gets the change in x-coordinate for one step in this direction.
     * @return The x-coordinate unit change
     */
    public int getXUnitChange() 
    {
        return xUnitChange;
    }

    /**
     * Gets the change in y-coordinate for one step in this direction.
     * @return The y-coordinate unit change
     */
    public int getYUnitChange() 
    {
        return yUnitChange;
    }

    /**
     * Simple test method for the Direction enum.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Direction direction = Direction.UP;
        System.out.println(direction.getXUnitChange());
    }
}