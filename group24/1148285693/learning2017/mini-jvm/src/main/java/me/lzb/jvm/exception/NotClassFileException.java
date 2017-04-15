package me.lzb.jvm.exception;

/**
 * Created by LZB on 2017/4/14.
 */
public class NotClassFileException extends Exception{
    public NotClassFileException(String message, Exception cause) {
        super(message, cause);
    }

    public NotClassFileException(String message) {
        super(message);
    }
}
