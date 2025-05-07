package ClientPackage;

public enum Direction{
     EAST, SOUTH, WEST, NORTH;
    
    public static Direction getDirectionName(int index){
        return switch(index){
            case 0 -> Direction.EAST;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.WEST;
            case 3 -> Direction.NORTH;
            default -> null;
        };
    } 

}