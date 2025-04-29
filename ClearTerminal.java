public class ClearTerminal {
    public static void main(String[] args) {
        printSlowly("Hi there", 300);
    }

    /**
     * Print a message slowly
     * @param message Message to be printed
     * @param time Tiem in milli-seconds. E.g 1000 = 1 second.
     */
    public static void printSlowly(String message, int time){
        int strlenght = message.length();
        for(int n = 0; n < strlenght; n++){
            System.out.print(message.charAt(n));
            try {
                Thread.sleep(time);
            } catch (Exception e) {
                Thread.currentThread().isInterrupted();
            }
        }
    }
}
