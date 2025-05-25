package za.co.wethinkcode.robots.Server;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import java.util.logging.Logger;
import za.co.wethinkcode.robots.Utility.LogConfiguration;


public class ClientRegistry {

    private static Map<Integer, Client> setClients = Collections.synchronizedMap(new HashMap<>());

    public static void addClient(int clientId, Client client){
        setClients.put(clientId, client);
    }

    public static void removeClient(int clientId){
        setClients.remove(clientId);
    }

    public static Client getClient(int clientId){
        return setClients.getOrDefault(clientId, null);
    }

    public static Map<Integer, Client> getAllClients(){
        synchronized(setClients){
            return new HashMap<>(setClients);
        }
    }

    public static boolean confirmClientDetails(int clientId, String name){
        Client client = setClients.get(clientId);
        if(client == null){
            return false;
        }
        return client.getID() == clientId && client.getName().equals(name);
    }
}
