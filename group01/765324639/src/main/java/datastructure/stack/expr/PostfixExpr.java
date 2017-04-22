package datastructure.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
	    TokenParser tokenParser = new TokenParser(expr);
        List<Token> tokenList = tokenParser.parse();
        
        Stack<Float> floatStack = new Stack<>();
        
        for (Token token : tokenList) {
            if (token.isNumber()) {
                floatStack.push(Float.valueOf(token.getValue()));
            } else if (token.isOperator()) {
                float num2 = floatStack.pop();
                float num1 = floatStack.pop();
                float result = calculate(token.getValue(), num1, num2);
                floatStack.push(result);
            }
        }
        
        return floatStack.pop();
    }
    
    private float calculate(String operator, float num1, float num2) {
        float result = 0;
        switch (operator.charAt(0)) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }
}
