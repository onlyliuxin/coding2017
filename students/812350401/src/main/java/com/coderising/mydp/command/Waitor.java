package com.coderising.mydp.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class Waitor {
    List<Command> commands = new ArrayList<>();

    public void addOrder(Command command) {
        commands.add(command);
    }

    public void sendOrders() {
        commands.stream().forEach(Command::execute);
    }

}
