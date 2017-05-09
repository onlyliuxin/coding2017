package task7.expr;

import task5.stack.Stack;

import java.util.List;

public class PrefixExpr {

    private String expr;

    private Stack<Float> numberStack = new Stack<>();

    public PrefixExpr(String expr) {
        this.expr = expr;

    }

    public float evaluate() {
        List<Token> parse = new TokenParser().parse(expr);
        for (int i = parse.size() - 1; i >= 0; i--) {
            Token token = parse.get(i);
//            前缀表达式:从右向左遍历 遇到操作数入栈,遇到操作符弹出操作数栈的两个数计算再入栈
            if (token.isNumber())
                numberStack.push((float) token.getIntValue());
            else
                numberStack.push(cal(token.toString(), numberStack.pop(), numberStack.pop()));
        }
        /*while (!operatorStack.isEmpty()) {
            numberStack.push(cal(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
        }*/
        return numberStack.peek();
    }

    private static float cal(String operator, float var1, float var2) {
        switch (operator) {
            case "+":
                return var1 + var2;
            case "-":
                return var1 - var2;
            case "*":
                return var1 * var2;
            case "/":
                return var1 / var2;
        }
        return -1;
    }
}
