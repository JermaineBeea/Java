package Game.ServerPackage.RobotModules;

import java.util.Set;
import java.util.HashSet;

public class World {
    
    private static Set<Robot> setRobots = new HashSet<>();

    public static Set<Robot> getAllPlayers(){
        return setRobots;
    }
}