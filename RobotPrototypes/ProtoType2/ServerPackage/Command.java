package ServerPackage;

public class Command{
    
    private Position position;
    private Robot robot;
    private int xUnitChange;
    private int yUnitChange;
    private int indexDirection;

    Command(Robot  instance){
        this.robot = instance;
        this.position = instance.getPosition();

        // Fetch variables from robot position.
        this.xUnitChange = position.getDirection().getXunitChange();
        this.yUnitChange = position.getDirection().getYunitChange();
        this.indexDirection = position.getDirection().getRotationIndex();
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        position.setX(position.getX() + xUnitChange * distance);
        position.setY(position.getY() + yUnitChange * distance);
        robot.consumeFuel(distance);
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
        position.setDirection(getDirection(newIndex));
    }

    public void rotateLeft(int rotation){
        int newIndex = (4 + indexDirection + rotation) % 4;
        position.setDirection(getDirection(newIndex));
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
