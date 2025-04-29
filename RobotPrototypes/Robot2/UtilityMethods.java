package RobotPrototypes.Robot2;
/**
 * This static method is intended to delay the output messages.
 * @param seconds Time in seconds program delays for.
 */
public abstract class UtilityMethods {

    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void delayPrint(int seconds, String string){
        try {
            Thread.sleep(seconds * 1000);
            System.out.println(string);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}