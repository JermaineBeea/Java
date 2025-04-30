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
        
        // Create a wrapper around the user-provided runnable that:
        // 1. Only executes when the thread is flagged as running
        // 2. Handles all exceptions to prevent thread termination
        // 3. Ensures the running flag is properly reset when execution completes
        this.wrapperfunction = ()-> {
            try {
                if (threadrunning.get()) {
                    runnablefunction.run();
                }
            } catch (Exception e) {
                System.err.println("Error in thread execution: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Always mark the thread as not running when execution ends
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
     * Waits for this thread to complete for up to the specified time.
     * This method causes the current thread to pause execution until this thread terminates
     * or the specified amount of time elapses.
     *
     * @param timeoutMillis Maximum time to wait in milliseconds. A value of 0 means to wait indefinitely.
     * @throws NullPointerException if the thread has not been started via startThread()
     * @throws IllegalArgumentException if timeoutMillis is negative
     */
    public void waitForCompletion(long timeoutMillis) {
        if (thread == null) {
            throw new NullPointerException("Thread has not been initialized, call startThread() first");
        }
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("Timeout value cannot be negative");
        }
        try {
            thread.join(timeoutMillis);
        } catch (InterruptedException e) {
            System.err.println("Thread wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    /**
     * Waits for this thread to complete, with no timeout.
     * This method causes the current thread to pause execution until this thread terminates.
     *
     * @throws NullPointerException if the thread has not been started via startThread()
     */
    public void waitForCompletion() {
        if (thread == null) {
            throw new NullPointerException("Thread has not been initialized, call startThread() first");
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}