import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/**
 * Manages all connected clients in the chat application.
 * Handles adding, removing, and retrieving client information.
 */
public class ClientManager {
    private static final Map<Integer, Client> clients = new HashMap<>();

    /**
     * Adds a client to the manager.
     *
     * @param client The client to add
     */
    public static void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    /**
     * Removes a client from the manager.
     *
     * @param client The client to remove
     */
    public static void removeClient(Client client) {
        clients.remove(client.getId());
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param clientId The ID of the client to retrieve
     * @return The client with the specified ID, or null if not found
     */
    public static Client getClient(int clientId) {
        return clients.get(clientId);
    }

    /**
     * Checks if a client with the specified ID exists.
     *
     * @param clientId The ID to check
     * @return true if a client with the specified ID exists, false otherwise
     */
    public static boolean clientExists(int clientId) {
        return clients.containsKey(clientId);
    }
    
    /**
     * @return A collection of all connected clients
     */
    public static Collection<Client> getAllClients() {
        return clients.values();
    }
}