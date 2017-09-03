package command;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class OrderPorkCommand implements Command {

    private Cook cook;

    public OrderPorkCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookPork();
    }
}
