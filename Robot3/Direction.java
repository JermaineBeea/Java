enum Direction{
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    public int xUnitChange;
    public int yUnitChange;

    Direction(int xUnitChange, int yUnitChange){
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }
}