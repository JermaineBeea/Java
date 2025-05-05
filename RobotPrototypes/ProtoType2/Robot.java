public class Robot {
    
    private Position posInstance;
    private Commands robotCommands;

    public Robot(){
        this.posInstance = new Position();        
        this.robotCommands = new Commands(posInstance);
    }

    public Position position(){
        return posInstance;
    }

    public Commands command(){
        return robotCommands;
    }

}
