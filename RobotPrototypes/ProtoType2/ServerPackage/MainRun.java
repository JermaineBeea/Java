package ServerPackage;

public class MainRun {
    
    public static void main(String[] args) {

        Robot robot = new Robot();
        Position postionRobot = robot.getPosInstance();
        Command robotCommand = new Command(robot);
        robotCommand.backward(-6);
        robotCommand.rotateLeft(6);

        // System.out.println("robot is at (" + robot.position().getX() + "," + robot.position().getY() + ")");
        // System.out.println("Robot is facing " + robot.position().getDirection());
    }
}
