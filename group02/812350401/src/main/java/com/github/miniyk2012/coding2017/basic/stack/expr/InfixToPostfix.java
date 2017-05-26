package com.github.miniyk2012.coding2017.basic.stack.expr;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Shunting-yard algorithm
 * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 初始化一个运算符栈和一个输出队列
 不断读取token
     如果token是一个数
        将其放入输出队列
     如果token是一个运算符o1
        while栈顶部有运算符o2:
            如果o1的优先级小于等于o2，把o2取出放到输出队列
        最后push(o1)
 将栈中的操作符逐个出栈放到输出队列
 */
public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
        TokenParser parser = new TokenParser();
        List<Token> tokens  = parser.parse(expr);
        Stack<Token> operatorStack = new Stack<>();
		List<Token> outputTokens = new LinkedList<>();
        for (Token token: tokens) {
            if (token.isNumber()) {
                outputTokens.add(token);
            } else {
                while (true) {
                    try {
                        Token o2 = operatorStack.peek();
                        if (!token.hasHigherPriority(o2)) {
                            operatorStack.pop();
                            outputTokens.add(o2);
                        } else {
                            break;
                        }
                    } catch (EmptyStackException e) {
                        break;
                    }
                }
                operatorStack.push(token);
            }
        }
        while (!operatorStack.isEmpty()) {
            outputTokens.add(operatorStack.pop());
        }
		return outputTokens;
	}
}
