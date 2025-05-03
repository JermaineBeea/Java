/**
 * Status codes used for client-server communication.
 * Each status code has a numeric value and associated message.
 */
public enum StatusCode {
    SERVER_FULL(300, "The server is full"),
    ONBOARDING_COMPLETE(500, "Client onboarding is complete");

    public final int code;
    public final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}