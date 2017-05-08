package assignment0409.expr;

import java.util.Stack;

public class PrefixExpr {
    String expr = null;
    Stack<Float> stack = new Stack<>();

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        String[] tokens = expr.split(" +");
        for (int i = tokens.length - 1; i >= 0; i--) {
            if (isNum(tokens[i])) {
                stack.push((float) Integer.parseInt(tokens[i]));
            } else {
                stack.push(Operator.of(tokens[i].charAt(0)).evaluate(stack.pop(), stack.pop()));
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
