package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.coding.basic.stack.StackUtil;

public class InfixExpr {
	String expr = null;
	private List<Token> tokenList = new ArrayList<>();
	private final static String regex = "-|\\+|\\*|/|\\(|\\)";

	public InfixExpr(String expr) {
		this.expr = expr;
		init();
	}

	private void init() {
		if (StackUtil.isValidPairs(expr)) {
			initTokens();
		} else {
			throw new RuntimeException(
					"Syntax error, ( ) is not completed in your Expression");
		}
	}

	/**
	 * 表达式分解并入栈
	 */
	private void initTokens() {
		String[] nums = expr.split(regex);
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < nums.length; i++) {
			if (!nums[i].equals("")) {
				arrayList.add(nums[i]);
			}
		}
		char[] exChars = expr.toCharArray();
		int numInx = 0;
		for (int i = 0; i < exChars.length;) {
			char ex = exChars[i];
			int item = ex & 0xffff;
			if (item >= 48 && item <= 57) {
				int numSize = arrayList.get(numInx++).length();
				String num = new String(exChars, i, numSize);
				Token token = new Token(num, Token.NUMBER);
				tokenList.add(token);
				i += numSize;
			} else {
				Token token = new Token(String.valueOf(ex), Token.OPERA);
				tokenList.add(token);
				i += 1;
			}
		}
	}

	/**
	 * 从左向右扫描所有token，当遇到同优先级或低优先级的操作符时，把之前的操作符依次出栈并计算结果
	 * 扫描完所有token后，依次出栈所有操作数并计算结果
	 * 
	 * @return
	 */
	public float evaluate() {
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);
			switch (token.getType()) {
			case Token.NUMBER:
				numStack.push(token.getNumValue());
				break;
			case Token.OPERA:
				if (opStack.isEmpty()) {
					opStack.push(token);
				} else {
					while (!opStack.isEmpty() && !token.hasHigherPriority(opStack.peek())) {
						Token preOper = opStack.pop();
						Float latter = numStack.pop();
						Float former = numStack.pop();
						Float result = caculate(preOper, latter, former);
						numStack.push(result);
					}
					opStack.push(token);
				}
				break;
			default:
				throw new RuntimeException(" error not Number not Operate ");
			}
		}
		while (!opStack.isEmpty()) {
			Token pre = opStack.pop();
			Float f1 = numStack.pop();
			Float f2 = numStack.pop();
			Float result = caculate(pre, f1, f2);
			numStack.push(result);
		}
		return numStack.pop();
	}
	private Float caculate(Token t, Float latter,Float former){
		switch (t.getValue()) {
		case "+":
			return latter + former;
		case "-":
			return former - latter;
		case "*":
			return latter * former;
		case "/":
			return former / latter;
		default:
			throw new RuntimeException("operation"+t.getValue()+" isn't implemented");
		}
	}
}
