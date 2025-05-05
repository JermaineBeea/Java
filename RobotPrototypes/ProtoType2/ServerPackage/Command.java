package ServerPackage;

public class Command{
    
    private Robot robotInstance;
    private double fuelAmount;
    private double fuelRate;

    private Position robotPosition;
    private int xUnitChange;
    private int yUnitChange;
    private int indexDirection;

    Command(Robot  instance){
        this.robotInstance = instance;
        this.fuelAmount = instance.getFuelAmount();
        this.fuelRate = instance.getRate();
        this.robotPosition = instance.position();

        // Fetch variables from robot position.
        this.xUnitChange = robotPosition.getDirection().getXunitChange();
        this.yUnitChange = robotPosition.getDirection().getYunitChange();
        this.indexDirection = robotPosition.getDirection().getRotationIndex();
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        robotPosition.setX(robotPosition.getX() + xUnitChange * distance);
        robotPosition.setY(robotPosition.getY() + yUnitChange * distance);
        robotInstance.setFuelamount(fuelAmount - (fuelRate * distance));
    }

    private Direction getDirection(int index){
        return switch(index){
            case 0 -> Direction.EAST;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.WEST;
            case 3 -> Direction.NORTH;
            default -> null;
        };
    } 
    
    // Delta functions.
    public void rotateRight(int rotation){
        int newIndex = (indexDirection + rotation) % 4;
        robotPosition.setDirection(getDirection(newIndex));
    }

    public void rotateLeft(int rotation){
        int newIndex = (4 + indexDirection + rotation) % 4;
        robotPosition.setDirection(getDirection(newIndex));
    }

    public void forward(double distance){
        move(distance, xUnitChange, yUnitChange);
    }

    public void backward(double distance){
        move(distance, -xUnitChange, -yUnitChange);
    }

    public void right(double distance){
        move(distance, yUnitChange, -xUnitChange);
    }

    public void left(double distance){
        move(distance, -yUnitChange, xUnitChange);
    }

}
