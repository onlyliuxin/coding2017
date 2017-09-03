package com.coderings.dp.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class Waiter {
    private List<Command> commands = new ArrayList<>();

    public void addOrder(Command command) {
        commands.add(command);
    }

    public void sendOrders() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
