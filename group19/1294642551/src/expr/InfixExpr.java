package expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
		
	}

	public float evaluate() {
		List<Token> tokenList = TokenParser.parse(expr);
		Stack<Token> operatorStack = new Stack<Token>();
		Stack<Token> valueStack = new Stack<Token>();
		
		for(int i = 0; i < tokenList.size(); i++){
			Token token = tokenList.get(i);
			if(token.isOperator()){//新元素是操作符
				if(operatorStack.isEmpty()){
					operatorStack.push(token);
				}else{
					while(!operatorStack.isEmpty() && !token.hasHigherPriority(operatorStack.peek())){
						popCompute(operatorStack, valueStack);
					}
					operatorStack.push(token);
				}
			}else{//新元素是数值
				valueStack.push(token);
			}
			
			
		}
		
		while(!operatorStack.isEmpty()){
			popCompute(operatorStack, valueStack);
		}
		
		
		return valueStack.peek().getFloatValue();
	}
	
	// 计算加减乘除的结果
	public float compute(float num1, float num2, String operator){
		if(operator.equals("+")){
			return num1 + num2;
		}else if(operator.equals("-")){
			return num1 - num2;
		}else if(operator.equals("*")){
			return num1 * num2;
		}else{
			return num1 / num2;
		}
	}
	
	// 弹栈计算，取一个操作符和两个操作数进行计算，并将得到的操作数压入数值栈
	public void popCompute(Stack<Token> operatorStack, Stack<Token> valueStack){
		float num2 = valueStack.pop().getFloatValue();
		float num1 = valueStack.pop().getFloatValue();
		String operator = operatorStack.pop().getOperator();
		float value = compute(num1, num2, operator);
		valueStack.push(new Token(1, String.valueOf(value)));

	}
	
	
}
