package third.download.api;

public class ConnectionException extends Exception {

    public ConnectionException(String message) {
        super("message:" + message);
    }

    public ConnectionException(Throwable cause){
        super(cause);
    }
}
