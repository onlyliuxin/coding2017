package code03.v1.api;

public class ConnectionException extends Exception {

    public ConnectionException(String message,Throwable e){
        super(message,e);
    }

}
