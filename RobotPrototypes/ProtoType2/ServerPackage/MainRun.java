package ServerPackage;

public class MainRun {
    
    public static void main(String[] args) {

        Robot robot = new Robot();
        robot.position().setX(3);
        robot.position().setY(4);
        robot.command().forward(8.5);
        robot.command().rotateLeft(3);

        System.out.println("robot is at (" + robot.position().getX() + "," + robot.position().getY() + ")");
        System.out.println("Robot is facing " + robot.position().getDirection());
    }
}
