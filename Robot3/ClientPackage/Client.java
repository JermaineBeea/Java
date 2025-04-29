package ClientPackage;

import java.net.Socket;

import RobotPackage.Robot;

public class Client {
       
    private String name;
    private int Identification;
    private Socket clientsocket;
    private Robot robot;

    public Client(int clientID, String name, Socket clientsocket, Robot robot){
        this.Identification = clientID;
        this.name = name;
        this.clientsocket = clientsocket;
        this.robot = robot;
    }
}
