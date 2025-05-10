package Server;
public enum ServerCodes {
    STATUS_OK(300), 
    STATUS_ERROR(700),
    HANDSHAKE(3579),
    EXECUTION_ATTEMPTS(5);
    
    public int code;

    ServerCodes(int statusCode){
        this.code = statusCode;
    }
    
}
