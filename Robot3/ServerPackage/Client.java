package ServerPackage;

import java.net.Socket;
import java.util.Set;
import java.util.HashSet;

import RobotPackage.Robot;


public class Client {
       
    private String name;
    private int Identification;
    private Socket clientsocket;
    private Robot robot;

    private Set<Robot> clientRobots = new HashSet<>();

    public Client(int clientID, String name, Socket clientsocket){
        this.Identification = clientID;
        this.name = name;
        this.clientsocket = clientsocket;
    }

    public void addRobot(Robot robot){
        clientRobots.add(robot);
    }
    
    public String getName(){
        return name; 
    }
    
}
