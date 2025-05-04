package Game.ServerPackage.RobotModules;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the available robot types in the game.
 * This class provides a thread-safe repository of different robot types
 * that clients can choose from.
 */
public class RobotTypes {

    /**
     * Test method to display available robots
     */
    public static void main(String[] args) {
        System.out.println(getStrRobots());
    }

    /**
     * Thread-safe map of available robot types
     */
    private static final Map<Integer, Robot> availableRobots = new ConcurrentHashMap<>();
    
    // Static initializer block - will run when the class is loaded
    static {
        // Initialize with default robot types
        availableRobots.put(1, new Robot("Raptor99", 25));
        availableRobots.put(2, new Robot("Jaguar77", 15));
        availableRobots.put(3, new Robot("Blitzer", 33));
    }

    /**
     * Returns the map of available robots
     *
     * @return Map of robot IDs to Robot objects
     */
    public static Map<Integer, Robot> getAvailableRobots(){
        // Return a copy to prevent external modification
        return Collections.unmodifiableMap(availableRobots);
    }

    /**
     * Returns a formatted string listing all available robots with their details
     *
     * @return String containing formatted robot information
     */
    public static String getStrRobots(){
        StringBuilder sb = new StringBuilder("AVAILABLE ROBOTS\n");
        sb.append("=================\n");
        
        for(Map.Entry<Integer, Robot> entry : availableRobots.entrySet()){
            int index = entry.getKey();
            Robot robot = entry.getValue();
            
            sb.append(String.format("%d. %s: Fuel Usage (%.1f) per 1 distance\n", 
                     index, robot.name, robot.rateFuelUsage));
        }

        return sb.toString();
    }

    /**
     * Adds a new robot type to the available robots
     *
     * @param id The ID to assign to the robot
     * @param robot The Robot object to add
     * @return true if added successfully, false if ID already exists
     */
    public static boolean addRobotType(int id, Robot robot) {
        if (availableRobots.containsKey(id)) {
            return false;
        }
        availableRobots.put(id, robot);
        return true;
    }
    
    /**
     * Removes a robot type from the available robots
     *
     * @param id The ID of the robot to remove
     * @return The removed Robot object, or null if not found
     */
    public static Robot removeRobotType(int id) {
        return availableRobots.remove(id);
    }
}