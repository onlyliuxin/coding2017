import java.util.*;
import java.util.Stack;

/**
 * Created by Lxx on 2017/4/29.
 */
public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser parser = new TokenParser();
        List<Token> tokens = parser.parse(this.expr);
        Stack<Float> numStack = new Stack<>();
        Stack<Token> allStack = new Stack<>();
        for (Token token : tokens) {
            allStack.push(token);
        }
        while (!allStack.isEmpty()) {
            if (allStack.peek().isNumber()) {
                numStack.push(new Float(allStack.pop().getIntValue()));
            }
            if (allStack.peek().isOperator()) {
                Token token = allStack.pop();
                Float f1 = numStack.pop();
                Float f2 = numStack.pop();
                numStack.push(calculate(token.toString(), f1, f2));
            }
        }
        return numStack.pop().floatValue();
    }

    private Float calculate(String op, float f1, float f2){
        if(op.equals("+")){
            return f1 + f2;
        }
        if(op.equals("-")){
            return f1 - f2;
        }
        if(op.equals("*")){
            return f1 * f2;
        }
        if (op.equals("/")){
            return f1 / f2;
        }
        return null;
    }
}
