package Game.ClientPackage;

public class Position  {
    
    private double xPos;
    private double yPos;
    private double xInitial;
    private double yInitial;
    private Direction direction;

    public double getXpos(){
        return xPos;
    }

    public double getYpos(){
        return yPos;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
    private void changeCoordinates(double distance, int xTranslation, int yTranslation){
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
