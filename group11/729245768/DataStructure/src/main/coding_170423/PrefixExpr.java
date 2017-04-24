package main.coding_170423;


import java.util.Stack;

/**
 * Created by peter on 2017/4/23.
 */
public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        String[] tokens = expr.split(" ");
        Stack<Float> operator = new Stack();
        int index = tokens.length - 1;
        while (index >= 0) {
            String s = tokens[index];
            if (Character.isDigit(s.charAt(0))) {
                //说明是操作数
                operator.push(Float.parseFloat(s));
            } else {
                //说明是操作符
                float opera1 = operator.pop();
                float opera2 = operator.pop();
                switch (s) {
                    case "+":
                        operator.push(opera1 + opera2);
                        break;
                    case "-":
                        operator.push(opera1 - opera2);
                        break;
                    case "*":
                        operator.push(opera1 * opera2);
                        break;
                    case "/":
                        operator.push(opera1 / opera2);
                        break;

                }
            }
        index--;
        }
        return operator.pop();
    }
}
