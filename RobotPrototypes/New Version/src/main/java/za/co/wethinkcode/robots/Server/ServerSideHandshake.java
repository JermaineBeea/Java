package za.co.wethinkcode.robots.Server;

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
 * Handles the server-side encrypted handshake protocol
 * Educational implementation demonstrating basic challenge-response concepts
 */
public class ServerSideHandshake {
    private static LogConfiguration logConfig = new LogConfiguration(ServerSideHandshake.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private DataInputStream fromClient;
    private DataOutputStream toClient;
    private Encryption encryption;
   
    /**
     * Creates a new server-side handshake object for the given client socket
     * @param clientSocket The socket connecting to the client
     */
    public ServerSideHandshake(Socket clientSocket) {
        try {
            this.fromClient = new DataInputStream(clientSocket.getInputStream());
            this.toClient = new DataOutputStream(clientSocket.getOutputStream());
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
     * Performs the complete server-side handshake process:
     * 1. Sends encryption key to client (product of primes)
     * 2. Client should use this to verify it can compute the same concatenated primes
     * 3. Receives client's computed concatenated primes
     * 4. Validates the response matches expected value
     * 
     * @throws IOException If a connection error occurs
     * @throws HandshakeException If the handshake validation fails
     */
    public void performHandshake() throws IOException, HandshakeException {
        try {
            // Step 1: Send the encryption key
            BigInteger encryptionKey = encryption.encrypt();
            byte[] keyBytes = encryptionKey.toByteArray();
            toClient.writeInt(keyBytes.length);
            toClient.write(keyBytes);
            logger.info("Sent encryption key: " + encryptionKey);
    
            // Step 2: Receive client's proof that they can factor the key
            int length = fromClient.readInt();
            byte[] proofBytes = new byte[length];
            fromClient.readFully(proofBytes);
            BigInteger clientProof = new BigInteger(proofBytes);
            logger.info("Received client proof: " + clientProof);
    
            // Step 3: Verify the client reconstructed the concatenated primes
            BigInteger reconstructedChallenge = encryption.decrypt(encryptionKey);
    
            if (!clientProof.equals(reconstructedChallenge)) {
                throw new HandshakeException("Client failed to prove knowledge of factorization");
            }
    
            // Step 4: Send confirmation
            toClient.writeLong(ServerCodes.STATUS_OK.code);
            logger.info("Handshake successful");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during server-side handshake", e);
            logConfig.printStack(e);
            closeResources();
            throw e;
        }
    }    
    
    /**
     * Gets the encryption instance used by this handshake
     * @return The encryption instance
     */
    public Encryption getEncryption() {
        return encryption;
    }
    
    /**
     * Closes all resources associated with this handshake
     */
    private void closeResources() {
        try {
            if (fromClient != null) fromClient.close();
            if (toClient != null) toClient.close();
            // Note: We don't close the socket here as it might be used elsewhere
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing server handshake resources", e);
        }
    }
}