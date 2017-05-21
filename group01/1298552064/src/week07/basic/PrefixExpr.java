package week07.basic;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	private String expr = null;

	public PrefixExpr(String expr) {
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
		Stack<Integer> numStack = new Stack<>();
		for(int i = tokens.size() - 1; i >= 0;i--){
			Token token = tokens.get(i);
			if(token.isNumber()){
				numStack.push(token.getIntValue());
			}else if(token.isOperator()){
				int n1 = numStack.pop();
				int n2 = numStack.pop();
				numStack.push(cal(token.value.charAt(0), n1, n2));
			}
		}
		return (float)numStack.pop() ;
	}
}
