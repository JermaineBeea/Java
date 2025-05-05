public class Commands {
    
    private Position posInstance;

    Commands(Position  instance){
        this.posInstance = instance;
    }
    
    public void forward(double distance){
        posInstance.setX(posInstance.getX() + distance);
    }
    public void backward(double distance){

    }
    public void right(double distance){

    }
    public void left(double distance){

    }

}
