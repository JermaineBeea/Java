public class Robot{

    private Position position;
    private String name;
    private String buildType;
    private int durability;
    private int maxShots;
    private int fuelAmount;
    private int rateFuelUsage;

    public Robot(String buildArg, int durabilityArg, int maxShotsArg, int rateArg){
        this.name  = "";
        this.fuelAmount = 10000;
        this.buildType = buildArg;
        this.durability = durabilityArg;
        this.maxShots = maxShotsArg;
        this.rateFuelUsage = rateArg;
        this.position = new Position(0, 0, Direction.NORTH);

    }

    // Assignment Methods.
    public void setName(String nameArg){
        this.name = nameArg;
    }

    public void setFuel(){

    }

    // Access Methods.
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

    // Delta Methods.
    public void moveForward(){

    }

}