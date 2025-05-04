package Game.ServerPackage.RobotModules;

public class Robot{

    // Fixed properties
    private String name;
    private double RATE_FUEL_USAGE; // Units of fuel usage per 1 unit of distance.

    // Dynamic properties. Deafult properties for all robot tyeps
    // Default values are sent to client.
    public double fuelAmount; 
    public Direction direction;
    public double xPos;
    public double yPos;
    
    Robot(String name, double rateFuelUsage){
        this.name = name;
        this.RATE_FUEL_USAGE = rateFuelUsage;
        this.fuelAmount = 1000;
        this.direction = Direction.NORTH;
        this.xPos = 0;
        this.yPos = 0;
    }

    // Retrieval methods.
    public String getInfo(){
        return "Type: " + name + " | Rate fuel usage: " + RATE_FUEL_USAGE;
    }

}
