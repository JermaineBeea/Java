package Server;


public enum ServerCodes {
    STATUS_OK(300), 
    STATUS_ERROR(700),
    STATUS_EXCEPTION(1100),
    HANDSHAKE(3579);

    public final int code;

    ServerCodes(int codeArg){
        this.code = codeArg;
    }
}

