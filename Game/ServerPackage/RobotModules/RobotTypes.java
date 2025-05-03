package Game.ServerPackage.RobotModules;

import java.util.Set;
import java.util.HashSet;

public class RobotTypes {

    private Set<Robot> availableRobots = new HashSet<>();
    {
        availableRobots.add(new Robot("Raptor99", 25));
        availableRobots.add(new Robot("Jaguar77", 15));
        availableRobots.add(new Robot("Blitzer", 33));
    }

}