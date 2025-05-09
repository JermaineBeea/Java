package Server;

import java.net.Socket;

import RobotModules.Robot;

public class Client {

    private String name;
    private int ID;
    private Robot robot;
    private Socket clientSocket;
    private Thread thread;
    
    public Client(String nameArg, int IDArg, Socket socketArg, Thread threadArg){
        this.name = nameArg;
        this.ID = IDArg;
        this.clientSocket = socketArg;
        this.thread = threadArg;
    }
}
