package Game.ServerPackage.RobotModules;

import java.util.*;

public class RobotTypes {

    private static Map<Integer, Robot> availableRobots = new HashMap<>();
    {
        availableRobots.put(1, new Robot("Raptor99", 25));
        availableRobots.put(2, new Robot("Jaguar77", 15));
        availableRobots.put(3, new Robot("Blitzer", 33));
    }

    public static Map<Integer, Robot> getAvailableRobots(){
        return availableRobots;
    }

}