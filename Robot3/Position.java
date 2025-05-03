public class Position {
    
    private double xPos;
    private double yPos;
    private Direction direction = Direction.NORTH;

    public Position(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Position(){
        this(0, 0);
    }

    @Override
    public String toString(){
        return "Robot is at (" + xPos + "," + yPos + ")";
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
    public void changeCoordinates(double distance, int xTranslation, int yTranslation){
        this.xPos += distance * xTranslation;
        this.yPos += distance * yTranslation;
    }

    public void moveForward(double distance) {
       changeCoordinates(distance, direction.xUnitChange, direction.yUnitChange);
    }

    public void moveBackward(double distance){
        changeCoordinates(distance, -direction.xUnitChange, -direction.yUnitChange);
    }
    
    public void moveLeft(double distance){
        changeCoordinates(distance, -direction.yUnitChange, direction.xUnitChange);
    }

    public void moveRight(double distance){
        changeCoordinates(distance, direction.yUnitChange, -direction.xUnitChange);
    }

}
