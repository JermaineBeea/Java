package ClientPackage;

import java.util.Set;
import java.util.HashSet;

public class AllClients {
    
    private static Set<Client> setClients = new HashSet<>();

    private static void addClient(Client client){
        setClients.add(client);
    }

    private static void removeClient(Client client){
        setClients.remove(client);
    }
}
