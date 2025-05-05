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
    public void setFuelamount(double amount){
        this.fuelAmount = amount;
    }

    public Position getPosInstance(){
        return posInstance;
    }

    public double getRate(){
        return fuelRate;
    }

    public double getFuelAmount(){
        return fuelAmount;
    }
}
