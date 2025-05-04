package Game2.ClientPackage.RobotModules;

import java.io.Serializable;

/**
 * Enum representing the four cardinal directions with their movement vectors.
 * Implements Serializable to allow transmission between client and server.
 */
@SuppressWarnings("all")
public enum Direction implements Serializable {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);
    
    /** Unit vector X component for this direction */
    public final int xUnitChange;
    
    /** Unit vector Y component for this direction */
    public final int yUnitChange;

    /**
     * Constructor for Direction enum.
     * @param xUnitChange X component of direction vector
     * @param yUnitChange Y component of direction vector
     */
    Direction(int xUnitChange, int yUnitChange) {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }
}