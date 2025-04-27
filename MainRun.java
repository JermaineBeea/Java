import java.net.*;

public class MainRun {
    public static void main(String[] args) {

        // Start server in a new thread
        new Thread(() -> Server.runClient()).start();

        try {
            // Give the server a little time to start up
            Thread.sleep(100);

            // Now start the client
            try (Socket server = new Socket("localhost", 9000)){
                System.out.println("Client connected to server!");
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }
}

class Server {
    public static void runClient() { 
        try (ServerSocket server = new ServerSocket(9000)) {
            Socket client = server.accept();
            System.out.println("Server accepted connection from: " + client.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
