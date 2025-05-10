package Server;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Utility.LogConfiguration;

public class ServerSession {
    private static LogConfiguration logConfig = new LogConfiguration(ServerSession.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private final int clientId;
    private final Socket clientSocket;
    private final Thread serverThread;
    
    public ServerSession(int clientIdArg, Socket clientSocketArg, Thread serverThreadArg){
        this.clientId = clientIdArg;
        this.clientSocket = clientSocketArg;
        this.serverThread = serverThreadArg;
        runSession();
    }

    private void runSession(){
        try(
            DataInputStream datafromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream datatoClient = new DataOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream ObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        ){
        for(int n = 0; n < ServerConstants.EXECUTION_ATTEMPTS.num; n++){
            // Begin onborading by recieving name from client.
            System.out.println();
            logger.info("Waiting for client to send name...\n");

            String clientName = datafromClient.readUTF();
            logger.info("Recieved client name: " + clientName);

            // Create instance of client with name and add to client list.
            Client client = new Client(clientName, clientId, clientSocket, serverThread);
            ClientSet.addClient(clientId, client);

            // Confirm client exists.
            boolean clientExits = ClientSet.confirmClientDetails(clientId, clientName);

            // Send status message to client.
            if(n + 1 == ServerConstants.EXECUTION_ATTEMPTS.num){
                datatoClient.writeInt(ServerCodes.STATUS_ERROR.code);
            }
            int status = (clientExits) ? ServerCodes.STATUS_OK.code:ServerCodes.STATUS_EXCEPTION.code;
            
        }


        }catch(IOException e){
            logger.log(Level.SEVERE , "Server Session Error", e);
            logConfig.printStack(e);
        }
    }
}
