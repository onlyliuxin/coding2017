package task7.expr;

import task5.stack.Stack;

import java.util.List;

public class PostfixExpr {
    private String expr = null;

    private Stack<Float> numberStack = new Stack<>();

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<Token> parse = new TokenParser().parse(expr);
        for (int i = 0; i < parse.size(); i++) {
            Token token = parse.get(i);
//            后缀表达式:从左向右遍历 遇到操作数入栈,遇到操作符弹出操作数栈的两个数计算再入栈
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
                return var2 + var1;
            case "-":
                return var2 - var1;
            case "*":
                return var2 * var1;
            case "/":
                return var2 / var1;
        }
        return -1;
    }
}