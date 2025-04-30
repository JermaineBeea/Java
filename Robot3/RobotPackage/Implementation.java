package RobotPackage;

public class Implementation {
    public static void main(String[] args) {
        Robot robot = new Robot(0, 0);
        robot.setDirection(Direction.NORTH);
        robot.forward(2);

        robot.setDirection(Direction.EAST);
        robot.right(4);
        
        System.err.println(robot.getDirection());
        System.out.println(robot.getXPos());
        System.out.println(robot.getYPos());
    }

}
