package za.co.wethinkcode.robots.RobotModules;

public enum Direction{
     EAST(0, 1, 0), 
     SOUTH(1, 0, -1), 
     WEST(2, -1, 0), 
     NORTH(3, 0, 1);
    
    private final int xUnitChange;
    private final int yUnitChange;
    private final int rotationIndex;

    Direction(int rotationIndex, int xUnitChange, int yUnitChange) {
        this.rotationIndex = rotationIndex;
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }

    public static Direction getDirectionName(int index) {
        if (index == 0) {
            return Direction.EAST;
        } else if (index == 1) {
            return Direction.SOUTH;
        } else if (index == 2) {
            return Direction.WEST;
        } else if (index == 3) {
            return Direction.NORTH;
        } else {
            return null;
        }
    }    
    public int getXunitChange(){
        return xUnitChange;
    }

    public int getYunitChange(){
        return yUnitChange;
    }

    public int getIndex(){
        return rotationIndex;
    }

}