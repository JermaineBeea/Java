public class ClearTerminal {
    public static void main(String[] args) {
        try {
            System.out.print("Hello, World!");  // Print initial text
            Thread.sleep(2000);  // Wait for 2 seconds
            System.out.print("\r               \r");  // Overwrite the line with spaces to "erase" it
            Thread.sleep(1000);  // Wait for 1 second
            System.out.print("Text erased!");  // Print new text
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
