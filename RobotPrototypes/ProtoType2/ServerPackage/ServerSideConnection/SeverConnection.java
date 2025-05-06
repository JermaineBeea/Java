package ServerPackage.ServerSideConnection;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;

public class SeverConnection {
    private static final int PORT = 1700;

    public void runConnection(){
        try(ServerSocket serverconnection = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){

        }catch(IOException e){
            System.out.println("Error establlishing connection: " + e.getMessage());
        }
    }
    
}
