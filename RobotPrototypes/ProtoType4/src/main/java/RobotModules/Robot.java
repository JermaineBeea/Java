package RobotModules;

import java.util.function.Consumer;
import java.util.logging.Logger;

import Utility.LogConfiguration;

public class Robot {
    private LogConfiguration logConfig = new LogConfiguration(Robot.class.getName());
    private Logger logger = logConfig.getLogger();


    // All robots have a default fuel amount of 1000.
    private final int DEFAULT_FUEL = 1000;

    // Immutable once set.
    private String name;
    private String buildType;
    private int rateFuelUsage;

    // Mutable properties.
    private int durability;
    private int maxShots;
    private int fuelAmount;
    private Position position;

    public Robot(String buildArg, int rateArg, int durabilityArg, int maxShotsArg) {
        this.name = "";
        this.buildType = buildArg;
        this.rateFuelUsage = rateArg;
        this.durability = durabilityArg;
        this.maxShots = maxShotsArg;
        this.fuelAmount = DEFAULT_FUEL; 
        this.position = new Position(0, 0, Direction.NORTH);
    }
    
    // Movement methods that delegate to Position

    public void rotateRight(int quantity) {
        position.rotateRight(quantity);
    }

    public void rotateLeft(int quantity) {
        position.rotateleft(quantity); 
    }

    // Helper method used in methods to move robot position.

    public void moveRobot(int distance, Consumer<Integer> function){
        if (fuelAmount >= rateFuelUsage * distance) {
            function.accept(distance);
            fuelAmount -= rateFuelUsage * distance;
        }    
    }
    

    // Methods to change position of robot.

    public void moveForward(int distance) {
        moveRobot(distance, position::moveForward);
    }

    public void moveBackward(int distance) {
        moveRobot(distance, position::moveBackward);
    }

    public void moveRight(int distance){
        moveRobot(distance, position::moveRight);
    }

    public void moveLeft(int distance){
        moveRobot(distance, position::moveLeft);
    }

    // Fuel-related helper methods

    public void repair() {
        if (fuelAmount >= 10) {
            fuelAmount -= 10;
            logger.info(name + " repaired to full durability.");
        } else {
            logger.warning(name + " does not have enough fuel to repair.");
        }
    }

    public void refuel() {
        try {
            logger.info(name + " started refueling...");
            Thread.sleep(2000);
            logger.info(name + " refueling complete. Fuel level: " + fuelAmount);
        } catch (InterruptedException e) {
            logger.warning(name + " was interrupted while refueling.");
            Thread.currentThread().interrupt();
        }
    }

    public void shoot() {
        if (maxShots > 0) {
            maxShots--;
            logger.info(name + " fired a shot. Remaining shots: " + maxShots);
        } else {
            logger.warning(name + " has no shots left to fire!");
        }
    }

    // Assignment Methods

    public void setName(String nameArg) {
        this.name = nameArg;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setFuelAmount(int amount) {
        this.fuelAmount = amount;
    }

    // Access Methods

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getBuildType() {
        return buildType;
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxShots() {
        return maxShots;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public int getRateUsage() {
        return rateFuelUsage;
    }
}