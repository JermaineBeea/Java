long num = 899956;

// Method 1: Using string manipulation
String formatted = String.valueOf(num);
if (formatted.length() > 2) {
    formatted = formatted.substring(0, formatted.length() - 2) + 
                "." + 
                formatted.substring(formatted.length() - 2);
} else if (formatted.length() == 2) {
    formatted = "0." + formatted;
} else {
    formatted = "0.0" + formatted;
}
System.out.println(formatted); // "8999.56"

// Method 2: Using decimal division and formatting
double amount = num / 100.0;
String formatted2 = String.format("%.2f", amount);
System.out.println(formatted2); // "8999.56"

// Method 3: Using DecimalFormat
import java.text.DecimalFormat;
DecimalFormat df = new DecimalFormat("#,##0.00");
String formatted3 = df.format(num / 100.0);
System.out.println(formatted3); // "8,999.56" (with thousands separator)

// Without thousands separator:
DecimalFormat df2 = new DecimalFormat("0.00");
String formatted4 = df2.format(num / 100.0);
System.out.println(formatted4); // "8999.56"