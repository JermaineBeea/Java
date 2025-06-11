package za.co.wethinkcode.robots.Server;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;


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
           }finally {

               ThreadRegistry.closeThread(this);
            logger.log(Level.SEVERE, e.getMessage());
            logConfig.printStack(e);
           }
        };
    }


    public void startThread(){
        if(thread != null && thread.isAlive()){
            return;
        }

        threadRunning.set(true);


        thread = new Thread(wrapperFunction);
        thread.start();
    }



    public void endThreadRun() {
        threadRunning.set(false);
        if (thread != null && thread.isAlive()) {
            thread.interrupt();           //interrupt execution
        }
    }

    public void closeThread() {
        endThreadRun();                  //signal stop
        ThreadRegistry.closeThread(this);
    }

    public boolean isRunning() {
        return threadRunning.get();
    }

    public Thread getThread() {
        return thread;
    }


}
=======
}
