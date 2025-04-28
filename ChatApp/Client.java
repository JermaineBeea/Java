package ChatApp;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Class of each client connected to Server.
 */
public class Client {
    private final int clientId;
    private final String name;
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isActive;

    /**
     * Constructor to initialize fields - name, clientId and clientSocket.
     * @param clientId Unique Id of each client
     * @param name Client name.
     * @param clientSocket socket of connection to server.
     */
    public Client(int clientId, String name, Socket clientSocket) {
        this.clientId = clientId;
        this.name = name;
        this.clientSocket = clientSocket;
        this.isActive = true;
        
        try {
            this.inputStream = new DataInputStream(clientSocket.getInputStream());
            this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error setting up client streams: " + e.getMessage());
        }
    }

    /** 
     * Retrieval method.
     * @return The ID of the client.
     */
    public int getClientID() {
        return clientId;
    }

    /**
     * Retrieval method.
     * @return Name of client
     */
    public String getClientName() {
        return name;
    }

    /**
     * Retrieval method.
     * @return Client's connection-socket to server.
     */
    public Socket getClientSocket() {
        return clientSocket;
    }
    
    /**
     * Get the input stream for this client.
     * @return The DataInputStream for reading from client.
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }
    
    /**
     * Get the output stream for this client.
     * @return The DataOutputStream for writing to client.
     */
    public DataOutputStream getOutputStream() {
        return outputStream;
    }
    
    /**
     * Check if client is active.
     * @return true if client is connected and active.
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Set client's active status.
     * @param active New active status.
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    /**
     * Send a message to this client.
     * @param message The message to send.
     * @return true if message was sent successfully, false otherwise.
     */
    public boolean sendMessage(String message) {
        if (!isActive || clientSocket.isClosed()) {
            return false;
        }
        
        try {
            outputStream.writeUTF(message);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            System.err.println("Error sending message to " + name + ": " + e.getMessage());
            setActive(false);
            return false;
        }
    }
    
    /**
     * Close all client resources.
     */
    public void close() {
        try {
            isActive = false;
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing client resources: " + e.getMessage());
        }
    }
}