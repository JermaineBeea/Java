package ServerPackage;

import ServerPackage.RobotModules.*;

public class ServerUtility {
    
    public static String getStatusString(Robot instance) {
        StringBuilder status = new StringBuilder();
        Position position = instance.getPosition();
        status.append(position.getX()).append(",")
              .append(position.getY()).append(",")
              .append(position.getDirection().getIndex()).append(",")
              .append(instance.getFuelAmount());
        return status.toString();
    }

}
