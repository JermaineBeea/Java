package Threading;

public class CreateThread {
    
    private Thread thread;

    public void startThread() {
        thread = new Thread(this::execution);
        thread.start();
    }

    private void execution() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Doing something");
            // Optional: Add a small delay to make interruption more responsive
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                break;
            }
        }
    }

    public void closeThread() {
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Propagate the interruption
            }
        }
    }
}