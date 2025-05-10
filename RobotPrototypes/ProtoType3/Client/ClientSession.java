package Client;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Utility.LogConfiguration;

public class ClientSession {
    private static LogConfiguration logConfig = new LogConfiguration(ClientSession.class.getName());
    private static Logger logger = logConfig.getLogger();
    private Socket serverSocket;
    
    public ClientSession(Socket serverSocketArg){
        this.serverSocket = serverSocketArg;
        runSession();
    }

    private void runSession(){
        try(
            Scanner consoleIn = new Scanner(System.in);
            DataOutputStream dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            DataInputStream dataFromServer = new DataInputStream(serverSocket.getInputStream());
            ObjectOutputStream ObjectdataToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream ObjectdataFromServer = new ObjectInputStream(serverSocket.getInputStream());
        ){
        
        System.out.print("Please enter your name: ");
        while(true){
            // Begin onborading by recieving name from client.
            String clientName = consoleIn.nextLine();

            // Send name to server.
            dataToServer.writeUTF(clientName);

            // Reciev status code from server.
            int serverCode = dataFromServer.readInt();

            // Valida server code.
            if(serverCode == ClientCodes.STATUS_OK.code){
                break;
            }else if(serverCode == ClientCodes.STATUS_EXCEPTION.code){
                System.out.println("\nServer Error!");
                System.out.print("\nPlease re-enter your name: ");
                continue;
            }else if (serverCode == ClientCodes.STATUS_ERROR.code){
                System.err.println("Server Error " + ClientCodes.STATUS_ERROR.code);
                System.err.println("Closing connection to Server..." + ClientCodes.STATUS_ERROR.code);
                logger.log(Level.SEVERE, "Error getting client name\n");
                logger.log(Level.SEVERE, "Closing connection to server!");
            }else{
                logger.log(Level.SEVERE, "Error: Unknown Status Code");
            }
        }
        }catch(IOException e){
            logger.log(Level.SEVERE , "Client Session Error", e);
            logConfig.printStack(e);
        }
    }
}
