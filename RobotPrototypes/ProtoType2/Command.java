
public class Command{
    
    private Position posInstance;
    private int xUnitChange;
    private int yUnitChange;
    private int indexDirection;

    Command(Position  instance){
        this.posInstance = instance;
        this.xUnitChange = instance.getDirection().getXunitChange();
        this.yUnitChange = instance.getDirection().getYunitChange();
        this.indexDirection = instance.getDirection().getRotationIndex();
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        posInstance.setX(posInstance.getX() + xUnitChange * distance);
        posInstance.setY(posInstance.getY() + yUnitChange * distance);
    }

    private Direction getDirection(int index){
        return switch(index){
            case 0 -> Direction.EAST;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.WEST;
            case 3 -> Direction.NORTH;
            default -> Direction.NORTH;
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
