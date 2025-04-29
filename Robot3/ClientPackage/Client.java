package ClientPackage;

import java.net.Socket;

import RobotPackage.Robot;

public class Client {
       
    private String name;
    private int Identification;
    private Socket cleintsocket;
    private Robot robot;

    public Client(int clientID, String name, Socket clienSocket){
        this.Identification = clientID;
        this.name = name;
        this.cleintsocket = clienSocket;
    }
}
