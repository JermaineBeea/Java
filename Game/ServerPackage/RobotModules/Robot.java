package Game.ServerPackage.RobotModules;

import Game.ServerPackage.PositionModules.Position;

public class Robot extends Position {

    private String name;
    private double fuelAmount = 10000;
    private double RATE_FUEL_USAGE; // Units of fuel usage per 1 unit of distance.
    
    Robot(String name, double rateFuelUsage){
        this.name = name;
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

   public void reFuel(double amount){
        if(amount instanceof Number && amount > 0){
            this.fuelAmount += amount;
        }else{
            System.err.println("Fuel amount" + amount + ", is invalid!");
        }
   }

   public String getName(){
        return name;
   }

   public double getFuel(){
        return fuelAmount;
   }

}
