import java.util.concurrent.atomic.AtomicBoolean;

public class Createthread3 {

    private Thread thread; // Default value for Thread type is null;
    private final AtomicBoolean threadrunning = new AtomicBoolean(false);
    private final Runnable runnableFunction;
    private Runnable wrapperFunction;

    /**
     * Constructor to initialize with a Runnable function.
     * @param mainFunction Function executed in thread.
     */
    public Createthread3(Runnable mainFunction) {
        if (mainFunction == null) {
            throw new NullPointerException("Main function cannot be null");
        }
        
        this.runnableFunction = mainFunction;
        createWrapperFunction();
    }
    
    /**
     * Constructor to initialize with any Object that can be executed as a function.
     * @param functionObject Object representing a function to be executed.
     */
    public Createthread3(Object functionObject) {
        if (functionObject == null) {
            throw new NullPointerException("Function object cannot be null");
        }
        
        if (functionObject instanceof Runnable) {
            this.runnableFunction = (Runnable) functionObject;
        } else {
            // Create a simple adapter that calls toString() if it's not a Runnable
            // You can replace this with any appropriate default behavior
            this.runnableFunction = () -> {
                functionObject.toString();
                System.out.println("Executed function object: " + functionObject);
            };
        }
        
        createWrapperFunction();
    }
    
    /**
     * Creates the wrapper function that handles execution and error handling.
     */
    private void createWrapperFunction() {
        // Create a wrapper around the user-provided runnable that:
        // 1. Only executes when the thread is flagged as running
        // 2. Handles all exceptions to prevent thread termination
        // 3. Ensures the running flag is properly reset when execution completes
        this.wrapperFunction = () -> {
            try {
                if (threadrunning.get()) {
                    runnableFunction.run();
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
     * Starts the thread, then runs the mainFunction.
     */
    public void startThread() {
        try {
            // Check if thread is already running
            if (thread != null && thread.isAlive()) {
                return;
            }

            // If not started, set thread as running
            threadrunning.set(true);

            // Initialize thread, and pass wrapperFunction to thread.
            thread = new Thread(wrapperFunction);
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
     * @return true if the thread is running, false otherwise
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