package ProtoType1.ServerPackage.ServerSideModules;

import ProtoType1.ServerPackage.RobotModules.*;

/**
 * Handles robot state management on the server side.
 * Updates and retrieves robot properties.
 */
public class ServerCommands {
    
    private ServerRobot robot;

    /**
     * Initializes ServerCommands with a robot instance.
     * @param instance The server robot to manage
     */
    public ServerCommands(ServerRobot instance){
        this.robot = instance;
    }

    /**
     * Updates the robot's position coordinates.
     * @param xPosArg New X position
     * @param yPosArg New Y position
     */
    public void updatePosition(double xPosArg, double yPosArg){
        robot.xPos = xPosArg;
        robot.yPos = yPosArg;
    }

    /**
     * Updates the robot's direction.
     * @param directionArg New direction
     */
    public void updateDirection(Direction directionArg){
        robot.direction = directionArg;
    }

    /**
     * Displays the robot's current position and direction
     */
    public String getPosition(){
        return "Robot is at (" + robot.xPos + "," + robot.yPos + ") facing " + robot.direction;
    }

    /**
     * @return The robot's current X position
     */
    public double getXpos(){
        return robot.xPos;
    }

    /**
     * @return The robot's current Y position
     */
    public double getYpos(){
        return robot.yPos;
    }

    /**
     * @return The robot's current direction
     */
    public Direction getDirection(){
        return robot.direction;
    }
}