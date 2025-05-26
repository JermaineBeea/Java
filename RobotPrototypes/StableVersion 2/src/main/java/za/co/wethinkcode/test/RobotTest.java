package za.co.wethinkcode.test;
import za.co.wethinkcode.robots.RobotModules.*;

/**
 * Simple unit tests for Robot commands and World functionality
 */
public class RobotTest {
    
    private static int testsPassed = 0;
    private static int totalTests = 0;
    
    public static void main(String[] args) {
        System.out.println("Running Robot Command Tests...\n");
        
        testRobotMovement();
        testRobotRotation();
        testRobotCombat();
        // testWorldManagement();
        testFuelConsumption();
        
        System.out.println("\n=== TEST RESULTS ===");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        System.out.println("Success rate: " + (testsPassed * 100 / totalTests) + "%");
    }
    
    private static void testRobotMovement() {
        System.out.println("--- Testing Robot Movement ---");
        
        Robot robot = new Robot("TestBot", 10, 100, 5);
        robot.setName("MoveBot");
        
        // Test initial position
        assert robot.getPosition().getX() == 0 : "Initial X should be 0";
        assert robot.getPosition().getY() == 0 : "Initial Y should be 0";
        assert robot.getPosition().getDirection() == Direction.NORTH : "Initial direction should be NORTH";
        testResult("Initial position", true);
        
        // Test forward movement
        int initialFuel = robot.getFuelAmount();
        robot.moveForward(5);
        testResult("Move forward", robot.getPosition().getY() == 5 && robot.getFuelAmount() == initialFuel - 50);
        
        // Test backward movement
        robot.moveBackward(2);
        testResult("Move backward", robot.getPosition().getY() == 3);
        
        // Test side movement
        robot.moveRight(3);
        testResult("Move right", robot.getPosition().getX() == -3); // Right is negative X when facing North
        
        robot.moveLeft(1);
        testResult("Move left", robot.getPosition().getX() == -2); // Left is positive X when facing North
    }
    
    private static void testRobotRotation() {
        System.out.println("\n--- Testing Robot Rotation ---");
        
        Robot robot = new Robot("RotateBot", 10, 100, 5);
        robot.setName("Spinner");
        
        // Test right rotation
        robot.rotateRight(1);
        testResult("Rotate right once", robot.getPosition().getDirection() == Direction.EAST);
        
        robot.rotateRight(2);
        testResult("Rotate right twice more", robot.getPosition().getDirection() == Direction.WEST);
        
        // Test left rotation
        robot.rotateLeft(1);
        testResult("Rotate left once", robot.getPosition().getDirection() == Direction.SOUTH);
        
        // Test full rotation
        robot.rotateRight(4);
        testResult("Full rotation", robot.getPosition().getDirection() == Direction.SOUTH);
    }
    
    private static void testRobotCombat() {
        System.out.println("\n--- Testing Robot Combat ---");
        
        Robot robot = new Robot("Warrior", 10, 100, 3);
        robot.setName("Fighter");
        
        int initialShots = robot.getMaxShots();
        
        // Test shooting
        robot.shoot();
        testResult("Shoot reduces ammo", robot.getMaxShots() == initialShots - 1);
        
        // Test taking damage
        robot.takeDamage(30);
        testResult("Take damage", robot.getDurability() == 70);
        
        // Test alive status
        testResult("Robot alive", robot.isAlive());
        
        // Test reload
        int fuelBefore = robot.getFuelAmount();
        robot.setFuelAmount(100); // Ensure enough fuel
        robot.reload();
        testResult("Reload restores ammo", robot.getMaxShots() == initialShots);
        
        // Test repair
        robot.repair();
        testResult("Repair restores health", robot.getDurability() == 100);
    }
    
    // private static void testWorldManagement() {
    //     System.out.println("\n--- Testing World Management ---");
        
    //     World world = new World();
    //     Robot robot1 = new Robot("Scout", 10, 80, 4);
    //     robot1.setName("Explorer");
        
    //     Robot robot2 = new Robot("Tank", 20, 150, 2);
    //     robot2.setName("Guardian");
        
    //     // Test robot launch
    //     testResult("Launch first robot", world.launchRobot(robot1));
    //     testResult("Launch second robot fails (same position)", !world.launchRobot(robot2));
        
    //     // Move first robot and try again
    //     robot1.moveForward(5);
    //     testResult("Launch second robot after first moved", world.launchRobot(robot2));
        
    //     // Test world tracking
    //     testResult("World has 2 robots", world.getAllRobots().size() == 2);
        
    //     // Test robot retrieval
    //     testResult("Get robot by name", world.getRobot("Explorer") != null);
        
    //     // Test position validation
    //     Position validPos = new Position(50, 50, Direction.NORTH);
    //     Position invalidPos = new Position(200, 200, Direction.NORTH);
    //     testResult("Valid position check", world.isValidPosition(validPos));
    //     testResult("Invalid position check", !world.isValidPosition(invalidPos));
    // }
    
    private static void testFuelConsumption() {
        System.out.println("\n--- Testing Fuel Consumption ---");
        
        Robot robot = new Robot("Efficient", 5, 100, 5);
        robot.setName("EcoBot");
        
        int initialFuel = robot.getFuelAmount();
        
        // Test movement fuel consumption
        robot.moveForward(10);
        testResult("Fuel consumed for movement", robot.getFuelAmount() == initialFuel - 50);
        
        // Test insufficient fuel
        robot.setFuelAmount(10); // Set low fuel
        int fuelBefore = robot.getFuelAmount();
        int xBefore = robot.getPosition().getX();
        robot.moveForward(5); // Should require 25 fuel but only have 10
        testResult("Insufficient fuel prevents movement", 
                  robot.getFuelAmount() == fuelBefore && robot.getPosition().getX() == xBefore);
        
        // Test refuel
        robot.refuel();
        testResult("Refuel restores fuel", robot.getFuelAmount() == 1000);
    }
    
    private static void testResult(String testName, boolean passed) {
        totalTests++;
        if (passed) {
            testsPassed++;
            System.out.println("✓ " + testName + " - PASSED");
        } else {
            System.out.println("✗ " + testName + " - FAILED");
        }
    }
}