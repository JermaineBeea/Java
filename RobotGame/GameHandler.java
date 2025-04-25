package RobotGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Main game class that handles robot movement and game logic.
 * This class translates string commands into robot movements.
 */
public class GameHandler 
{
    private final Robot robot;  // The robot controlled by the game
    
    // Map to convert string direction commands to Direction enum values
    private static final Map<String, Direction> DIRECTION_MAP = new HashMap<>();

    // Static initializer to populate the direction mapping
    static 
    {
        DIRECTION_MAP.put("left", Direction.LEFT);
        DIRECTION_MAP.put("right", Direction.RIGHT);
        DIRECTION_MAP.put("up", Direction.UP);
        DIRECTION_MAP.put("down", Direction.DOWN);
    }

    /**
     * Creates a new game with robot at the origin (0,0).
     */
    public GameHandler() 
    {
        this.robot = new Robot(0, 0);
    }

    /**
     * Processes a move command in the game.
     * Validates the input, moves the robot accordingly, and returns the new position.
     * 
     * @param strDirection The direction to move (left, right, up, down - case insensitive)
     * @param strQuantity The number of steps to move (must be a positive integer)
     * @return A string indicating the robot's new position
     * @throws IllegalArgumentException if the direction is invalid or quantity is not a positive integer
     */
    public String playGame(String strDirection, String strQuantity) 
    {
        // Convert string direction to Direction enum
        Direction direction = DIRECTION_MAP.get(strDirection.toLowerCase());
        if (direction == null) 
        {
            throw new IllegalArgumentException("Invalid direction. Valid options are: left, right, up, down");
        }

        // Parse and validate the quantity (steps)
        int quantity;
        try 
        {
            quantity = Integer.parseInt(strQuantity);
            if (quantity < 0) 
            {
                throw new IllegalArgumentException("Quantity must be a positive number");
            }
        } 
        catch (NumberFormatException e) 
        {
            throw new IllegalArgumentException("Quantity must be a valid integer");
        }

        // Move the robot and return its new position
        robot.move(direction, quantity);
        return "Robot is at: " + robot.toString();
    }
}