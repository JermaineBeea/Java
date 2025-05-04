package Game2.ServerPackage.RobotModules;

/**
 * Data model for the robot on the server side.
 */
public class ServerRobot{

    public String name;
    public double rateFuelUsage; 
    public double fuelAmount; 
    public Direction direction;
    public double xPos;
    public double yPos;
    
    /**
     * Initializes a new robot with default values.
     * - Position at origin (0,0)
     * - Facing NORTH
     */
    public ServerRobot(){
        this.direction = Direction.NORTH;
        this.xPos = 0;
        this.yPos = 0;
    }
}