package ServerPackage;

public class ServerTest {
    
    public static void main(String[] args) {

        Robot robot = new Robot();
        Position robotPosition = robot.position();
        Command robotCommand = new Command(robot);

        robotPosition.setX(1);
        robotPosition.setY(3);
        robotCommand.backward(6);
        robotCommand.rotateLeft(1);

        System.out.println("robot is at (" + robotPosition.getX() + "," + robotPosition.getY() + ")");
        System.out.println("Robot is facing " + robotPosition.getDirection());
        System.out.println("Fuel amount is " + robot.getFuelAmount());
    }
}
