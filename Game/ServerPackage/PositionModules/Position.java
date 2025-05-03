package Game.ServerPackage.PositionModules;

public class Position {
    
    private double xPos = 0;
    private double yPos = 0;
    private double xInitial;
    private double yInitial;
    private boolean positionSet = false;
    private Direction direction = Direction.NORTH;

    public void setPos(double xinit, double yinit) {
        if (!this.positionSet) {
            this.xPos = xinit;
            this.yPos = yinit;
            this.xInitial = xinit;
            this.yInitial = yinit;
            this.positionSet = true;
        }
        System.out.println("Cannot re-set Position!");
        System.out.println("Position had been set to (" + xInitial +  ", " + yInitial + ")");
    }

    public boolean isPositionSet(){
        return positionSet;
    }

    public double getXpos(){
        return xPos;
    }

    public double getYpos(){
        return yPos;
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
