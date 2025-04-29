package Threading;

import java.util.concurrent.atomic.AtomicBoolean;

public class NewThread2 {
    
    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final Runnable originalFunction;

    public NewThread2(Runnable function){
        // Store the original function
        this.originalFunction = function;
    }

    public void startThread(){
        if (thread != null && thread.isAlive()) {
            return; // Thread already running
        }
        
        running.set(true);
        
        // Wrap the original function in our own that checks the running flag
        Runnable wrappedFunction = () -> {
            try {
                // Run the original function only if we're still running
                if (running.get()) {
                    originalFunction.run();
                }
            } catch (Exception e) {
                // Handle any exception that might occur
                System.err.println("Exception in thread: " + e.getMessage());
                e.printStackTrace();
            } finally {
                running.set(false);
            }
        };
        
        thread = new Thread(wrappedFunction);
        thread.start();
    }
    
    public void stopThread() {
        if (thread != null) {
            running.set(false);
            thread.interrupt();
        }
    }
    
    public void joinThread() throws InterruptedException {
        if (thread != null) {
            thread.join();
        }
    }
    
    public void joinThread(long millis) throws InterruptedException {
        if (thread != null) {
            thread.join(millis);
        }
    }
    
    public boolean isAlive() {
        return thread != null && thread.isAlive();
    }
    
    public boolean isRunning() {
        return running.get();
    }
}