package RobotModules;

import java.util.function.Consumer;

public class Robot {

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
        //TODO implement repair logic
    }

    public void refuel() {
        // TODO Implement refuel logic
    }

    public void shoot() {
        // TODO Implement shooting logic
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