package za.co.wethinkcode.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robots.RobotModules.Direction;
import za.co.wethinkcode.robots.RobotModules.Position;
import za.co.wethinkcode.robots.RobotModules.Robot;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Robot robot;
    private Position position;

    @BeforeEach
    void setUp() {
        robot = new Robot("Standard", 10, 100, 5);
        position = new Position(0, 0, Direction.NORTH);
    }

    @Test
    void testRobotInitialization() {
        assertEquals("Standard", robot.getBuildType());
        assertEquals(100, robot.getDurability());
        assertEquals(5, robot.getMaxShots());
        assertEquals(1000, robot.getFuelAmount());
        assertTrue(robot.isAlive());
    }

    @Test
    void testMoveForward() {
        robot.moveForward(5);
        assertEquals(0, robot.getPosition().getX());
        assertEquals(5, robot.getPosition().getY());
        assertEquals(950, robot.getFuelAmount());
    }

    @Test
    void testMoveBackward() {
        robot.moveBackward(3);
        assertEquals(0, robot.getPosition().getX());
        assertEquals(-3, robot.getPosition().getY());
        assertEquals(970, robot.getFuelAmount());
    }

    @Test
    void testRotateRight() {
        robot.rotateRight(1);
        assertEquals(Direction.EAST, robot.getPosition().getDirection());
    }

    @Test
    void testRotateLeft() {
        robot.rotateLeft(1);
        assertEquals(Direction.WEST, robot.getPosition().getDirection());
    }

    @Test
    void testTakeDamage() {
        robot.takeDamage(30);
        assertEquals(70, robot.getDurability());
        assertTrue(robot.isAlive());
    }

    @Test
    void testTakeLethalDamage() {
        robot.takeDamage(120);
        assertEquals(0, robot.getDurability());
        assertFalse(robot.isAlive());
    }

    @Test
    void testRepair() {
        robot.takeDamage(50);
        robot.repair();
        assertEquals(100, robot.getDurability());
        assertEquals(980, robot.getFuelAmount());
    }

    @Test
    void testShoot() {
        robot.shoot();
        assertEquals(4, robot.getMaxShots());
    }

    @Test
    void testReload() {
        robot.shoot();
        robot.reload();
        assertEquals(5, robot.getMaxShots());
    }

    @Test
    void testRefuel() {
        robot.moveForward(10);
        robot.refuel();
        assertEquals(1000, robot.getFuelAmount());
    }

    @Test
    void testPositionInitialization() {
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
        assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    void testPositionMoveForward() {
        position.moveForward(5);
        assertEquals(0, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testPositionMoveBackward() {
        position.moveBackward(3);
        assertEquals(0, position.getX());
        assertEquals(-3, position.getY());
    }

    @Test
    void testPositionRotateRight() {
        position.rotateRight(1);
        assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    void testPositionRotateLeft() {
        position.rotateleft(1);
        assertEquals(Direction.WEST, position.getDirection());
    }
}