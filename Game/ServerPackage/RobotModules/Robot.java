package Game.ServerPackage.RobotModules;

public class Robot{

    // Fixed properties
    private String name;
    private double RATE_FUEL_USAGE; // Units of fuel usage per 1 unit of distance.

    // Dynamic properties.
    private double fuelAmount = 10000;
    private Direction direction;
    private double xPos;
    private double yPos;
    
    Robot(String name, double rateFuelUsage){
        this.name = name;
        this.RATE_FUEL_USAGE = rateFuelUsage;
    }
  
   public String getInfo(){
        return "Type: " + name + " | Rate fuel usage: " + RATE_FUEL_USAGE;
   }

   public String getPos(){
        return "Robot is at (" + xPos + "," + yPos + ")";
   }

   public double getXpos(){
        return xPos;
   }

   public double getYpos(){
        return yPos;
   }

   public void updateXpos(double xPos){
        this.xPos = xPos;
   }

   public void updateYpos(double yPos){
    this.yPos = yPos;
    }

    public void updateFuel(double amount){
        this.fuelAmount = amount;
    }

}
