package ClientPackage;

import static UtilityModules.PrintMethods.delayPrint;
import static UtilityModules.PrintMethods.delayPrintWipe;

import java.net.Socket;

public class ClientGameHandler {
    
    private Socket serverSocket;

    public ClientGameHandler(Socket serversocket){
        this.serverSocket = serversocket;
    }

    public void runGame(){
        delayPrintWipe("Welcome to the Robot Game!");
        delayPrint("Lets begin the game!");

        // Retrive Robot name.
        delayPrintWipe("Give your Robot a name: ");
    }
}
