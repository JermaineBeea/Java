package Utility;

import RobotModules.Robot;
import RobotModules.Position;
import org.json.JSONObject;

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nType: ").append(type).append(", ");
        sb.append("\nRateFuelUsage: ").append(rateFuelUsage).append(", ");
        sb.append("\nDurability: ").append(durability).append(", ");
        sb.append("\nMaxShots: ").append(maxShots).append(", ");
        sb.append("\nFuelAmount: ").append(fuelAmount).append(", ");
        sb.append("\nPosition: (x: ").append(position.getX()).append(", y: ").append(position.getY()).append(")");
        return sb.toString();
    }

        // Method to return properties as a JSON object
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("type", type);
            json.put("rateFuelUsage", rateFuelUsage);
            json.put("durability", durability);
            json.put("maxShots", maxShots);
            json.put("fuelAmount", fuelAmount);
    
            JSONObject positionJson = new JSONObject();
            positionJson.put("x", position.getX());
            positionJson.put("y", position.getY());
            positionJson.put("direction", position.getDirection());
    
            json.put("position", positionJson);
    
            return json;
        }


    public static void main(String[] args) {
        // Example usage
        Robot robot = new Robot("GermanBullTerrier", 30, 300, 30);
        RobotString robotString = new RobotString(robot);
        System.out.println(robotString.toJson().toString(4));
    }

}