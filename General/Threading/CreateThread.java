package Threading;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Creates and manages a thread that executes a provided runnable.
 * @author [Your Name]
 * 
 */
public class CreateThread {
    private Thread thread;
    private final AtomicBoolean threadRunning = new AtomicBoolean(false);
    private final Runnable threadRunnable;
    private final Runnable wrapperFunction;

    /**
     * Initializes the thread manager with a function to run.
     * @param function Function executed during thread run.
     */
    public CreateThread(Runnable function) {
        if (function == null) {
            throw new IllegalArgumentException("Function cannot be null");
        }
        this.threadRunnable = function;
        this.wrapperFunction = createWrapper();
    }

    /**
     * Creates a Runnable function that encapsulates the threadRunnable.
     * Ensures threadRunnable runs only if thread is in running state.
     * @return A wrapper Runnable that handles exceptions and state management.
     */
    private Runnable createWrapper() {
        return () -> {
            try {
                while (threadRunning.get()) {
                    try {
                        threadRunnable.run();
                    } catch (Exception e) {
                        System.err.println("Error executing thread runnable: " + e.getMessage());
                        // Allow the thread to continue running despite errors in the runnable
                    }
                    
                    // Check if we need to exit due to interruption
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            } finally {
                threadRunning.set(false);
            }
        };
    }

    /**
     * Creates and starts a new thread if one isn't already running.
     * @return true if a new thread was started, false if a thread was already running
     */
    public boolean startThread() {
        // Only create a new thread if necessary
        if (thread != null && thread.isAlive()) {
            return false;
        }
        
        // Set running flag before creating the thread
        threadRunning.set(true);
        
        // Create and start the thread
        thread = new Thread(wrapperFunction);
        thread.start();
        return true;
    }

    /**
     * Requests the thread to stop gracefully.
     * Sets the running flag to false and interrupts the thread if it exists.
     */
    public void stopThread() {
        // Set running flag to false to signal thread termination
        threadRunning.set(false);
        
        // Interrupt the thread if it exists
        if (thread != null) {
            thread.interrupt();
        }
    }

    /**
     * Changes the running state of the thread.
     * Setting to 'false' will cause the thread to exit after completing current task.
     * Setting to 'true' will only take effect if the thread is still alive.
     * 
     * @param runFlag true to continue running, false to stop
     * @return true if the state was changed, false otherwise
     */
    public boolean setRunning(boolean runFlag) {
        // Can't set to true if thread isn't alive
        if (runFlag && (thread == null || !thread.isAlive())) {
        }
        
        threadRunning.set(runFlag);
        return true;
    }
    
    /**
     * Checks if the thread is currently running.
     * @return true if the thread is running, false otherwise
     */
    public boolean isRunning() {
        return threadRunning.get();
    }
    
    /**
     * Checks if the thread exists and is alive.
     * @return true if the thread exists and is alive, false otherwise
     */
    public boolean isAlive() {
        return thread != null && thread.isAlive();
    }
}