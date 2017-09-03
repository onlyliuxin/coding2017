package command;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class OrderSteakCommand implements Command {

    private Cook cook;

    public OrderSteakCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookSteak();
    }
}
