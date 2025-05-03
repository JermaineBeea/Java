package Game.ServerPackage.RobotModules;

public class Robot extends Position {

    private String name;
    private double fuelAmount = 1000;
    private final double RATE_FUEL_USAGE = 3.5; // Units of fuel usage per 1 unit of distance.
    
    public Robot(double xPos, double yPos){
        super(xPos, yPos);
    }
  
   @Override
   public void changeCoordinates(double distance, int xTranslation, int yTranslation) {
        if(fuelAmount >= fuelAmount * distance){       
            super.changeCoordinates(distance, xTranslation, yTranslation);
            this.fuelAmount -= distance * RATE_FUEL_USAGE;
        }else{
            System.out.println("Check fuel level!");
            System.out.println("Not enough fuel to go distance " + distance);
        }
   }

   public String getName(){
        return name;
   }

   public double getFuel(){
        return fuelAmount;
   }

}
