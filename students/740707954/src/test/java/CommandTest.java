import dp.command.*;
import org.junit.Test;

/**
 * Created by lx on 2017/8/12.
 */
public class CommandTest {

    @Test
    public void testCommand() {
        // 创建厨师
        Cook cook = new Cook();

        // 创建店小二
        Waiter waiter = new Waiter();

        Command command1 = new OrderSteakCommand(cook);
        Command command2 = new OrderPorkCommand(cook);

        waiter.addOrder(command1);
        waiter.addOrder(command2);
        waiter.sendOrders();
    }
}
