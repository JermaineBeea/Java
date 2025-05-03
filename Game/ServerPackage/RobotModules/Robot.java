package Game.ServerPackage.RobotModules;

public class Robot extends Position {

    private String name;
    private double fuelAmount;
    private double RATE_FUEL_USAGE; // Units of fuel usage per 1 unit of distance.
    
    Robot(String name, double fuelAmount, double rateFuelUsage){
        this.name = name;
        this.fuelAmount = fuelAmount;
        this.RATE_FUEL_USAGE = rateFuelUsage;
    }
  
   @Override
   public void changeCoordinates(double distance, int xTranslation, int yTranslation) {
        if(fuelAmount >= fuelAmount * distance){       
            super.changeCoordinates(distance, xTranslation, yTranslation);
            this.fuelAmount -= distance * RATE_FUEL_USAGE;
        }else{
            System.out.println("Not enough fuel to go distance " + distance);
            System.out.println("Fuel level is: " + fuelAmount);
            System.out.println("Engine uses " + RATE_FUEL_USAGE + ", per 1 distance.");
        }
   }

   public String getName(){
        return name;
   }

   public double getFuel(){
        return fuelAmount;
   }

}
