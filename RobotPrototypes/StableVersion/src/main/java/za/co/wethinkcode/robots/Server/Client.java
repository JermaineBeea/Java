package za.co.wethinkcode.robots.Server;

import java.net.Socket;
import java.util.Objects;

import za.co.wethinkcode.robots.RobotModules.Robot;

public class Client {

    private int ID;
    private String name;
    private Robot robot;
    private Socket clientSocket;
    private Thread thread;
    
    public Client(String nameArg, int IDArg, Socket socketArg, Thread threadArg){
        this.name = nameArg;
        this.ID = IDArg;
        this.clientSocket = socketArg;
        this.thread = threadArg;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;

        Client otherInstance = (Client) object;
        return otherInstance.ID == ID && Objects.equals(otherInstance.name, name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ID, name);
    }

    @Override
    public String toString(){
        return "Client " + ID + ": name[" + name + "]";
    }

}
