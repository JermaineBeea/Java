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

    private void runSession() {
        try (
            DataInputStream datafromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream datatoClient = new DataOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream ObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        ) {
            for (int n = 0; n < ServerConstants.EXECUTION_ATTEMPTS.num; n++) {
                logger.info("Waiting for client to send name...");
                
                String clientName = datafromClient.readUTF();
                logger.info("Received client name: " + clientName);
                
                // Create instance of client with name and add to client list
                Client client = new Client(clientName, clientId, clientSocket, serverThread);
                ClientSet.addClient(clientId, client);
                
                // Confirm client exists
                boolean clientExists = ClientSet.confirmClientDetails(clientId, clientName);
                
                // Send exactly one status code to the client
                int status;
                if (n + 1 == ServerConstants.EXECUTION_ATTEMPTS.num && !clientExists) {
                    status = ServerCodes.STATUS_ERROR.code;
                } else {
                    status = clientExists ? ServerCodes.STATUS_OK.code : ServerCodes.STATUS_EXCEPTION.code;
                }
                
                datatoClient.writeInt(status);
                
                if (status == ServerCodes.STATUS_OK.code) {
                    logger.info("Client successfully added!");
                    break;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server Session Error", e);
            logConfig.printStack(e);
        }
    }
}
