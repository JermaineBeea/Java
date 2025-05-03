package Game.ServerPackage.ServerSideModules;

import java.io.*;
import java.net.*;

import static Game.ServerPackage.ServerSideModules.UtilityFunctions.*;
import  Game.ServerPackage.RobotModules.Robot;
import  static Game.ServerPackage.RobotModules.RobotTypes.*;

public class ServerSide {
    private static String IP_ADDRESS = "localhost";
    private static int PORT = 3000;

    public static void main(String[] args) {
        try(ServerSocket serversocket = new ServerSocket(PORT)){
            Socket client = serversocket.accept();
            ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream toClient = new ObjectOutputStream(client.getOutputStream());

            Robot clientRobot = getAvailableRobots().get(1);
            
            toClient.writeObject(clientRobot);
        }catch(IOException e){

        }
    }
}
