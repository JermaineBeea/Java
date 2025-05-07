package ServerPackage.App;

public class ServerApp {
    
    public static void main(String[] args) {
        ServerConnection server = new ServerConnection();
        server.runConnection();
        server.runGame();
    }
}
