package ChatApp;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

@SuppressWarnings("unused")
/**
 * Test class for simulating clients and verifying client tracking functionality.
 * This class provides a controlled environment to test the AllClients and Client classes
 * without requiring actual socket connections.
 */
public class TestClientManager {
    
    public static void main(String[] args) {
        System.out.println("===== Client Manager Test =====");
        
        // Clear any existing clients to start fresh
        AllClients.getClients().clear();
        
        // Create some test clients with mock sockets
        try {
            // Create mock sockets (these won't actually connect to anything)
            Socket mockSocket1 = createMockSocket("192.168.1.101");
            Socket mockSocket2 = createMockSocket("192.168.1.102");
            Socket mockSocket3 = createMockSocket("192.168.1.103");
            
            // Create client instances
            System.out.println("Creating test clients...");
            Client client1 = new Client(1, mockSocket1, "Alice");
            Client client2 = new Client(2, mockSocket2, "Bob");
            Client client3 = new Client(3, mockSocket3, "Charlie");
            
            // Display the total number of clients
            System.out.println("Total clients: " + AllClients.getClientCount());
            
            // Display details of all clients
            System.out.println("\n===== Client Details =====");
            for (Client client : AllClients.getClients()) {
                System.out.println(
                    "Client ID: " + client.getID() +
                    "\nClient Name: " + client.getname() +
                    "\nClient Address: " + client.getSocket().getInetAddress().getHostAddress() +
                    "\n-----------------------------"
                );
            }
            
            // Test client lookup (optional extension)
            System.out.println("\nLooking up client with ID 2: " + findClientById(2).getname());
            
        } catch (IOException e) {
            System.out.println("Error in test: " + e.getMessage());
        }
    }
    
    /**
     * Creates a mock socket with a specific IP address for testing
     */
    private static Socket createMockSocket(String ipAddress) throws IOException {
        // Create a socket that isn't really connected but has the specified IP
        Socket mockSocket = new Socket();
        mockSocket.bind(null); // Bind to any available port
        
        // Use reflection to set the inet address field for test purposes
        try {
            java.lang.reflect.Field remoteAddress = Socket.class.getDeclaredField("impl");
            remoteAddress.setAccessible(true);
            Object impl = remoteAddress.get(mockSocket);
            
            java.lang.reflect.Field address = impl.getClass().getDeclaredField("address");
            address.setAccessible(true);
            address.set(impl, InetAddress.getByName(ipAddress));
            
        } catch (Exception e) {
            // If reflection fails, we'll just continue with default address
            System.out.println("Could not set mock IP: " + e.getMessage());
        }
        
        return mockSocket;
    }
    
    /**
     * Helper method to find a client by ID
     */
    private static Client findClientById(int id) {
        for (Client client : AllClients.getClients()) {
            if (client.getID() == id) {
                return client;
            }
        }
        return null;
    }
}