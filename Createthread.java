import java.util.concurrent.atomic.AtomicBoolean;

public class Createthread{

    private Thread thread; // Default value for Thread type is null;
    private final AtomicBoolean threadrunning = new AtomicBoolean(false);
    private final Runnable runnablefunction;

    /**
    * Constructor to initialise the runnable function to the mainfunction passed to instance.
    * @param mainfunction Function executed in thread.
    */
    public Createthread(Runnable mainfunction){
        this.runnablefunction = mainfunction;
    }

    /**
    * Lambda runnable that encapsulates the runnablefunction, so that function runs if thread is running and has started.
    */
    Runnable wrapperfunction = ()-> {
        try{
            if(threadrunning.get()){ // .get() returns true if thread is running, else fasle.
                runnablefunction.run();
            }
        }catch(Exception e){
            System.err.println("Error in thread creation: " + e.getMessage());
            e.printStackTrace();
        }finally{
            threadrunning.set(false);
        }
    };

    /**
    * Starts the thread, then runs the mainfunction.
    */
    public void startThread(){
        try{
            // Check if thread has been started, but not terminated.
            if(thread != null && thread.isAlive()){
                return;
            }

            // If not started, run thread.
            threadrunning.set(true);

            // Initialise thread, and pass wrapperfunction to thread.
            thread = new Thread(wrapperfunction);
            thread.start();

        }catch(NullPointerException e){
            // If thread is null, continue with thread creation
            System.err.println("Thread is null (expected on first run): " + e.getMessage());
            
            // Set thread as running
            threadrunning.set(true);
            
            // Initialise thread, and pass wrapperfunction to thread.
            thread = new Thread(wrapperfunction);
            thread.start();
        }catch(Exception e){
            System.err.println("Unexpected error starting thread: " + e.getMessage());
            e.printStackTrace();
            threadrunning.set(false);
        }
    }

    /**
    * Stops thread run.
    */
    public void stopThread() throws NullPointerException{
        threadrunning.set(false);
        thread.interrupt();
    }

    /**
    * Validates if thread is running or not.
    */
    public Boolean threadrunning() throws NullPointerException{
        return threadrunning.get();
    }

    /**
    * Pauses the Main thread that new thread partitioned from, until new thread is complete.
    * @param timeMilli Pause time in milli seconds. E.g 1000 milli-seconds = 1 second.
    */
    public void delayMain(int timeMilli) throws NullPointerException{
        try{
            thread.join(timeMilli);
        }catch(InterruptedException e){
            System.err.println("Error delaying Main: " + e.getMessage());
        }
    }

    /**
    * Indefinetly pauses the Main thread that new thread partitioned from, to pause until new thread is complete.
    */
    public void delayMain() throws NullPointerException{
        try{
            thread.join();
        }catch(InterruptedException e){
            System.err.println("Error delaying Main: " + e.getMessage());
        }
    }
}