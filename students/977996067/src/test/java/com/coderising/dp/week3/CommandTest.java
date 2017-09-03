package com.coderising.dp.week3;

import com.coderising.dp.week3.command.*;
import org.junit.Test;

public class CommandTest {

    @Test
    public void testCook() {
        Cook cook = new Cook();
        Waiter waiter = new Waiter();
        Command c1 = new OrderPorkCommand(cook);
        Command c2 = new OrderSteakCommand(cook);
        waiter.addOrder(c1);
        waiter.addOrder(c2);
        waiter.sendOrders();
    }
}
