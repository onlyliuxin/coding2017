package teePrivate.designPattern.chainResponsePattern;

/**
 * @author : 温友朝
 * @date : 2017/5/10
 */
public abstract class Handler {
    private Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler();
}
