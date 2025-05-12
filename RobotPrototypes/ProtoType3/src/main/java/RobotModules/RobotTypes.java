package RobotModules;
import java.util.Set;
import java.util.HashSet;
import org.json.JSONObject;

import Utility.LogConfiguration;
import Utility.RobotJson;
import java.util.logging.Logger;

public class RobotTypes {

    private static LogConfiguration logConfig = new LogConfiguration(RobotTypes.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private static final Set<Robot> robotTypes = Set.of(
        new Robot("GermanBullTerrier", 30, 300, 30),
        new Robot("ItalianKing", 250, 35, 5),
        new Robot("FrenchPoodle", 50, 100, 10)
    );

    public Set<Robot> getRobotTypes(){
        return new HashSet<>(robotTypes);
    }

    public static JSONObject getRobotTypesAsJson() {
        JSONObject json = new JSONObject();
        for (Robot robot : robotTypes) {
            JSONObject robotJson = new RobotJson(robot).toJson();
            robotJson.remove("type"); // Remove the "type" field
            json.put(robot.getBuildType(), robotJson);
        }
        return json;
    }

    public static void main(String[] args) {
    //Dispaly robot types for client
        System.out.println("\nRobot Types:");
        System.out.println(RobotTypes.getRobotTypesAsJson().toString(2));
    }
}
