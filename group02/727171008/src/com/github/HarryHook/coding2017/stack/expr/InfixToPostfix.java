package com.github.HarryHook.coding2017.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//中序表达式： 3*20+12*5-40/2  转换成后续表达式：3 20 * 12 5 * + 40 2 / -
//思路：当前token依次从左往右读取， 数字依次append， 当前入栈的运算符的优先级小于等于栈顶的运算符，栈顶操作符出栈
public class InfixToPostfix {

    public List<Token> convert(String expr) {
	
	TokenParser parser = new TokenParser();
	List<Token> tokens = parser.parse(expr);
	
	List<Token> listOfTokens = new ArrayList<>();
	
	Stack<Token> opsStack = new Stack<>();

	for (Token token : tokens) {

	    if (token.isNumber()) {
		listOfTokens.add(token);
	    } else if (token.isOperator()) { // 还需判断当前操作符和栈顶操作符的优先级
		if (opsStack.isEmpty()) {
		    opsStack.push(token);
		} else {
		    if (!token.hasHigherPriority(opsStack.peek())) {
			listOfTokens.add(opsStack.pop());
		    }
		    opsStack.push(token);
		}

	    }
	}
	while (!(opsStack.isEmpty())) { // exprStack 为空，但操作符栈还有元素
	    listOfTokens.add(opsStack.pop());
	}
	return listOfTokens;
    }

    public static void main(String[] args) {
	InfixToPostfix toPostfix = new InfixToPostfix();
	List<Token> t = new ArrayList<Token>();
	String expr = "3+20+12*5+40/2";
	t = toPostfix.convert(expr);
	System.out.println("expr: " + expr);
	System.out.println("PostfixExpr: " + t);
    }

}
