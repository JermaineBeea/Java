public class MainRun{

  static String getFibonnaci(int n){
    StringBuilder sequence = new StringBuilder("Fibonacci Sequence: ");
    if(n<= 0) return sequence.toString();

    int num1 = 0; int num2 = 1;
    sequence.append(num1);

    if(n == 1) return sequence.toString();
    sequence.append(", ").append(num2);

    for(int indx = 2; indx < n; indx ++){
        sequence.append(", ").append(num1 + num2);
        num1 = num2;
        num2 = num1 + num2;
    }

    return sequence.toString();
  }
}