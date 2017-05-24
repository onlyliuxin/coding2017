package week06.basic;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	private String expr = null;

	public InfixExpr(String expr) {
		this.expr = expr;
	}
	
	/**
	 * 计算
	 * @param operator 运算符
	 * @param i1 操作数1
	 * @param i2 操作数2
	 * @return
	 */
	private int cal(char operator, int i1, int i2){
		int result = 0;
		switch(operator){
			case '+' : result = i1 + i2 ; break;
			case '-' : result = i1 - i2 ; break;
			case '*' : result = i1 * i2 ; break;
			case '/' : 
				if(i1 == 0){
					throw new ArithmeticException("除数不能为0");
				}
				result = i1 / i2 ; break;
		}
		return result;
	}

	public float evaluate() {
		TokenParser tokenParser = new TokenParser();
		List<Token> tokens = tokenParser.parse(expr);
		Stack<Token> operatorStack = new Stack<>();
		Stack<Integer> operandStack = new Stack<>();
		for(Token token : tokens){
			if(token.isNumber()){
				operandStack.push(token.getIntValue());
				continue;
			}
			
			if(token.isOperator()){
				if(operatorStack.isEmpty()){
					operatorStack.push(token);
					continue;
				}
				
				Token pre = operatorStack.peek();
				boolean hasHigherPriority = token.hasHigherPriority(pre);
				if(hasHigherPriority){
					operatorStack.push(token);
				}else{
					int n2 = operandStack.pop();
					int n1 = operandStack.pop();
					String operator = operatorStack.pop().value;
					operatorStack.push(token);
					operandStack.push(cal(operator.charAt(0), n1, n2));
				}
			}
		}
		
		while(!operatorStack.isEmpty()){
			if(operatorStack.size() == 1){
				int n2 = operandStack.pop();
				int n1 = operandStack.pop();
				String operator = operatorStack.pop().value;
				operandStack.push(cal(operator.charAt(0), n1, n2));
				break;
			}
			
			Token cur = operatorStack.pop();
			Token pre = operatorStack.pop(); 
			if(cur.hasHigherPriority(pre)){
				int n2 = operandStack.pop();
				int n1 = operandStack.pop();
				operandStack.push(cal(cur.value.charAt(0), n1, n2));
				
				operatorStack.push(pre);
			}else{
				int n3 = operandStack.pop();
				int n2 = operandStack.pop();
				int n1 = operandStack.pop();
				operandStack.push(cal(pre.value.charAt(0), n1, n2));
				operandStack.push(n3);
				
				operatorStack.push(cur);
			}
			
		}
		return (float)operandStack.pop();
	}
	
	public static void main(String[] args) {
		InfixExpr expr = new InfixExpr("10-2*3+50");
		System.out.println(expr.evaluate());
	}

}
