package ServerPackage;

public class ServerTest {
    
    public static void main(String[] args) {

        Robot robot = new Robot();
        Position robotPosition = robot.getPosition();
        CommandProcessor commandProcessor = new CommandProcessor(robot);

        robotPosition.setX(1);
        robotPosition.setY(3);
        commandProcessor.executeCommand("left", 8.0);
        commandProcessor.executeCommand("rotate", null);

        System.out.println("robot is at (" + robotPosition.getX() + "," + robotPosition.getY() + ")");
        System.out.println("Robot is facing " + robotPosition.getDirection());
        System.out.println("Fuel amount is " + robot.getFuelAmount());

        
    }
}
