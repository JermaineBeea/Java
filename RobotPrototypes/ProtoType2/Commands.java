import java.util.Hashtable;

public class Commands {
    
    private Position posInstance;
    private int xUnitChange;
    private int yUnitChange;
    private Hashtable<Direction, Integer> setDirection = new Hashtable<>();

    Commands(Position  instance){
        this.posInstance = instance;
        xUnitChange = instance.getDirection().getXunitChange();
        yUnitChange = instance.getDirection().getYunitChange();
        setDirection.put(Direction.EAST, 0);
        setDirection.put(Direction.SOUTH, 1);
        setDirection.put(Direction.WEST, 2);
        setDirection.put(Direction.NORTH, 3);
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        posInstance.setX(posInstance.getX() + xUnitChange * distance);
        posInstance.setY(posInstance.getY() + yUnitChange * distance);
    }

    private void rotate(int quantity){
        int index = setDirection.get(posInstance.getDirection());
    }

    // Delta functions.
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
