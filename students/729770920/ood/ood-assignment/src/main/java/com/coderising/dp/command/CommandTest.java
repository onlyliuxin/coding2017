package command;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class CommandTest {

    public static void main(String[] args) {
        Cook cook = new Cook();
        Waiter waiter = new Waiter();

        Command command1 = new OrderSteakCommand(cook);
        Command command2 = new OrderPorkCommand(cook);

        waiter.addOrder(command1);
        waiter.addOrder(command2);

        waiter.sendOrders();
    }

}
