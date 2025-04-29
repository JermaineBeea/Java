package RobotPackage;
import java.util.Set;
import java.util.HashSet;

public class World {

    private static Set<Robot> setRobots = new HashSet<>();

    public static void addRobot(Robot robot){
        setRobots.add(robot);
    }
    
}
