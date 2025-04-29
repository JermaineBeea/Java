package PositionModules.Position1;

public enum Direction {
    NORTH(0, 1), EAST(1,0), SOUTH(0, -1), WEST(-1, 0);

    private int xUnitChange;
    private int yUnitChange;

    Direction(int xChange, int yChange){
        this.xUnitChange = xChange;
        this.yUnitChange = yChange;
    }

    public int getXUnitChange(){
        return xUnitChange;
    }

    public int getYUnitChange(){
        return yUnitChange;
    }
}
