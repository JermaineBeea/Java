package ServerPackage;

import java.util.Set;
import java.util.HashSet;

public class AllClients {
    
    private static Set<Client> setClients = new HashSet<>();

    public static void addClient(Client client){
        setClients.add(client);
    }

    public static void removeClient(Client client){
        setClients.remove(client);
    }
}
