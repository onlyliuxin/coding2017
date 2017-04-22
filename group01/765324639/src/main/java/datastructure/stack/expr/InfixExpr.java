package datastructure.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser tokenParser = new TokenParser(expr);
		List<Token> tokenList = tokenParser.parse();
		
		Stack<Token> operaStack = new Stack<>();
		Stack<Float> floatStack = new Stack<>();
		
		for (int i = 0; i < tokenList.size(); i++) {
		    if (Token.OPERATOR.equals(tokenList.get(i).getType())) {
		        if (operaStack.isEmpty()) {
		            operaStack.push(tokenList.get(i));
		        } else {
		            Token topToken = operaStack.peek();
		            if (tokenList.get(i).comparePriority(topToken) > 0) {
		                operaStack.push(tokenList.get(i));
		            } else {
		                float result = stackTopCalculate(operaStack, floatStack);
                        floatStack.push(result);
                        
                        i--;
		            }
		        }
		    } else {
		        floatStack.push(Float.valueOf(tokenList.get(i).getValue()));
		    }
		}
		while (!operaStack.empty()) {
    		float temp = stackTopCalculate(operaStack, floatStack);
            floatStack.push(temp);
		}
		return floatStack.pop();
	}

    private float stackTopCalculate(Stack<Token> operaStack, Stack<Float> floatStack) {
        String operator = operaStack.pop().getValue();
        float num2 = floatStack.pop();
        float num1 = floatStack.pop();
        float result = calculate(operator, num1, num2);
        return result;
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
