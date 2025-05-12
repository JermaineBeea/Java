package Utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Server.ServerCodes;
import Client.ClientCodes;

/**
 * Handles the handshake protocol between server and client
 */
public class HandShake {
    private static LogConfiguration logConfig = new LogConfiguration(HandShake.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private DataInputStream fromPartner;
    private DataOutputStream toPartner;
   
    /**
     * Creates a new handshake object for the given socket
     * @param partnerSocket The socket connecting to the partner
     */
    public HandShake(Socket partnerSocket) {
        try {
            this.fromPartner = new DataInputStream(partnerSocket.getInputStream());
            this.toPartner = new DataOutputStream(partnerSocket.getOutputStream());
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
     * Server-side handshake: sends server handshake code and expects client code in response
     * @throws IOException If a connection error occurs
     * @throws HandshakeException If the handshake validation fails
     */
    public void sendHandshake() throws IOException, HandshakeException {
        try {
            logger.info("Sending handshake code: " + ServerCodes.HANDSHAKE.code);
            toPartner.writeInt(ServerCodes.HANDSHAKE.code);
            
            int receivedCode = fromPartner.readInt();
            logger.info("Received response code: " + receivedCode);
            
            if (receivedCode != ClientCodes.HANDSHAKE.code) {
                throw new HandshakeException("Invalid client handshake response. Expected: " + 
                                           ClientCodes.HANDSHAKE.code + ", Received: " + receivedCode);
            }
            
            logger.info("Handshake successful");
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Error establishing handshake", e);
            logConfig.printStack(e);
            closeResources();
            throw e;
        }
    }

    /**
     * Client-side handshake: expects server handshake code and sends client code in response
     * @throws IOException If a connection error occurs
     * @throws HandshakeException If the handshake validation fails
     */
    public void receiveHandshake() throws IOException, HandshakeException {
        try {
            int receivedCode = fromPartner.readInt();
            logger.info("Received server handshake code: " + receivedCode);
            
            if (receivedCode != ServerCodes.HANDSHAKE.code) {
                throw new HandshakeException("Invalid server handshake code. Expected: " + 
                                           ServerCodes.HANDSHAKE.code + ", Received: " + receivedCode);
            }
            
            logger.info("Sending client handshake code: " + ClientCodes.HANDSHAKE.code);
            toPartner.writeInt(ClientCodes.HANDSHAKE.code);
            logger.info("Handshake successful");
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Error establishing handshake", e);
            logConfig.printStack(e);
            closeResources();
            throw e;
        }
    }
    
    /**
     * Closes all resources associated with this handshake
     */
    private void closeResources() {
        try {
            if (fromPartner != null) fromPartner.close();
            if (toPartner != null) toPartner.close();
            // Note: We don't close the socket here as it might be used elsewhere
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing handshake resources", e);
        }
    }
}