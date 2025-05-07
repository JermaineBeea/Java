package ServerPackage;

import ServerPackage.RobotModules.*;

public class ServerTest {
    
    public static void main(String[] args) {
        // Create test instance
        Robot robot = new Robot();
        Position robotPosition = robot.getPosition();
        CommandProcessor commandProcessor = new CommandProcessor(robot);

        try {
            // Set initial position
            robotPosition.setX(1);
            robotPosition.setY(3);
            
            // Execute valid commands
            commandProcessor.executeCommand("left", 8.0);
            commandProcessor.executeCommand("rotateright", 1.0);
            
            // Display results
            System.out.println("Robot is at (" + robotPosition.getX() + "," + robotPosition.getY() + ")");
            System.out.println("Robot is facing " + robotPosition.getDirection());
            System.out.println("Fuel amount is " + robot.getFuelAmount());
            
            // Test fuel exhaustion
            double remainingFuel = robot.getFuelAmount();
            System.out.println("\nTesting fuel exhaustion:");
            double distanceToEmptyFuel = remainingFuel / robot.getRate() + 1.0; // +1 to ensure it's over the limit
            
            try {
                commandProcessor.executeCommand("forward", distanceToEmptyFuel);
                System.out.println("Error: Movement should not be allowed with insufficient fuel");
            } catch (IllegalStateException e) {
                System.out.println("Successfully caught exception: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}