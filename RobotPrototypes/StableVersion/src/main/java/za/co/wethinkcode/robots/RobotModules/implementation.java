package za.co.wethinkcode.robots.RobotModules;

public class implementation {
    
    public static void main(String[] args) {
        // Example usage of the Robot class
        Robot robot = RobotTypes.getRobot("GermanBullTerrier");
        System.out.println("Robot Build Type: " + robot.getBuildType());
        System.out.println("Robot Durability: " + robot.getDurability());
        System.out.println("Robot Fuel Amount: " + robot.getFuelAmount());
        System.out.println("Robot Position: " + robot.getPosition().getX() + ", " + robot.getPosition().getY());
     
        // Example usage of the RobotTypes class
        robot.moveForward(4);
        robot.rotateRight(3);

        System.out.println("\nFinal Robot Position: " + robot.getPosition().getX() + ", " + robot.getPosition().getY());
        System.out.println(" Robot is facing`: " + robot.getPosition().getDirection());
        System.out.println("Robot Fuel Amount after movement: " + robot.getFuelAmount());

    }
}
