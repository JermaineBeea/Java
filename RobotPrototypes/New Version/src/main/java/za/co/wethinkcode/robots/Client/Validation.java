package za.co.wethinkcode.robots.server.RobotGame.ClientPackage;

import java.util.*;
import za.co.wethinkcode.robots.server.RobotGame.RobotPackage.Direction;
import za.co.wethinkcode.robots.server.RobotGame.RobotPackage.Position;

public class Validation {

    private final int worldWidth;
    private final int worldHeight;

    public Validation(int width, int height) {
        this.worldWidth = width;
        this.worldHeight = height;
    }

    public boolean isWithinBounds(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return x >= 0 && x < worldWidth && y >= 0 && y < worldHeight;
    }

    public boolean isObstacle(Position pos, List<Position> obstacles) {
        return obstacles.contains(pos);
    }

    public boolean isRobot(Position pos, List<Position> robotPositions) {
        return robotPositions.contains(pos);
    }

    public boolean isValidMove(Position newPosition, List<Position> obstacles, List<Position> robots) {
        return isWithinBounds(newPosition)
                && !isObstacle(newPosition, obstacles)
                && !isRobot(newPosition, robots);
    }

    // ✅ Updated to include Direction parameter
    public boolean isValidFirePath(Position current, Direction direction, List<Position> obstacles, List<Position> robots, int range) {
        for (int steps = 1; steps <= range; steps++) {
            Position next = current.getRelativePosition(direction, steps);  // Make sure Position supports this
            if (!isWithinBounds(next)) {
                return false;
            }
            if (isObstacle(next, obstacles) || isRobot(next, robots)) {
                return true; // Stops at the first object
            }
        }
        return true;
    }
}
