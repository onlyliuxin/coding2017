package com.coding.week6.expr;

import com.coding.weak1.Stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfixExpr {
	String expr = null;
	private Stack numberStack;
	private Stack operatorStack;
	private List<Token> tokens;
	public InfixExpr(String expr) {
		this.expr = expr;
		this.numberStack = new Stack();
		this.operatorStack = new Stack();
		tokens = new ArrayList<>();
	}


	public float evaluate() {
		fillStack();
		while (!operatorStack.isEmpty()) {
			char symbol = (char) operatorStack.pop();
			float operTop1 = (float) numberStack.pop();
			float operTop2 = (float) numberStack.pop();
			numberStack.push(caculate(symbol, operTop2, operTop1));
		}
		return (float)numberStack.pop();
	}

	public void parseTokens() {
		char[] chars = expr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Token.isOperator(c)) {
				Token token = new Token(c+"");
				tokens.add(token);
			} else {
				String t = "";
				while (Token.isDigit(c)){
					t += c;
					i++;
					if (i == chars.length)
						break;
					c = chars[i];
				}
				Token token = new Token(t);
				tokens.add(token);
				i--;
			}
		}
	}


	public void fillStack() {
		parseTokens();
		Iterator<Token> iterator = tokens.iterator();
		while (iterator.hasNext()) {
			Token token = iterator.next();
			if (token.isNumber()) {
				numberStack.push((float)token.parseInt());
				continue;
			}
			if (token.isOperator()) {
				char operator = token.parseOperator();
				if (operatorStack.isEmpty()) {
					operatorStack.push(operator);
				}else {
					char topSymbol = (char)operatorStack.peek();
					if (compare(operator, topSymbol) > 0) {
						operatorStack.push(operator);
					} else {
						float operTop1 = (float) numberStack.pop();
						float operTop2 = (float) numberStack.pop();
						numberStack.push(caculate(topSymbol, operTop2, operTop1));
						operatorStack.pop();
						operatorStack.push(operator);
					}
				}
			}

		}
	}


	private float caculate(char symbol, float oper1, float oper2) {
		if ('*' == symbol)
			return oper1 * oper2;
		else if ('/' == symbol)
			return oper1 / oper2;
		else if ('+' == symbol)
			return oper1 + oper2;
		else if ('-' == symbol)
			return oper1 - oper2;
		else
			throw new RuntimeException("this operation has not implement");
	}

	public int compare(char opertor1, char opertor2) {
		if (!Token.isOperator(opertor1) )
			throw new IllegalArgumentException(opertor1 + "is not supported opertor");
		if (!Token.isOperator(opertor2))
			throw new IllegalArgumentException(opertor2 + "is not supported opertor");
		if (Token.isAddOrSub(opertor1)) {
			if (Token.isAddOrSub(opertor2))
				return 0;
			else
				return -1;
		}
		else {
			if (Token.isAddOrSub(opertor2))
				return 1;
			else
				return 0;
		}
	}

	public String printNumberStack() {
		return numberStack.toString();
	}

	public String printOperatorStack() {
		return operatorStack.toString();
	}
}
