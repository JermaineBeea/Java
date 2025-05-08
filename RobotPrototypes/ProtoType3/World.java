import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class World {
    
   private static final Set<Robot> setRobots = Collections.synchronizedSet(new HashSet<>());

   public Set<Robot> getsetRobots(){
        synchronized(setRobots){
            return new HashSet<>(setRobots);
        }
   }

   public void addRobot(Robot robot){
        setRobots.add(robot);
   }

   public void removeRobot(Robot robot){
        setRobots.remove(robot);
   }
}

/*
 import java.util.HashSet;
import java.util.Set;

public class World {

    // Instance-based collection - each World instance has its own set of robots
    private final Set<Robot> allRobots = new HashSet<>();

    // No need for synchronization or defensive copying if each client has their own World
    public Set<Robot> getAllRobots() {
        return new HashSet<>(allRobots); // Still using defensive copy as a best practice
    }

    public void addRobot(Robot robot) {
        allRobots.add(robot);
    }

    public void removeRobot(Robot robot) {
        allRobots.remove(robot);
    }
}
 */