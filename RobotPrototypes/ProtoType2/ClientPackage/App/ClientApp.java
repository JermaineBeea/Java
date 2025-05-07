package ClientPackage.App;

public class ClientApp {
    public static void main(String[] args) {
        ClientConnection client = new ClientConnection();
        client.runConnection();
        client.runGame();
    }
}
