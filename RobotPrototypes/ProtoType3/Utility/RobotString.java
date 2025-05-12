package Utility;

import RobotModules.Robot;
import RobotModules.Position;

public class RobotString {
    private String name;
    private String type;
    private int rateFuelUsage;
    private int durability;
    private int maxShots;
    private int fuelAmount;
    private Position position;

    // Constructor
    public RobotString(Robot robot) {
        this.name = robot.getName();
        this.type = robot.getBuildType();
        this.rateFuelUsage = robot.getRateUsage();
        this.durability = robot.getDurability();
        this.maxShots = robot.getMaxShots();
        this.fuelAmount = robot.getFuelAmount();
        this.position = robot.getPosition();
   
    }

    // Method to return properties as a JSON-like string
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nType: ").append(type).append(", ");
        sb.append("\nRateFuelUsage: ").append(rateFuelUsage).append(", ");
        sb.append("\nDurability: ").append(durability).append(", ");
        sb.append("\nMaxShots: ").append(maxShots).append(", ");
        sb.append("\nFuelAmount: ").append(fuelAmount).append(", ");
        sb.append("\nPosition: (x: ").append(position.getX()).append(", y: ").append(position.getY()).append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example usage
        Robot robot = new Robot("GermanBullTerrier", 30, 300, 30);
        RobotString robotString = new RobotString(robot);
        System.out.println(robotString.toJson());
    }

}