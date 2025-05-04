package Game.ClientPackage.RobotModules;

import java.io.Serializable;

@SuppressWarnings("all")
public enum Direction implements Serializable {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    public final int xUnitChange;
    public final int yUnitChange;

    Direction(int xUnitChange, int yUnitChange) {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }
}