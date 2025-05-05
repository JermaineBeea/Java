package ServerPackage;

public class MainRun {
    
    public static void main(String[] args) {

        Robot robot = new Robot();
        Position robotPosition = robot.position();
        Command robotCommand = new Command(robot);

        robotPosition.setX(1);
        robotPosition.setY(3);
        robotCommand.backward(-6);
        robotCommand.rotateLeft(6);

        // System.out.println("robot is at (" + robot.position().getX() + "," + robot.position().getY() + ")");
        // System.out.println("Robot is facing " + robot.position().getDirection());
    }
}
