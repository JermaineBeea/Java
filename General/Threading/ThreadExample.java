package Threading;

public class ThreadExample {
    public static void main(String[] args) {
        // Create a new thread with a Runnable lambda
        NewThread myThread = new NewThread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread running: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Start the thread
        myThread.startThread();
        
        // Main thread continues execution
        System.out.println("Main thread continues...");
        
        try {
            // Wait for the thread to finish
            myThread.joinThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("All threads finished");
    }
}