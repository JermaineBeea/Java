package Game.ServerPackage.RobotModules;

import java.util.*;

public class RobotTypes {

    public static void main(String[] args) {
        System.out.println(getStrRobots());
    }

    private static Map<Integer, Robot> availableRobots = new HashMap<>();
    
    // Static initializer block - will run when the class is loaded
    static {
        availableRobots.put(1, new Robot("Raptor99", 25));
        availableRobots.put(2, new Robot("Jaguar77", 15));
        availableRobots.put(3, new Robot("Blitzer", 33));
    }

    public static Map<Integer, Robot> getAvailableRobots(){
        return availableRobots;
    }

    public static String getStrRobots(){
        String string = "";
        int n = 0;
        for(Map.Entry <Integer, Robot> entry : availableRobots.entrySet()){
            int index = (int) entry.getKey();
            String name = (String) entry.getValue().name;
            double rateFuelUsage = (double) entry.getValue().rateFuelUsage;
            String space = (n == 0) ? "" : "\n";
            string += space + index + ". " + name + ": Fuel Usage (" + rateFuelUsage + ")" + " per 1 distance";
            n++;
        }

        return string;
    }

    //
    /* 
    public static String getStrRobots(){
        StringBuilder sb = new StringBuilder();
        sb.append("Available Robots:\n");
        
        for (Map.Entry<Integer, Robot> entry : availableRobots.entrySet()) {
            int robotNumber = entry.getKey();
            Robot robot = entry.getValue();
            sb.append(String.format("Robot #%d: %s (Fuel Usage: %d)\n", 
                     robotNumber, robot.name, robot.rateFuelUsage));
        }
        return sb.toString();
    }
    */
}