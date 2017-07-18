package com.coderising.ood.payroll.db;

import com.coderising.ood.payroll.Paycheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class MockDB {

    private static Map<Integer, Stack<Paycheck>> map;

    static {
        map = new HashMap<Integer, Stack<Paycheck>>();
    }

    public static void put(int id, Paycheck pc) {
        if (map.containsKey(id)) {
            Stack<Paycheck> stack = map.get(id);
            stack.push(pc);
        } else {
            Stack<Paycheck> stack = new Stack<>();
            stack.push(pc);
            map.put(id, stack);
        }
    }

    public static Paycheck peek(int id) {
        if (map.containsKey(id)) {
            return map.get(id).peek();
        }
        return null;
    }
}
