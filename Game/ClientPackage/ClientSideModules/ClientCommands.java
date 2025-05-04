package Game.ClientPackage.ClientSideModules;

import java.util.*;
import java.util.function.Consumer;

import Game.ClientPackage.RobotModules.ClientRobot;
import Game.ClientPackage.RobotModules.Direction;

public class ClientCommands {
    
    private ClientRobot clientRobot;
    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    private List<Direction> listDirections = Arrays.asList(
        Direction.NORTH,
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST
    );

    public ClientCommands(ClientRobot instance){
        this.clientRobot = instance;
        mapCommands.put("rotateright", n -> rotateClockWise(n.intValue()));
        mapCommands.put("rotateleft", n -> rotateAntiClockWise(n.intValue()));
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);  // Fixed the typo from 'backwards' to 'backward'
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }

    // Executes String command.
    public void executeCommand(String strCommand, double commandrotationQuantity){
        Consumer<Double> command = mapCommands.get(strCommand.toLowerCase());
        if (command != null) {
            command.accept(commandrotationQuantity);
        } else {
            System.err.println("Unknown command: " + strCommand);
            System.err.println("Available commands: forward, backward, right, left");
        }
    }

    // Retrieval methods.
    public double getXpos(){
        return clientRobot.xPos;
    }

    public double getYpos(){
        return clientRobot.yPos;
    }

    public Direction getDirection(){
        return clientRobot.direction;
    }

    public double getFuel(){
        return clientRobot.fuelAmount;
    }

    public void viewPosition(){
        System.out.println("Robot is at (" + clientRobot.xPos + ", " + clientRobot.yPos + ") facing " + clientRobot.direction);
    }

    // Helper methods.
    private void changeCoordinates(double distance, int xTranslation, int yTranslation){
        double currentFuel = clientRobot.fuelAmount;
        if(currentFuel >= clientRobot.rateFuelUsage * distance){
            clientRobot.xPos += distance * xTranslation;
            clientRobot.yPos += distance * yTranslation;
            clientRobot.fuelAmount -= clientRobot.rateFuelUsage * distance;
        }else{
            System.out.println("Not enough fuel to go distance " + distance);
        }
    }

    public void rotate(int rotationQuantity, int newIndex){
        if(rotationQuantity instanceof Integer){
            if(rotationQuantity >= 0){
                clientRobot.direction = listDirections.get(newIndex);
            }else{
                System.err.println("rotationQuantity cannot be less than zero");
                return;
            }
        }else{
            System.err.println("rotationQuantity has to be an integer!");
            return;
        }
    }

    // Setter methods.
    public void setPosition(double xPos, double yPos){
        clientRobot.xPos = xPos;
        clientRobot.yPos = yPos;
    }

    public void setDirection(Direction direction){
        clientRobot.direction = direction;
    }

    public void setFuel(double fuelAmount){
        clientRobot.fuelAmount = fuelAmount;
    }

    public void setRateUsage(double fuelRate){
        clientRobot.rateFuelUsage = fuelRate;
    }

    // Rotate methods
    public void rotateClockWise(int rotationQuantity){
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        int newIndex = (currentIndex + rotationQuantity) % 4;
        rotate(rotationQuantity, newIndex);
    }

    public void rotateAntiClockWise(int rotationQuantity){
        int currentIndex = listDirections.indexOf(clientRobot.direction);
        int newIndex = (4 + currentIndex + rotationQuantity) % 4;
        rotate(rotationQuantity, newIndex);
    }

    // Modifier methods.
    public void moveForward(double distance) {
       changeCoordinates(distance, clientRobot.direction.xUnitChange, clientRobot.direction.yUnitChange);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, -clientRobot.direction.xUnitChange, -clientRobot.direction.yUnitChange);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, -clientRobot.direction.yUnitChange, clientRobot.direction.xUnitChange);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, clientRobot.direction.yUnitChange, -clientRobot.direction.xUnitChange);
    }
}