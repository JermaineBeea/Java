package Game2.ServerPackage.ServerSideModules;

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;

/**
 * Provides utility functions for server-side operations.
 */
public class UtilityFunctions {
    
    /**
     * Determines the IP address of the local machine.
     * First attempts to connect to Google DNS to get local IP,
     * falls back to localhost if that fails.
     * 
     * @return The IP address string of the server
     */
    public static String getServerIP(){
        try(Socket tempSocket = new Socket("8.8.8.8", 100)){
            return tempSocket.getLocalAddress().getHostAddress();
        }catch(IOException e){
            try{
                return InetAddress.getLocalHost().getHostAddress();
            }catch(UnknownHostException ex){
                System.out.println("Unable to find Server IP: " + ex.getMessage());
                System.out.println("Switched to 'local host'");
                ex.printStackTrace();
                return "localhost";
            }
        }
    }
}