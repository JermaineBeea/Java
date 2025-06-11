import java.text.DecimalFormat;

public class Format {

    public static void main(String[] args) {
            
    long number1 = 2433;
    double number2 = 7899;

    // Technique one
    DecimalFormat decFormat = new DecimalFormat("R0.00");
    String strFormat =  decFormat.format(number1 / 100);
    System.out.println("Technique 1 result: " + strFormat);
    
    // Technique two
    String strFormat2 = "R" + String.format("%.2f", number2/100);    
    System.out.println("Technique 2 results: " + strFormat2);

    long rands = number1 / 100;
    long cents = number1 % 100;
    String result = String.format("R%d.%02d", rands, cents);
    System.out.println(result);
    }

    

}
