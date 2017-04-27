package basic.dataStructure.stack.expr;

import basic.dataStructure.stack.Stack;

import java.util.List;

/**
 * 后序表达式
 */
public class PostfixExpr {
	String expr = null;

	/**
	 * 23*21-/341-*+
	 */
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> tokens = TokenParser.parse(expr);

		Stack numbers = new Stack();
		Stack opers = new Stack();

		for(Token t : tokens){
			if(t.isNumber()) numbers.push(t);
			if(t.isOperator()) opers.push(t);

			if(opers.size() == 1){
				float num1 = ((Token)numbers.pop()).getFloatValue();
				float num2 = ((Token)numbers.pop()).getFloatValue();
				String oper = (opers.pop()).toString();
				float res = Calculator.getFloat(num1, num2, oper);
				numbers.push(new Token(Token.NUMBER, res + ""));
			}
		}

		return ((Token) numbers.pop()).getFloatValue();
	}
	
	
}
