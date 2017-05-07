package expr;

import stack.MyStack;

import java.util.List;

/**
 * Created by gongxun on 2017/4/22.
 */
public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser tokenParser = new TokenParser();
        if (expr == null)
            throw new RuntimeException("wrong expr !");
        MyStack<Float> stack = new MyStack<Float>();
        List<Token> tokens = tokenParser.parse(expr);
        if (tokens != null) {
            for (Token token : tokens) {
                if (token.isNumber())
                    stack.push(((Integer) token.getIntValue()).floatValue());
                else {
                    Float value = calcValue(token, stack);
                    stack.push(value);
                }
            }
        }
        return stack.pop();
    }

    private Float calcValue(Token token, MyStack<Float> stack) {
        String operator = token.toString();
        Float rightNum = stack.pop();
        Float leftNum = stack.pop();
        if (operator.equals("+"))
            return leftNum + rightNum;
        else if (operator.equals("-"))
            return leftNum - rightNum;
        else if (operator.equals("*"))
            return leftNum * rightNum;
        return leftNum / rightNum;
    }
}
