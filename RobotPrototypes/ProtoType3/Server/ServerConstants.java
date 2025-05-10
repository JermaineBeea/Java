package Server;

public enum ServerConstants {
    EXECUTION_ATTEMPTS(5);

    public final int num;

    ServerConstants(int constant){
        this.num = constant;
    }
}
