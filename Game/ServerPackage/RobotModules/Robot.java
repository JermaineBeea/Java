package Game.ServerPackage.RobotModules;

public class Robot{

    public String name;
    public double rateFuelUsage; 
    public double fuelAmount; 
    public Direction direction;
    public double xPos;
    public double yPos;
    
    public Robot(String name, double rateFuelUsage){
        this.name = name;
        this.rateFuelUsage = rateFuelUsage;

        this.fuelAmount = 1000;
        this.direction = Direction.NORTH;
        this.xPos = 0;
        this.yPos = 0;
    }

}
