package Game.ServerPackage.ServerSideModules;

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;

/**
 * Has Functions that are used primaraly in the ServerSide class.
 */
public class UtilityFunctions {
    
    /**
     * Finds the IP Address the server is connected to.
     * 
     * @return The IP Address the server is connected to. Default is "localhost"
     * @throws IOEXception
     * @throws UnknownHostException
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
