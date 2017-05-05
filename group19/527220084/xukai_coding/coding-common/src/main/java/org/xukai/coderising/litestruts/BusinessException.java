package org.xukai.coderising.litestruts;

/**
 * 业务逻辑异常
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);

    }

    public BusinessException(Throwable cause) {
        super(cause);

    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);

    }

}
