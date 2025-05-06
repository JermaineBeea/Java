package ServerPackage;
public class Robot {
    
    // Local fields.
    private final double fuelRate;
    private double fuelAmount;

    // Inherited fields.
    private Position posInstance;
    private  Command commandInstance;

    public Robot(){
        this.fuelRate = 10;
        this.fuelAmount = 1000;
        this.posInstance = new Position();
        this.commandInstance  = new Command(posInstance);   
    }

    // Re-assignmnet methods.
    public void setFuelamount(double amount){
        this.fuelAmount = amount;
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

    public Command getCommand(){
        return commandInstance;
    }
}
