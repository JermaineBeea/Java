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

import Utility.LogModule;

public class ClientSession {
    private LogModule logMod = new LogModule(ClientSession.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();

    private static Socket serverSocket;

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
        // Begin onborading by recieving name from client.
        System.out.print("Please enter your name: ");
        String clientName = consoleIn.nextLine();

        // Send name to server.
        dataToServer.writeUTF(clientName);

        }catch(IOException e){
            logger.log(Level.SEVERE , "Client Session Error", e);
            logMod.printStackTrace(e);
        }
    }
}
