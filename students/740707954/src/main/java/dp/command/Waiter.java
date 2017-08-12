package dp.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 店小二
 * Created by lx on 2017/8/12.
 */
public class Waiter {

    private List<Command> commands = new ArrayList<>();

    /**
     * 发菜单
     */
    public void sendOrders() {
        commands.forEach((Command c) -> c.excute());
    }

    /**
     * 添加订单
     * @param command1
     */
    public void addOrder(Command command1) {
        commands.add(command1);
    }
}
