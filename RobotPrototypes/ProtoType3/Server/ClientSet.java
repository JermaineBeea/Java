package Server;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import Client.ClientConnection;
import Utility.LogConfiguration;

import java.util.HashSet;

public class ClientSet {

    private static Set<Client> setClients = Collections.synchronizedSet(new HashSet<>());

    public void addClient(Client client){
        setClients.add(client);
    }

    public void removeClient(Client client){
        setClients.remove(client);
    }

    public Set<Client> getAllClients(){
        synchronized(setClients){
            return new HashSet<>(setClients);
        }
    }
}
