package ServerPackage;
public class Robot {
    
    // Local fields.
    private final double fuelRate;
    private double fuelAmount;

    // Inherited fields.
    private Position posInstance;

    public Robot(){
        this.fuelRate = 10;
        this.fuelAmount = 1000;
        this.posInstance = new Position();
    }

    // Re-assignmnet methods.
    public void setFuelAmount(double amount){
        this.fuelAmount = amount;
    }

    // Delta methods.
    public void consumeFuel(double distance){
        this.fuelAmount -= fuelRate * distance;
    }

    // Retrieval methods.
    public boolean canMove(double distance){
        return fuelAmount >= fuelRate * distance;
    }

    public double getRate(){
        return fuelRate;
    }

    public double getFuelAmount(){
        return fuelAmount;
    }

    public Position getPosition(){
        return posInstance;
    }

}
