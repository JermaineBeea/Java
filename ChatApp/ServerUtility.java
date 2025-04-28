package ChatApp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class ServerUtility{

    public static void delayPrint(int seconds, String string){
        try{
            Thread.sleep(seconds * 1000);
            System.out.println(string);
        }catch(InterruptedException e){
            System.out.println("Error delaying print: " + e.getMessage());
        }
    }

    public static String getServerIp(){
        // Retrieve non-local IP address.
        try(Socket tempsocket = new Socket("8.8.8.8", 1000)){
            return tempsocket.getLocalAddress().getHostAddress();
        }catch(IOException e){
        //Retrieve local IP address.
            try{
                return InetAddress.getLocalHost().getHostAddress();
            }catch(UnknownHostException ex){
                System.out.println("Cannot find IP address, switched to 'localhost");
                return "localhost";
            }
        }
    }
}