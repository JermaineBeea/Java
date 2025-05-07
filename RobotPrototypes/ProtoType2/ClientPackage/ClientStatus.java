package ClientPackage;

public enum ClientStatus {
    STATUS_EXIT(500), STATUS_OK(200), HANDSHAKE_RESPONSE(700);

    public int code;
    ClientStatus(int statuscode){
        this.code = statuscode;
    }
}
