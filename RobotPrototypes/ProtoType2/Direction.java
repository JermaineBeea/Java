public enum Direction{
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);
    
    private final int xUnitChange;
    private final int yUnitChange;

    Direction(int xUnitChange, int yUnitChange) {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }

    public int getXunitChange(){
        return xUnitChange;
    }

    public int getYunitChange(){
        return yUnitChange;
    }
}