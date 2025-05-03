import java.net.Socket;

/**
 * Represents a connected client in the chat application.
 * Stores the client's unique ID, name, and socket connection.
 */
public class Client {
    private final int id;
    private String name;
    private Socket socket;

    /**
     * Creates a new client with the specified ID, name, and socket.
     *
     * @param clientId The unique identifier for the client
     * @param clientName The display name of the client
     * @param clientSocket The socket connection to the client
     */
    public Client(int clientId, String clientName, Socket clientSocket) {
        this.id = clientId;
        this.name = clientName;
        this.socket = clientSocket;
    }

    /**
     * @return The client's unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return The client's display name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The client's socket connection
     */
    public Socket getSocket() {
        return socket;
    }
}