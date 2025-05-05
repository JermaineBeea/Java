public class Robot {
    
    private Position posInstance;
    private Command robotCommands;

    public Robot(){
        this.posInstance = new Position();        
        this.robotCommands = new Command(posInstance);
    }

    public Position position(){
        return posInstance;
    }

    public Command command(){
        return robotCommands;
    }

}
