package ChatApp;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Holds all instances of clients connected to server.
 * Thread-safe implementation for handling multiple clients.
 */
public class AllClients {
    // Using synchronized set for thread safety
    private static final Set<Client> setClients = Collections.synchronizedSet(new HashSet<>());

    /**
     * @return Returns set of all clients.
     */
    public static Set<Client> getClients() {
        return setClients;
    }

    /**
     * @return The number of clients connected to the Server.
     */
    public static int getCountClients() {
        return setClients.size();
    }

    /**
     * Add a client to the set of clients.
     * @param client The instance of Client class.
     */
    public static void addClient(Client client) {
        setClients.add(client);
    }
    
    /**
     * Remove a client from the set of clients.
     * @param client The instance of Client class to be removed.
     */
    public static void removeClient(Client client) {
        setClients.remove(client);
    }
    
    /**
     * Find a client by their ID.
     * @param clientId The ID of the client to find.
     * @return The client with the specified ID, or null if not found.
     */
    public static Client findClientById(int clientId) {
        for (Client client : setClients) {
            if (client.getClientID() == clientId) {
                return client;
            }
        }
        return null;
    }
}