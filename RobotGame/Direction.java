package RobotGame;

/**
 * Represents possible movement directions for the robot.
 */
public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, 1), DOWN(0, -1);

    private final int xUnitChange;
    private final int yUnitChange;

    Direction(int xUnitChange, int yUnitChange) {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }

    public int getXUnitChange() {
        return xUnitChange;
    }

    public int getYUnitChange() {
        return yUnitChange;
    }
}