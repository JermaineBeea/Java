package Game2.ClientPackage.ClientSideModules;

import java.util.*;
import java.util.function.Consumer;

import Game2.ClientPackage.RobotModules.*;

/**
 * Handles command execution and robot state management on the client side.
 * Maps string commands to their respective robot movement functions.
 */
public class ClientCommands {
    
    private ClientRobot clientRobot;
    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    private List<Direction> listDirections = Arrays.asList(
        Direction.NORTH,
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST
    );

    /**
     * Initializes ClientCommands with a robot instance and maps commands to functions.
     * @param instance The client robot to control
     */
    public ClientCommands(ClientRobot instance) {
        this.clientRobot = instance;
        mapCommands.put("rotateright", n -> rotateClockWise(n.intValue()));
        mapCommands.put("rotateleft", n -> rotateAntiClockWise(n.intValue()));
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);  
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }

    /**
     * Executes a string command with the specified quantity.
     * @param strCommand The command to execute
     * @param commandQuantity The quantity/magnitude for the command
     * @throws IllegalArgumentException if the command is unknown
     */
    public void executeCommand(String strCommand, double commandQuantity) {
        Consumer<Double> command = mapCommands.get(strCommand.toLowerCase());
        if (command != null) {
            command.accept(commandQuantity);
        } else {
            String availableCommands = String.join(", ", mapCommands.keySet());
            throw new IllegalArgumentException("Unknown command: " + strCommand + "\nAvailable commands: " + availableCommands);
        }
    }

    /**
     * @return The robot's current X position
     */
    public double getXpos() {
        return clientRobot.xPos;
    }

    /**
     * @return The robot's current Y position
     */
    public double getYpos() {
        return clientRobot.yPos;
    }

    /**
     * @return The robot's current direction
     */
    public Direction getDirection() {
        return clientRobot.direction;
    }

    /**
     * Displays the robot's current position and direction
     */
    public void viewPosition() {
        System.out.println("Robot is at (" + clientRobot.xPos + ", " + clientRobot.yPos + ") facing " + clientRobot.direction);
    }

    /**
     * Updates the robot's coordinates based on the distance and direction vectors.
     * @param distance The distance to move
     * @param xTranslation X component of movement vector
     * @param yTranslation Y component of movement vector
     */
    private void changeCoordinates(double distance, int xTranslation, int yTranslation) {
        clientRobot.xPos += distance * xTranslation;
        clientRobot.yPos += distance * yTranslation;    
    }

    /**
     * Rotates the robot to a new direction.
     * @param rotationQuantity The amount to rotate (must be non-negative)
     * @param newIndex The new direction index in the listDirections list
     * @throws IllegalArgumentException if rotation quantity is negative
     */
    public void rotate(int rotationQuantity, int newIndex) {
        if (rotationQuantity >= 0) {
            clientRobot.direction = listDirections.get(newIndex);
        } else {
            throw new IllegalArgumentException("Rotation quantity cannot be less than zero");
        }
    }

    /**
     * Sets the robot's position to specific coordinates.
     * @param xPos The new X position
     * @param yPos The new Y position
     */
    public void setPosition(double xPos, double yPos) {
        clientRobot.xPos = xPos;
        clientRobot.yPos = yPos;
    }

    /**
     * Sets the robot's direction.
     * @param direction The new direction
     */
    public void setDirection(Direction direction) {
        clientRobot.direction = direction;
    }

    /**
     * Rotates the robot clockwise by the specified amount.
     * @param rotationQuantity Number of 90-degree clockwise rotations
     */
    public void rotateClockWise(int rotationQuantity) {
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        int newIndex = (currentIndex + rotationQuantity) % 4;
        rotate(rotationQuantity, newIndex);
    }

    /**
     * Rotates the robot counter-clockwise by the specified amount.
     * @param rotationQuantity Number of 90-degree counter-clockwise rotations
     */
    public void rotateAntiClockWise(int rotationQuantity) {
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        // Fix: Using negative rotation for counter-clockwise movement
        int newIndex = (4 + currentIndex - rotationQuantity) % 4;
        rotate(rotationQuantity, newIndex);
    }

    /**
     * Moves the robot forward in its current direction.
     * @param distance The distance to move
     */
    public void moveForward(double distance) {
       changeCoordinates(distance, clientRobot.direction.xUnitChange, clientRobot.direction.yUnitChange);
    }

    /**
     * Moves the robot backward (opposite to its current direction).
     * @param distance The distance to move
     */
    public void moveBackward(double distance) {
        changeCoordinates(distance, -clientRobot.direction.xUnitChange, -clientRobot.direction.yUnitChange);
    }
    
    /**
     * Moves the robot to its left (90 degrees counter-clockwise from facing direction).
     * @param distance The distance to move
     */
    public void moveLeft(double distance) {
        changeCoordinates(distance, -clientRobot.direction.yUnitChange, clientRobot.direction.xUnitChange);
    }

    /**
     * Moves the robot to its right (90 degrees clockwise from facing direction).
     * @param distance The distance to move
     */
    public void moveRight(double distance) {
        changeCoordinates(distance, clientRobot.direction.yUnitChange, -clientRobot.direction.xUnitChange);
    }
}