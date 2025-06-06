package ProtoType1.ServerPackage.ServerSideModules;

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Provides utility functions for server-side operations.
 */
public class ServerUtility {

    /**
     * Receive integer to test connection to Socket.
     * @param socket The socket being tested
     * @return True if connection is active, False otherwise
     */
    public static boolean recieveHandshake(Socket socket) {
        if (socket == null || socket.isClosed()) {
            return false;
        }
        
        DataInputStream fromSocket = null;
        try {
            fromSocket = new DataInputStream(socket.getInputStream());
            fromSocket.readInt();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            // Don't close the stream as it would close the socket
        }
    }
    
    /**
     * Determines the IP address of the local machine.
     * First attempts to connect to Google DNS to get local IP,
     * falls back to localhost if that fails.
     * 
     * @return The IP address string of the server
     */
    public static String getServerIP() {
        Socket tempSocket = null;
        try {
            tempSocket = new Socket("8.8.8.8", 100);
            return tempSocket.getLocalAddress().getHostAddress();
        } catch (IOException e) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
                System.out.println("Unable to find Server IP: " + ex.getMessage());
                System.out.println("Switched to 'local host'");
                ex.printStackTrace();
                return "localhost";
            }
        } finally {
            if (tempSocket != null && !tempSocket.isClosed()) {
                try {
                    tempSocket.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
        }
    }
}