import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    private final Logging logging = new Logging(ServerConnection.class);
    private final Logger logger = logging.getLogger();

    // Public resorces.
    public ServerSocket serverSocket;
    public Socket clientSocket;
    public boolean serverRunning = false;
    public DataInputStream dataFromClient;
    public  DataOutputStream dataToClient;

    {
        logging.enableLogging(true);
        logging.enablePrintStack(true);
    }

    public ServerConnection(int serverPort, int backlog) throws IOException{
        try{
            this.serverSocket = new ServerSocket(serverPort, backlog, InetAddress.getByName("0.0.0.0"));
            serverRunning = true;
            connectClients();
        }catch(IOException e){
            closeConnection();
            System.out.println("Connection Error: " + e.getMessage());
        }
    }
    
    private void connectClients(){
        int clientCount = 0;
        try{
            while (serverSocket.isBound() && serverRunning){
                clientSocket = serverSocket.accept();
                clientCount++;
                new ClientThread(this);
            }
        }catch(IOException e){
            logger.log(Level.SEVERE, "Error connection client " + clientCount, e);
            logging.printStackTrace(e);
        }
    }

    public void closeConnection() throws IOException{
        try{
            if(serverSocket != null) serverSocket.close();
            if(dataFromClient != null) dataFromClient.close();
            if(dataToClient != null) dataToClient.close();
        }catch(IOException e){
            logger.log(Level.SEVERE,"Error closing connections", e);
            logging.printStackTrace(e);
        }
    }

}
