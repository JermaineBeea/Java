package ClientPackage;
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

    public Direction getDirection(int index){
        return switch(index){
            case 0 -> Direction.EAST;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.WEST;
            case 3 -> Direction.NORTH;
            default -> null;
        };
    } 
    
    public int getXunitChange(){
        return xUnitChange;
    }

    public int getYunitChange(){
        return yUnitChange;
    }

    public int getRotationIndex(){
        return rotationIndex;
    }

}