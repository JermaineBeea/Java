public class practice {
    
    public static void main(String[] args) {
        
    }

    public static char method1(int arg){
        return switch(arg){
            case 1 -> 10;
            default -> 0;
        };
    }

    public static char method2(int arg){
        switch(arg){
            case 1: return 10;
            default: return 1;
        }
    }
}
