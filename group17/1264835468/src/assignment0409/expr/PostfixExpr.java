package assignment0409.expr;

import java.util.Stack;

public class PostfixExpr {
    String expr = null;
    Stack<Float> stack;

    public PostfixExpr(String expr) {
        this.expr = expr;
        stack = new Stack<>();
    }

    public float evaluate() {
        String[] tokens = expr.split(" +");

        for (String token : tokens) {
            if (isNum(token)) {
                stack.push((float) Integer.parseInt(token));
            } else {
                Operator operator = Operator.of(token.charAt(0));
                float op2 = stack.pop();
                float op1 = stack.pop();
                stack.push(operator.evaluate(op1, op2));
            }
        }
        float result = stack.pop();
        if (!stack.empty()) {
            throw new RuntimeException("Illegal expression");
        }
        return result;
    }

    private boolean isNum(String token) {
        return Character.isDigit(token.charAt(0));
    }


}
