package ProtoType1.ServerPackage.RobotModules;

import java.util.Set;
import java.util.HashSet;

/**
 * Represents the game world that contains all robot players.
 * Maintains a collection of all robots in the game.
 */
public class World {
    
    /** Set of all robot instances in the game world */
    private static Set<ServerRobot> setRobots = new HashSet<>();

    /**
     * @return Set containing all robot players in the world
     */
    public static Set<ServerRobot> getAllPlayers(){
        return setRobots;
    }
}