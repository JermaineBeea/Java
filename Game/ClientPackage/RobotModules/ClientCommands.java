package Game.ClientPackage.RobotModules;

import java.util.*;
import java.util.function.Consumer;

public class ClientCommands {
    
    private ClientRobot clientRobot;
    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();

    public ClientCommands(ClientRobot instance){
        this.clientRobot = instance;
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);  // Fixed the typo from 'backwards' to 'backward'
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }

    // Executes String command.
    public void executeCommand(String strCommand, double commandQuantity){
        Consumer<Double> command = mapCommands.get(strCommand.toLowerCase());
        if (command != null) {
            command.accept(commandQuantity);
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

    public String viewPos(){
        return "Robot is at (" + clientRobot.xPos + ", " + clientRobot.yPos + ") facing " + clientRobot.direction;
    }

    
    // Helper methods.
    private void changeCoordinates(double distance, int xTranslation, int yTranslation){
        clientRobot.xPos += distance * xTranslation;
        clientRobot.yPos += distance * yTranslation;
    }

    // Setter methods.
    public void setDirection(Direction direction){
        clientRobot.direction = direction;
    }

    public void setPos(double xPos, double yPos){
        clientRobot.xPos = xPos;
        clientRobot.yPos = yPos;
    }

    public void setFuel(double amount){
        clientRobot.fuelAmount = amount;
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