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
        position.move(distance, xUnitChange, yUnitChange);
        robot.consumeFuel(distance);
    }

    // Delta functions.
    public void rotateRight(int rotation){
        int newIndex = (indexDirection + rotation) % 4;
        position.rotate(newIndex);
    }

    public void rotateLeft(int rotation){
        int newIndex = (4 + indexDirection + rotation) % 4;
        position.rotate(newIndex);
    }

    public void moveForward(double distance){
        move(distance, xUnitChange, yUnitChange);
    }

    public void moveBackward(double distance){
        move(distance, -xUnitChange, -yUnitChange);
    }

    public void moveRight(double distance){
        move(distance, yUnitChange, -xUnitChange);
    }

    public void moveLeft(double distance){
        move(distance, -yUnitChange, xUnitChange);
    }

}
