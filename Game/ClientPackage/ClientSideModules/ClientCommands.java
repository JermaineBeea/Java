package Game.ClientPackage.ClientSideModules;

import java.util.*;
import java.util.function.Consumer;

import Game.ClientPackage.RobotModules.ClientRobot;
import Game.ClientPackage.RobotModules.Direction;

/**
 * Handles robot movement and command execution on the client side.
 * This class manages the commands that can be executed by the client
 * and translates them to robot movement.
 */
public class ClientCommands {
    
    private final ClientRobot clientRobot;
    private final Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    private final List<Direction> listDirections = Arrays.asList(
        Direction.NORTH,
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST
    );

    /**
     * Constructs a new ClientCommands object associated with a ClientRobot instance.
     * Initializes the command map with all available robot commands.
     *
     * @param instance The ClientRobot instance to associate with this command set
     */
    public ClientCommands(ClientRobot instance){
        this.clientRobot = instance;
        mapCommands.put("rotateright", n -> rotateClockWise(n.intValue()));
        mapCommands.put("rotateleft", n -> rotateAntiClockWise(n.intValue()));
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }

    /**
     * Executes a command with the given parameters.
     *
     * @param strCommand The command to execute (case-insensitive)
     * @param commandQuantity The quantity/magnitude for the command
     */
    public void executeCommand(String strCommand, double commandQuantity){
        if (commandQuantity < 0) {
            System.err.println("Command quantity cannot be negative: " + commandQuantity);
            return;
        }
        
        Consumer<Double> command = mapCommands.get(strCommand.toLowerCase());
        if (command != null) {
            command.accept(commandQuantity);
        } else {
            System.err.println("Unknown command: " + strCommand);
            System.err.println("Available commands: forward, backward, right, left, rotateright, rotateleft");
        }
    }

    /**
     * @return Current X position of the robot
     */
    public double getXpos(){
        return clientRobot.xPos;
    }

    /**
     * @return Current Y position of the robot
     */
    public double getYpos(){
        return clientRobot.yPos;
    }

    /**
     * @return Current direction the robot is facing
     */
    public Direction getDirection(){
        return clientRobot.direction;
    }

    /**
     * @return Current fuel level of the robot
     */
    public double getFuel(){
        return clientRobot.fuelAmount;
    }

    /**
     * Display the current position and orientation of the robot
     */
    public void viewPosition(){
        System.out.println("Robot is at (" + clientRobot.xPos + ", " + clientRobot.yPos + ") facing " + clientRobot.direction);
    }

    /**
     * Changes the robot's coordinates according to the given parameters.
     * Checks if sufficient fuel is available before moving.
     *
     * @param distance Distance to move
     * @param xTranslation Change in x direction (-1, 0, or 1)
     * @param yTranslation Change in y direction (-1, 0, or 1)
     */
    private void changeCoordinates(double distance, int xTranslation, int yTranslation){
        if (distance < 0) {
            System.err.println("Distance cannot be negative: " + distance);
            return;
        }
        
        double fuelNeeded = clientRobot.rateFuelUsage * distance;
        double currentFuel = clientRobot.fuelAmount;
        
        if(currentFuel >= fuelNeeded){
            clientRobot.xPos += distance * xTranslation;
            clientRobot.yPos += distance * yTranslation;
            clientRobot.fuelAmount -= fuelNeeded;
        } else {
            System.out.println("Not enough fuel to go distance " + distance + 
                              ". Required: " + fuelNeeded + ", Available: " + currentFuel);
        }
    }

    /**
     * Updates the robot's direction based on rotation.
     *
     * @param rotationQuantity Number of 90-degree rotations
     * @param newIndex The index of the new direction in listDirections
     */
    public void rotate(int rotationQuantity, int newIndex){
        if(rotationQuantity < 0){
            System.err.println("rotationQuantity cannot be less than zero");
            return;
        }
        
        clientRobot.direction = listDirections.get(newIndex);
    }

    /**
     * Sets the robot's position
     *
     * @param xPos New X coordinate
     * @param yPos New Y coordinate
     */
    public void setPosition(double xPos, double yPos){
        clientRobot.xPos = xPos;
        clientRobot.yPos = yPos;
    }

    /**
     * Sets the robot's direction
     *
     * @param direction New direction
     */
    public void setDirection(Direction direction){
        clientRobot.direction = direction;
    }

    /**
     * Sets the robot's fuel amount
     *
     * @param fuelAmount New fuel amount
     */
    public void setFuel(double fuelAmount){
        if (fuelAmount < 0) {
            System.err.println("Fuel amount cannot be negative");
            clientRobot.fuelAmount = 0;
        } else {
            clientRobot.fuelAmount = fuelAmount;
        }
    }

    /**
     * Sets the robot's fuel consumption rate
     *
     * @param fuelRate New fuel usage rate
     */
    public void setRateUsage(double fuelRate){
        if (fuelRate <= 0) {
            System.err.println("Fuel usage rate must be positive");
            return;
        }
        clientRobot.rateFuelUsage = fuelRate;
    }

    /**
     * Rotates the robot clockwise by the specified number of 90-degree turns
     *
     * @param rotationQuantity Number of 90-degree clockwise rotations
     */
    public void rotateClockWise(int rotationQuantity){
        if (rotationQuantity < 0) {
            System.err.println("rotationQuantity cannot be negative");
            return;
        }
        
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        int newIndex = (currentIndex + rotationQuantity) % 4;
        rotate(rotationQuantity, newIndex);
    }

    /**
     * Rotates the robot counter-clockwise by the specified number of 90-degree turns
     *
     * @param rotationQuantity Number of 90-degree counter-clockwise rotations
     */
    public void rotateAntiClockWise(int rotationQuantity){
        if (rotationQuantity < 0) {
            System.err.println("rotationQuantity cannot be negative");
            return;
        }
        
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        // Calculate the new index for counter-clockwise rotation
        int newIndex = (4 + (currentIndex - rotationQuantity % 4)) % 4;
        rotate(rotationQuantity, newIndex);
    }

    /**
     * Moves the robot forward in the direction it's facing
     *
     * @param distance Distance to move
     */
    public void moveForward(double distance) {
       changeCoordinates(distance, clientRobot.direction.xUnitChange, clientRobot.direction.yUnitChange);
    }

    /**
     * Moves the robot backward (opposite to the direction it's facing)
     *
     * @param distance Distance to move
     */
    public void moveBackward(double distance){
        changeCoordinates(distance, -clientRobot.direction.xUnitChange, -clientRobot.direction.yUnitChange);
    }
    
    /**
     * Moves the robot to its left (90 degrees counter-clockwise from facing direction)
     *
     * @param distance Distance to move
     */
    public void moveLeft(double distance){
        changeCoordinates(distance, -clientRobot.direction.yUnitChange, clientRobot.direction.xUnitChange);
    }

    /**
     * Moves the robot to its right (90 degrees clockwise from facing direction)
     *
     * @param distance Distance to move
     */
    public void moveRight(double distance){
        changeCoordinates(distance, clientRobot.direction.yUnitChange, -clientRobot.direction.xUnitChange);
    }
}