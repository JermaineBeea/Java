package ServerPackage;

public class Game {
    
    private boolean isRunning;
    private boolean isConnected;
    private final int EXIT_CODE = 500;
    private ServerConnection connection;

    public Game(ServerConnection instance){
        this.connection = instance;
        this.isRunning = false;
        this.isConnected = instance.isConnected;
    }

    public void run(){

    }
}
