package za.co.wethinkcode.robots.Client;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import za.co.wethinkcode.robots.Utility.Encryption;
import za.co.wethinkcode.robots.Utility.LogConfiguration;

/**
 * Handles the client-side encrypted handshake protocol
 * Educational implementation demonstrating challenge-response authentication
 */
public class ClientSideHandshake {
    private static LogConfiguration logConfig = new LogConfiguration(ClientSideHandshake.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private Encryption encryption;
   
    /**
     * Creates a new client-side handshake object for the given server socket
     * @param serverSocket The socket connecting to the server
     */
    public ClientSideHandshake(Socket serverSocket) {
        try {
            this.fromServer = new DataInputStream(serverSocket.getInputStream());
            this.toServer = new DataOutputStream(serverSocket.getOutputStream());
            this.encryption = new Encryption();
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Connection error.", e);
            logConfig.printStack(e);
        }
    }

    /**
     * Custom exception for handshake failures
     */
    public static class HandshakeException extends Exception {
        public HandshakeException(String message) {
            super(message);
        }
    }

    /**
     * Performs the complete client-side handshake process:
     * 1. Receives encryption key from server (product of primes)
     * 2. Factors the key to extract prime factors
     * 3. Concatenates the prime factors to create response
     * 4. Sends the concatenated primes back to server
     * 5. Waits for server's success confirmation
     * 
     * @throws IOException If a connection error occurs
     * @throws HandshakeException If the handshake validation fails
     */
    public void performHandshake() throws IOException, HandshakeException {
        try {
            // Step 1: Receive encryption key from server
            int length = fromServer.readInt();
            byte[] keyBytes = new byte[length];
            fromServer.readFully(keyBytes);
            BigInteger encryptionKey = new BigInteger(keyBytes);
            logger.info("Received encryption key from server: " + encryptionKey);
            
            // Step 2: Prove we can factor the key by extracting prime factors
            // and concatenating them (this is our "decryption" process)
            BigInteger decryptedResponse = encryption.decrypt(encryptionKey);
            logger.info("Computed response from factorization: " + decryptedResponse);
            
            // Step 3: Send the response back to server
            byte[] responseBytes = decryptedResponse.toByteArray();
            toServer.writeInt(responseBytes.length);
            toServer.write(responseBytes);
            logger.info("Sent factorized response back to server");
            
            // Step 4: Wait for server's confirmation
            long serverResponse = fromServer.readLong();
            logger.info("Received server response code: " + serverResponse);
            
            // Assuming STATUS_OK is a known constant
            if (serverResponse != ClientCodes.STATUS_OK.code) {
                throw new HandshakeException("Server rejected handshake. Response code: " + serverResponse);
            }
            
            logger.info("Handshake successful - authenticated with server");
            
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Error during client-side handshake", e);
            logConfig.printStack(e);
            closeResources();
            throw e;
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Unexpected error during handshake", e);
            throw new HandshakeException("Handshake failed: " + e.getMessage());
        }
    }
    
    /**
     * Closes all resources associated with this handshake
     */
    private void closeResources() {
        try {
            if (fromServer != null) fromServer.close();
            if (toServer != null) toServer.close();
            // Note: We don't close the socket here as it might be used elsewhere
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing client handshake resources", e);
        }
    }
}