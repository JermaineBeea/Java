import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robots.server.RobotGame.ClientPackage.Validation;
import za.co.wethinkcode.robots.server.RobotGame.RobotPackage.Position;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    private Validation validation;
    private List<Position> obstacles;
    private List<Position> robots;

    @BeforeEach
    void setUp() {
        validation = new Validation(5, 5);
        obstacles = Arrays.asList(new Position(1, 1), new Position(2, 2));
        robots = Arrays.asList(new Position(3, 3), new Position(4, 4));
    }

    @Test
    void testIsWithinBounds() {
        assertTrue(validation.isWithinBounds(new Position(0, 0)));
        assertTrue(validation.isWithinBounds(new Position(4, 4)));
        assertFalse(validation.isWithinBounds(new Position(5, 5)));
        assertFalse(validation.isWithinBounds(new Position(-1, 0)));
    }

    @Test
    void testIsObstacle() {
        assertTrue(validation.isObstacle(new Position(1, 1), obstacles));
        assertFalse(validation.isObstacle(new Position(0, 0), obstacles));
    }

    @Test
    void testIsRobot() {
        assertTrue(validation.isRobot(new Position(3, 3), robots));
        assertFalse(validation.isRobot(new Position(1, 1), robots));
    }

    @Test
    void testIsValidMove() {
        assertTrue(validation.isValidMove(new Position(0, 0), obstacles, robots));
        assertFalse(validation.isValidMove(new Position(1, 1), obstacles, robots));
        assertFalse(validation.isValidMove(new Position(3, 3), obstacles, robots));
        assertFalse(validation.isValidMove(new Position(6, 6), obstacles, robots));
    }

    @Test
    void testIsValidFirePathHitsObstacle() {
        Position start = new Position(0, 0) {
            @Override
            public Position getRelativePosition(int steps) {
                return new Position(steps, steps);
            }
        };
        assertTrue(validation.isValidFirePath(start, obstacles, robots, 5));
    }

    @Test
    void testIsValidFirePathWithDirectionHitsObstacle() {
        Position start = new Position(0, 0) {
            @Override
            public Position getRelativePosition(Direction dir, int steps) {
                switch (dir) {
                    case NORTH: return new Position(0, steps);
                    case EAST: return new Position(steps, 0);
                    default: return new Position(0, 0);  // simplify
                }
            }
        };
        assertTrue(validation.isValidFirePath(start, Direction.NORTH, obstacles, robots, 5));
    }


    @Test
    void testIsValidFirePathNoTarget() {
        Position start = new Position(0, 0) {
            @Override
            public Position getRelativePosition(int steps) {
                return new Position(0, steps);
            }
        };
        assertTrue(validation.isValidFirePath(start, Arrays.asList(), Arrays.asList(), 4));
    }

    @Test
    void testIsValidFirePathOutOfBounds() {
        Position start = new Position(0, 0) {
            @Override
            public Position getRelativePosition(int steps) {
                return new Position(0, 5 + steps);
            }
        };
        assertFalse(validation.isValidFirePath(start, obstacles, robots, 3));
    }
}
