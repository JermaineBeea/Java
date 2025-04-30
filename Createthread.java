import java.util.concurrent.atomic.AtomicBoolean;

public class Createthread {

    private Thread thread; // Default value for Thread type is null;
    private final AtomicBoolean threadrunning = new AtomicBoolean(false);
    private final Runnable runnablefunction;
    private Runnable wrapperfunction;

    /**
    * Constructor to initialise the runnable function to the mainfunction passed to instance.
    * @param mainfunction Function executed in thread.
    */
    public Createthread(Runnable mainfunction) {
        if (mainfunction == null) {
            throw new NullPointerException("Main function cannot be null");
        }
        
        this.runnablefunction = mainfunction;

        //Lambda runnable that encapsulates the runnablefunction, so that function runs if thread is running and has started.
        this.wrapperfunction = ()-> {
            try {
                if (threadrunning.get()) { // .get() returns true if thread is running, else false.
                    runnablefunction.run();
                }
            } catch (Exception e) {
                System.err.println("Error in thread execution: " + e.getMessage());
                e.printStackTrace();
            } finally {
                threadrunning.set(false);
            }
        };
    }

    /**
    * Starts the thread, then runs the mainfunction.
    */
    public void startThread() {
        try {
            // Check if thread is already running
            if (thread != null && thread.isAlive()) {
                return;
            }

            // If not started, set thread as running
            threadrunning.set(true);

            // Initialise thread, and pass wrapperfunction to thread.
            thread = new Thread(wrapperfunction);
            thread.start();

        } catch (Exception e) {
            System.err.println("Error starting thread: " + e.getMessage());
            e.printStackTrace();
            threadrunning.set(false);
        }
    }

    /**
    * Stops thread run.
    * @throws NullPointerException if thread has not been started
    */
    public void stopThread() {
        if (thread == null) {
            throw new NullPointerException("Thread has not been initialized, call startThread() first");
        }
        threadrunning.set(false);
        thread.interrupt();
    }

    /**
    * Validates if thread is running or not.
    */
    public Boolean threadrunning() {
        return threadrunning.get();
    }

    /**
    * Pauses the Main thread that new thread partitioned from, until new thread is complete.
    * @param timeMilli Pause time in milli seconds. E.g 1000 milli-seconds = 1 second.
    * @throws NullPointerException if thread has not been started
    */
    public void delayMain(int timeMilli) {
        if (thread == null) {
            throw new NullPointerException("Thread has not been initialized, call startThread() first");
        }
        try {
            thread.join(timeMilli);
        } catch (InterruptedException e) {
            System.err.println("Error delaying Main: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    /**
    * Indefinetly pauses the Main thread that new thread partitioned from, to pause until new thread is complete.
    * @throws NullPointerException if thread has not been started
    */
    public void delayMain() {
        if (thread == null) {
            throw new NullPointerException("Thread has not been initialized, call startThread() first");
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Error delaying Main: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}