package expr;

import stack.MyStack;

import java.util.List;

/**
 * Created by gongxun on 2017/4/22.
 * 从后向前遍历入栈
 */
public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser tokenParser = new TokenParser();
        if (expr == null)
            throw new RuntimeException("wrong expr !");
        MyStack<Float> stack = new MyStack<Float>();
        List<Token> tokens = tokenParser.parse(expr);
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token token = tokens.get(i);
            if (token.isNumber())
                stack.push(((Integer) token.getIntValue()).floatValue());
            else {
                Float value = calcValue(stack, token.toString());
                stack.push(value);
            }
        }
        return stack.pop();
    }


    private Float calcValue(MyStack<Float> stack, String operator) {
        Float leftNum = stack.pop();
        Float rightNum = stack.pop();
        if (operator.equals("+"))
            return leftNum + rightNum;
        else if (operator.equals("-"))
            return leftNum - rightNum;
        else if (operator.equals("*"))
            return leftNum * rightNum;
        return leftNum / rightNum;
    }
}
