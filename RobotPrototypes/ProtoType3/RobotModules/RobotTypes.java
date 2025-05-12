package RobotModules;
import java.util.Set;
import java.util.HashSet;

public class RobotTypes {
    
    private static final Set<Robot> robotTypes = Set.of(
        new Robot("GermanBullTerrier", 30, 300, 30),
        new Robot("ItalianKing", 250, 35, 5),
        new Robot("FrenchPoodle", 50, 100, 10)
    );

    public  Set<Robot> getRobotTypes(){
        return new HashSet<>(robotTypes);
    }
}
