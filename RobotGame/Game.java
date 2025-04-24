package RobotGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Main game class that handles robot movement.
 */
public class Game 
{
    private final Robot robot;
    private static final Map<String, Direction> DIRECTION_MAP = new HashMap<>();

    static 
    {
        DIRECTION_MAP.put("left", Direction.LEFT);
        DIRECTION_MAP.put("right", Direction.RIGHT);
        DIRECTION_MAP.put("up", Direction.UP);
        DIRECTION_MAP.put("down", Direction.DOWN);
    }

    /**
     * Creates a new game with robot at origin.
     */
    public Game() 
    {
        this.robot = new Robot(0, 0);
    }

    /**
     * Processes a move in the game.
     * 
     * @param strDirection The direction to move
     * @param strQuantity The number of steps to move
     * @throws IllegalArgumentException if the direction is invalid or quantity is not a positive integer
     */
    public void playGame(String strDirection, String strQuantity) 
    {
        Direction direction = DIRECTION_MAP.get(strDirection.toLowerCase());
        if (direction == null) 
        {
            throw new IllegalArgumentException("Invalid direction. Valid options are: left, right, up, down");
        }

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

        robot.move(direction, quantity);
    }

    /**
     * @return Current status of the robot
     */
    public String getStatus() 
    {
        return "Robot is at: " + robot.toString();
    }
}