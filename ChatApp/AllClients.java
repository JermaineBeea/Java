package ChatApp;

import java.util.Set;
import java.util.HashSet;

/**
* Class of all clients.
*/
public class AllClients{

    private static Set<Client> setClients = new HashSet<>();

    public static Set<Client> getClients(){
        return setClients;
    }

    public  static int getClientCount(){
        return setClients.size();
    }

    public static void addClient(Client client){
        setClients.add(client);
    }
}