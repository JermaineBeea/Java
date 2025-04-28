package ChatApp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerUtility{

    public static void delayPrint(int seconds, String string){
        try{
            Thread.sleep(seconds * 1000);
            System.out.println(string);
        }catch(InterruptedException e){
            System.out.println("Error delaying print: " + e.getMessage());
        }
    }

    /**
     * Gets the Server IP
     * @return Either local or non-local address.
     */
    public static String getServerIP(){
        try(Socket tempsocket = new Socket("8.8.8.8", 100)){
            return tempsocket.getLocalAddress().getHostAddress();
        }catch(IOException e){
            try{
                return InetAddress.getLocalHost().getHostAddress();
            }catch(UnknownHostException ex){
                System.out.println("Cannot find ip address, switched to local-host");
                return "localhost";
            }
        }
    }
}