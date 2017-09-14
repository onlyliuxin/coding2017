package command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class Waiter {

    Queue<Command> commands = new LinkedList<>();

    public void addOrder(Command command) {
        commands.add(command);
    }

    public void sendOrders() {
        while (!commands.isEmpty()) {
            commands.poll().execute();
        }
    }

}
