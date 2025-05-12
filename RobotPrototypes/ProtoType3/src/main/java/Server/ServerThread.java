package Server;
import java.net.Socket;
import java.net.ServerSocket;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.LogConfiguration;

public class ServerThread {
    private static LogConfiguration logConfig = new LogConfiguration(ServerThread.class.getName());
    private static Logger logger = logConfig.getLogger();


    private Thread thread;
    private AtomicBoolean threadRunning = new AtomicBoolean(false);
    private Runnable wrapperFunction;

    {logger.setLevel(Level.ALL);}

    public ServerThread(int clientId, Socket clientSocket, ServerSocket serverSocket){
        wrapperFunction = ()-> {
           try{
                if(threadRunning.get() && !clientSocket.isClosed() && !serverSocket.isClosed()){
                    // Begin onboarding of new clients.
                    new ServerSession(clientId, clientSocket, thread);
                }else{
                    throw new Exception("Error creating client thread");
                }
           }catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            logConfig.printStack(e);
           }
        };
    }


    public void startThread(){
        // Check if thread has not been started prior.
        if(thread != null && thread.isAlive()){
            return;
        }

        // Run thread.
        threadRunning.set(true);

        // Create thread and run thread.
        thread = new Thread(wrapperFunction);
        thread.start();
    }
}
