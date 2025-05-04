package Game.ServerPackage.RobotModules;

public class Commands {
    
    private Robot robot;

    public Commands(Robot instance){
        this.robot = instance;
    }

    // Updater methods.
    public void updateDirection(Direction directionArg){
        robot.direction = directionArg;
    }

    public void updateXpos(double xPosArg){
        robot.xPos = xPosArg;
    }

    public void updateYpos(double yPosArg){
    robot.yPos = yPosArg;
    }

    public void updateFuel(double amountArg){
        robot.fuelAmount = amountArg;
    }

    // Retrieval methods.
    public String getPos(){
        return "Robot is at (" + robot.xPos + "," + robot.yPos + ")";
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
