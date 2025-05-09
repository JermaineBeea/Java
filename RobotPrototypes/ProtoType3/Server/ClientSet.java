package Server;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import Client.ClientConnection;
import Utility.LogModule;

import java.util.HashSet;

public class ClientSet {
    private LogModule logMod = new LogModule(ClientSet.class).launchLog(true, true);
    private Logger logger = logMod.getLogger();

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
