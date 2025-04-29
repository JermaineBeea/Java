package Threading;

public class NewThread {
    
    private Thread thread;
    private Runnable function;

    public NewThread(Runnable function){
        this.function = function;
    }

    public void startThread(){
        thread = new Thread(function);
        thread.start();
    }
    
    public void joinThread() throws InterruptedException {
        if (thread != null) {
            thread.join();
        }
    }
    
    public boolean isAlive() {
        return thread != null && thread.isAlive();
    }
}