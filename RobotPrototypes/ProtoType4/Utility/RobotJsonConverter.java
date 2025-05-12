package RobotModules;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Utility class to convert Robot objects to JSON format for client-server communication
 */
public class RobotJsonConverter {
    
    /**
     * Converts a Robot object to a JSONObject containing all relevant properties
     * 
     * @param robot The Robot object to convert
     * @return JSONObject with robot properties
     */
    public static JSONObject toJson(Robot robot) {
        if (robot == null) {
            return new JSONObject();
        }
        
        JSONObject robotJson = new JSONObject();
        
        // Add all robot properties
        robotJson.put("name", robot.getName());
        robotJson.put("buildType", robot.getBuild());
        robotJson.put("durability", robot.getDurability());
        robotJson.put("maxShots", robot.getMaxShots());
        robotJson.put("fuelAmount", robot.getFuelAmount());
        robotJson.put("fuelUsageRate", robot.getRate());
        
        // Add position data
        Position position = robot.getPosition();
        Map<String, Object> positionMap = new HashMap<>();
        positionMap.put("x", position.getX());
        positionMap.put("y", position.getY());
        positionMap.put("direction", position.getDirection().toString());
        
        robotJson.put("position", positionMap);
        
        return robotJson;
    }
}