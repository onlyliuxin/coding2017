package expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> tokenList = TokenParser.parse(expr);
		tokenList = reverse(tokenList);
		Stack<Token> operatorStack = new Stack<Token>();
		Stack<Token> valueStack = new Stack<Token>();
		for(int i = 0; i < tokenList.size(); i++){
			Token token = tokenList.get(i);
			if(token.isOperator()){
				operatorStack.push(token);
				popCompute(operatorStack, valueStack);
			}else{
				valueStack.push(token);
			}
			
		}
		
		return valueStack.peek().getFloatValue();
	}
	
	public List<Token> reverse(List<Token> tokenList){
		List<Token> newList = new ArrayList<Token>();
		int size = tokenList.size();
		for(int i = 0; i < size; i++){
			newList.add(tokenList.get(size - 1 - i));
		}
		return newList;
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
	// 注：在前缀表达式的计算中交换了第一个操作数和第二个操作数的位置，因为前缀表示式是从后向前遍历的
	public void popCompute(Stack<Token> operatorStack, Stack<Token> valueStack){
		float num2 = valueStack.pop().getFloatValue();
		float num1 = valueStack.pop().getFloatValue();
		String operator = operatorStack.pop().getOperator();
		float value = compute(num2, num1, operator);//交换了操作数的位置
		valueStack.push(new Token(1, String.valueOf(value)));

	}
	
	
	
}
