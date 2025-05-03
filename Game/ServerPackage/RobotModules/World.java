package Game.ServerPackage.RobotModules;

import java.util.Set;
import java.util.HashSet;

public class World {
    
    private static Set<Robot> setRobots = new HashSet<>();
    // private static double[] fuelingStation = new double[]{2, 4};

    public static Set<Robot> getAllPlayers(){
        return setRobots;
    }
}