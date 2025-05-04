package Game.ServerPackage.ServerSideModules;

import Game.ServerPackage.RobotModules.Direction;
import Game.ServerPackage.RobotModules.Robot;

public class ServerCommands {
    
    private Robot robot;

    public ServerCommands(Robot instance){
        this.robot = instance;
    }

    // Updater methods.
    public void updatePosition(double xPosArg, double yPosArg){
        robot.xPos = xPosArg;
        robot.yPos = yPosArg;
    }

    public void updateDirection(Direction directionArg){
        robot.direction = directionArg;
    }

    public void updateFuel(double amountArg){
        robot.fuelAmount = amountArg;
    }

    // Retrieval methods.
    public void viewPosition(){
        System.out.println("Robot is at (" + robot.xPos + "," + robot.yPos + ") facing " + robot.direction);
    }

    public double getXpos(){
        return robot.xPos;
    }

    public double getYpos(){
        return robot.yPos;
    }

    public Direction getDirection(){
        return robot.direction;
    }

    public double getFuel(){
        return robot.fuelAmount;
    }

    public double getRateFuelUsage(){
        return robot.rateFuelUsage;
    }
    
}
