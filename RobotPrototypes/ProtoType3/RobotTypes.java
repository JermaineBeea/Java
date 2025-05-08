import java.util.Set;
import java.util.HashSet;

public class RobotTypes {
    
    private static final Set<Robot> robotTypes = Set.of(
        new Robot("GermanBullTerrier", 300, 30, 3),
        new Robot("ItalianKing", 250, 35, 5)
    );

    public  Set<Robot> getRobotTypes(){
        return new HashSet<>(robotTypes);
    }
}
