package Game.ServerPackage.RobotModules;

public class ServerCommands {
    
    private Robot robot;

    public ServerCommands(Robot instance){
        this.robot = instance;
    }

    // Updater methods.
    public void updatePos(double xPosArg, double yPosArg, Direction directionArg){
        robot.xPos = xPosArg;
        robot.yPos = yPosArg;
        robot.direction = directionArg;
    }

    public void updateFuel(double amountArg){
        robot.fuelAmount = amountArg;
    }

    // Retrieval methods.
    public String viewPosition(){
        return "Robot is at (" + robot.xPos + "," + robot.yPos + ") facing " + robot.direction;
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
    
}
