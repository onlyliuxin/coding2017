package com.coding.week7;

import com.coding.week6.exprNew.Operator;
import com.coding.week6.exprNew.Token;
import com.coding.week6.exprNew.TokenParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		/**
		 1 建立符号栈
		 2 顺序扫描中序表达式
		 a） 是数字， 直接输出
		 b） 是运算符
		 i : “(” 直接入栈
		 ii : “)” 将符号栈中的元素依次出栈并输出, 直到 “(“, “(“只出栈, 不输出
		 iii: 其他符号, 将符号栈中的元素依次出栈并输出, 直到 遇到比当前符号优先级更低的符号或者”(“。 将当前符号入栈。
		 3 扫描完后, 将栈中剩余符号依次输出
		 */
		List<Token> targetTokens = new ArrayList<>();
		TokenParser tokenParser = new TokenParser();
		List<Token> sourceTokes = tokenParser.parse(expr);
		Stack<Token> operStack = new Stack<Token>();
		for (Token token : sourceTokes) {
			if (token.isNumber()) {
				targetTokens.add(token);
			} else {
				//左括号
				if (token.isLeftBracket()) {
					operStack.push(token);
				//右括号
				} else if (token.isRightBracket()) {
					while (!operStack.isEmpty()) {
						Token t = operStack.peek();
						if (t.isLeftBracket()) {
							operStack.pop();
							break;
						} else {
							targetTokens.add(operStack.pop());
						}
					}
				//普通运算符
				} else {
					Operator oper = token.getOperator();
					if (!operStack.isEmpty()) {
						Token t = operStack.peek();
						while (!t.isLeftBracket() && !oper.hasHigherPriority(t.getOperator()) && !operStack.isEmpty()) {
							t = operStack.pop();
							targetTokens.add(t);
						}
					}
					operStack.push(token);
				}
			}
		}
		//将栈中操作符全部输出
		while (!operStack.isEmpty()) {
			targetTokens.add(operStack.pop());
		}
		return targetTokens;
	}
	
	

}
