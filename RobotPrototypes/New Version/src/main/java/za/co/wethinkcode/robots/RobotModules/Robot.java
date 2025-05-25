package za.co.wethinkcode.robots.RobotModules;

import java.util.function.Consumer;
import java.util.logging.Logger;

import za.co.wethinkcode.robots.Utility.LogConfiguration;

/**
 * Represents a robot entity in the system.
 * Contains properties and methods for robot movement, actions, and state management.
 */
public class Robot 
{
    /** Logger configuration for this class */
    private LogConfiguration logConfig = new LogConfiguration(Robot.class.getName());
    
    /** Logger instance for this class */
    private Logger logger = logConfig.getLogger();

    /** Default fuel amount for all robots */
    private final int DEFAULT_FUEL = 1000;

    // Immutable properties (once set)
    /** Robot name identifier */
    private String name;
    
    /** Type of robot build */
    private String buildType;
    
    /** Rate at which fuel is consumed per movement unit */
    private int rateFuelUsage;

    // Mutable properties
    /** Current durability/health of the robot */
    private int durability;
    
    /** Maximum number of shots the robot can fire */
    private int maxShots;
    
    /** Current fuel amount */
    private int fuelAmount;
    
    /** Current position and direction of the robot */
    private Position position;

    /**
     * Creates a new robot with specified build parameters.
     * 
     * @param buildArg Type of robot build
     * @param rateArg Rate of fuel consumption per unit of movement
     * @param durabilityArg Initial durability/health of the robot
     * @param maxShotsArg Maximum number of shots the robot can fire
     */
    public Robot(String buildArg, int rateArg, int durabilityArg, int maxShotsArg) 
    {
        this.name = "";
        this.buildType = buildArg;
        this.rateFuelUsage = rateArg;
        this.durability = durabilityArg;
        this.maxShots = maxShotsArg;
        this.fuelAmount = DEFAULT_FUEL; 
        this.position = new Position(0, 0, Direction.NORTH);
    }
    
    /**
     * Rotates the robot right (clockwise) by the specified amount.
     * 
     * @param quantity Number of 90-degree turns to rotate
     */
    public void rotateRight(int quantity) 
    {
        position.rotateRight(quantity);
    }

    /**
     * Rotates the robot left (counter-clockwise) by the specified amount.
     * 
     * @param quantity Number of 90-degree turns to rotate
     */
    public void rotateLeft(int quantity) 
    {
        position.rotateleft(quantity); 
    }

    /**
     * Helper method to move the robot if sufficient fuel is available.
     * Decreases fuel based on distance and consumption rate.
     * 
     * @param distance Distance to move
     * @param function Movement function to execute
     */
    public void moveRobot(int distance, Consumer<Integer> function)
    {
        // Check if there's enough fuel for the movement
        if (fuelAmount >= rateFuelUsage * distance) 
        {
            // Execute the movement function
            function.accept(distance);
            
            // Decrease fuel based on distance and consumption rate
            fuelAmount -= rateFuelUsage * distance;
        }    
    }
    
    /**
     * Moves the robot forward by the specified distance.
     * 
     * @param distance Distance to move forward
     */
    public void moveForward(int distance) 
    {
        moveRobot(distance, position::moveForward);
    }

    /**
     * Moves the robot backward by the specified distance.
     * 
     * @param distance Distance to move backward
     */
    public void moveBackward(int distance) 
    {
        moveRobot(distance, position::moveBackward);
    }

    /**
     * Moves the robot right (strafe) by the specified distance.
     * 
     * @param distance Distance to move right
     */
    public void moveRight(int distance)
    {
        moveRobot(distance, position::moveRight);
    }

    /**
     * Moves the robot left (strafe) by the specified distance.
     * 
     * @param distance Distance to move left
     */
    public void moveLeft(int distance)
    {
        moveRobot(distance, position::moveLeft);
    }

    /**
     * Repairs the robot, restoring its durability.
     * Costs 10 fuel units to perform.
     */
    public void repair() 
    {
        if (fuelAmount >= 10) 
        {
            fuelAmount -= 10;
            logger.info(name + " repaired to full durability.");
        } 
        else 
        {
            logger.warning(name + " does not have enough fuel to repair.");
        }
    }

    /**
     * Refuels the robot over a 2-second period.
     * Simulates the time taken to refuel.
     */
    public void refuel() 
    {
        try 
        {
            logger.info(name + " started refueling...");
            Thread.sleep(2000); // Simulate refueling time
            logger.info(name + " refueling complete. Fuel level: " + fuelAmount);
        } 
        catch (InterruptedException e) 
        {
            logger.warning(name + " was interrupted while refueling.");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Fires a shot if the robot has shots remaining.
     * Decreases available shots by 1.
     */
    public void shoot() 
    {
        if (maxShots > 0) 
        {
            maxShots--;
            logger.info(name + " fired a shot. Remaining shots: " + maxShots);
        } 
        else 
        {
            logger.warning(name + " has no shots left to fire!");
        }
    }

    /**
     * Sets the robot's name.
     * 
     * @param nameArg The name to set
     */
    public void setName(String nameArg) 
    {
        this.name = nameArg;
    }

    /**
     * Sets the robot's durability.
     * 
     * @param durability The durability value to set
     */
    public void setDurability(int durability) 
    {
        this.durability = durability;
    }

    /**
     * Sets the robot's fuel amount.
     * 
     * @param amount The fuel amount to set
     */
    public void setFuelAmount(int amount) 
    {
        this.fuelAmount = amount;
    }

    /**
     * Gets the robot's current position.
     * 
     * @return The current position
     */
    public Position getPosition() 
    {
        return position;
    }

    /**
     * Gets the robot's name.
     * 
     * @return The robot's name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the robot's build type.
     * 
     * @return The build type
     */
    public String getBuildType() 
    {
        return buildType;
    }

    /**
     * Gets the robot's current durability.
     * 
     * @return The current durability
     */
    public int getDurability() 
    {
        return durability;
    }

    /**
     * Gets the number of shots remaining.
     * 
     * @return The remaining shots
     */
    public int getMaxShots() 
    {
        return maxShots;
    }

    /**
     * Gets the current fuel amount.
     * 
     * @return The current fuel amount
     */
    public int getFuelAmount() 
    {
        return fuelAmount;
    }

    /**
     * Gets the fuel usage rate.
     * 
     * @return The fuel usage rate per unit of movement
     */
    public int getRateUsage() 
    {
        return rateFuelUsage;
    }
}