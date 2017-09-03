package dp.command;

/**
 * Created by lx on 2017/8/12.
 */
public class OrderSteakCommand implements Command {

    private Cook cook;

    public OrderSteakCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void excute() {
        cook.cookSteak();
    }
}
