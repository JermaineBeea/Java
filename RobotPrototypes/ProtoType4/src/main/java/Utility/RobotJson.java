package Utility;

import RobotModules.Robot;
import RobotModules.Position;
import org.json.JSONObject;

public class RobotJson {
    private String name;
    private String type;
    private int rateFuelUsage;
    private int durability;
    private int maxShots;
    private int fuelAmount;
    private Position position;

    // Constructor
    public RobotJson(Robot robot) {
        this.name = robot.getName();
        this.type = robot.getBuildType();
        this.rateFuelUsage = robot.getRateUsage();
        this.durability = robot.getDurability();
        this.maxShots = robot.getMaxShots();
        this.fuelAmount = robot.getFuelAmount();
        this.position = robot.getPosition();
   
    }

    public static void displayJSON(JSONObject jsonObject) {
        System.out.println(jsonObject.toString(4));
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

        // JSONObject positionJson = new JSONObject();
        // positionJson.put("x", position.getX());
        // positionJson.put("y", position.getY());
        // positionJson.put("direction", position.getDirection());

        return json;
    }

    /*
    import java.util.LinkedHashMap;
    
    public JSONObject toJson() {
        LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("name", name);
        jsonMap.put("type", type);
        jsonMap.put("rateFuelUsage", rateFuelUsage);
        jsonMap.put("durability", durability);
        jsonMap.put("maxShots", maxShots);
        jsonMap.put("fuelAmount", fuelAmount);
    
        LinkedHashMap<String, Object> positionMap = new LinkedHashMap<>();
        positionMap.put("x", position.getX());
        positionMap.put("y", position.getY());
        positionMap.put("direction", position.getDirection());
    
        jsonMap.put("position", positionMap);
    
        return new JSONObject(jsonMap);
    }
        */

    public static void main(String[] args) {
        // Example usage
        Robot robot = new Robot("GermanBullTerrier", 30, 300, 30);
        RobotJson robotString = new RobotJson(robot);
        System.out.println(robotString.toJson().toString(4));
    }

}