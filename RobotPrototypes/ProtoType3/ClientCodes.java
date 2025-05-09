

public enum ClientCodes {
    STATUS_OK(300), 
    STATUS_ERROR(700),
    HANDSHAKE(2468);

    public int code;

    ClientCodes(int code){
        this.code = code;
    }

}
