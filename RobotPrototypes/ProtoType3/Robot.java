public class Robot{

    private String name;

    // Imutable once set.
    private final int FUEL_AMOUNT = 1000;
    private String buildType;
    private int rateFuelUsage;

    // Mutable properties.
    private int durability;
    private int maxShots;
    private int fuelAmount;
    private Position robotPosition;

    public Robot(String buildArg, int durabilityArg, int maxShotsArg, int rateArg){
        this.name  = "";
        this.fuelAmount = FUEL_AMOUNT; // All robots have intial fuel amount
        this.robotPosition = new Position(0, 0, Direction.NORTH);

        this.buildType =  buildArg;
        this.durability = durabilityArg;
        this.maxShots = maxShotsArg;
        this.rateFuelUsage = rateArg;

    }
    // Delta Methods.

    public void repair(){

    }

    public void refuel(){

    }

    public void shoot(){
        
    }

    // Assignment Methods.
    
    public void setName(String nameArg){
        this.name = nameArg;
    }

    public void setFuel(int amount){
        this.fuelAmount = amount;
    }

    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setFuelAmount(int amount){
        this.fuelAmount = amount;
    }

    // Access Methods.

    public Position position(){
        return robotPosition;
    }

    public String getName(){
        return name;
    }

    public String getBuild(){
        return buildType;
    }

    public int getDurability(){
        return durability;
    }

    public int getMaxShots(){
        return maxShots;
    }

    public int getFuelAmount(){
        return fuelAmount;
    }

    public int getRate(){
        return rateFuelUsage;
    }

    

}