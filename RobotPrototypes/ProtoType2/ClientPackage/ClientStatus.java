package ClientPackage;

public enum ClientStatus {
    STATUS_EXIT(500), STATUS_ERROR(700);

    public int code;
    ClientStatus(int statuscode){
        this.code = statuscode;
    }
}
