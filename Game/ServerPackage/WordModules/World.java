package Game.ServerPackage.WordModules;

import java.util.Set;

import Game.ServerPackage.RobotModules.Robot;

import java.util.HashSet;

public class World {
    
    private static Set<Robot> setRobots = new HashSet<>();

    public static Set<Robot> getAllPlayers(){
        return setRobots;
    }
}