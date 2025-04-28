package ChatApp;

import java.net.Socket;

/**
* Has all the attributes of the client connected.
* @param name The name of the client
* @param clientID The Identification number of the client.
* @param clientsocket The socket of the client.
*/
public class Client extends AllClients{

    private String name;
    private int clientID;
    private Socket clientsocket;

    /**
    * Constructor that assignes the client attributes, ID, socket and name.
    */
    Client(int clientID, Socket clientsocket, String name){
        this.clientID = clientID;
        this.clientsocket = clientsocket;
        this.name = name;

        // Add client instance to set of clients.
        addClient(this);
    }

    public Socket getSocket(){
        return clientsocket;
    }

    public int getID(){
        return clientID;
    }

    public String getname(){
        return name;
    }
}