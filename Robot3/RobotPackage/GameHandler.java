package RobotPackage;

import static UtilityModules.PrintMethods.*;
import ServerPackage.Client;


public class GameHandler {

    private Client client;
    private String clientname;
    
    public GameHandler(Client client){
        this.client = client;
        this.clientname = client.getName();
    }

    public void runGame(){
      delayPrintWipe(clientname + ", has started the game!");
      
    } 
}
