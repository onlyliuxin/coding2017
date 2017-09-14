package dp.command;

/**
 * Created by lx on 2017/8/12.
 */
public class OrderPorkCommand implements Command {

    private Cook cook;

    public OrderPorkCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void excute() {
        cook.cookPork();
    }
}
