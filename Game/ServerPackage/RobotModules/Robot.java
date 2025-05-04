package Game.ServerPackage.RobotModules;

public class Robot{

    public double RATE_FUEL_USAGE; 
    public double fuelAmount; 
    public Direction direction;
    public double xPos;
    public double yPos;
    
    public Robot(){
        this.RATE_FUEL_USAGE = 10;
        this.fuelAmount = 1000;
        this.direction = Direction.NORTH;
        this.xPos = 0;
        this.yPos = 0;
    }

}
