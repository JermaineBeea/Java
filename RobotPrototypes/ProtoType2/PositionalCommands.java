public class PositionalCommands {
    
    private Position posInstance;

    PositionalCommands(Position  instance){
        this.posInstance = instance;
    }
    
    private static void forward(double distance){
        posInstance.setX(posInstance.getX() + distance);
    }
    private static void backward(double distance){

    }
    private static void right(double distance){

    }
    private static void left(double distance){

    }

}
