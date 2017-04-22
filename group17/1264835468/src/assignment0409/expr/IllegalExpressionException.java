package assignment0409.expr;

/**
 * Created by Administrator on 2017/4/16.
 */
class IllegalExpressionException extends RuntimeException {
    public IllegalExpressionException() {
    }

    public IllegalExpressionException(String message) {
        super(message);
    }
}
