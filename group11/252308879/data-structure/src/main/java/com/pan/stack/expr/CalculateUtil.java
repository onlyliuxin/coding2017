package com.pan.stack.expr;

import java.util.Stack;

/**
 * Created by Pan on 2017/5/7.
 */
public class CalculateUtil {


    public static Float calculate(String op, Float f1, Float f2) {
        if (op.equals("+")) {
            return f1 + f2;
        }
        if (op.equals("-")) {
            return f1 - f2;
        }
        if (op.equals("*")) {
            return f1 * f2;
        }
        if (op.equals("/")) {
            return f1 / f2;
        }
        throw new RuntimeException(op + " is not supported");
    }

}
