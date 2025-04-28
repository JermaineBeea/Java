package ChatApp;

import java.util.Set;

public class MainRun{
    
    public static void main(String[] args){
        // Get currents clients connected to Server.
        Set<Client> setClients = AllClients.getClients();

        for(Client client : setClients){
            System.out.println(
                "Client Id: " + client.getID() +
                "\nClient name: " + client.getname() +
                "\nClient Address: "+ client.getSocket().getRemoteSocketAddress()
            );
        }
    }
}