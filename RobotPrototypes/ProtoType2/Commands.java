public class Commands {
    
    private Position posInstance;
    private int xUnitChange;
    private int yUnitChange;

    Commands(Position  instance){
        this.posInstance = instance;
        xUnitChange = instance.getDirection().getXunitChange();
        yUnitChange = instance.getDirection().getYunitChange();
    }

    // Helper functions
    private void move(double distance, int xUnitChange, int yUnitChange){
        posInstance.setX(posInstance.getX() + xUnitChange * distance);
        posInstance.setY(posInstance.getY() + yUnitChange * distance);
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
