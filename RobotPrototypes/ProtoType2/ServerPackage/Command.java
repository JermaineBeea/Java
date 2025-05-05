package ServerPackage;

public class Command{
    
    private Robot robotInstance;
    private double fuelAmount;
    private double fuelRate;

    private Position posInstance;
    private int xUnitChange;
    private int yUnitChange;
    private int indexDirection;

    Command(Robot  instance){
        this.robotInstance = instance;
        this.fuelAmount = instance.getFuelAmount();
        this.fuelRate = instance.getRate();
        this.posInstance = instance.getPosInstance();
        this.xUnitChange = instance.getPosInstance().getDirection().getXunitChange();
        this.yUnitChange = instance.getPosInstance().getDirection().getYunitChange();
        this.indexDirection = instance.getPosInstance().getDirection().getRotationIndex();
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        posInstance.setX(posInstance.getX() + xUnitChange * distance);
        posInstance.setY(posInstance.getY() + yUnitChange * distance);
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
        posInstance.setDirection(getDirection(newIndex));
    }

    public void rotateLeft(int rotation){
        int newIndex = (4 + indexDirection + rotation) % 4;
        posInstance.setDirection(getDirection(newIndex));
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
    //

}
