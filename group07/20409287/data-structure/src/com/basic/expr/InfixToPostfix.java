package com.basic.expr;

import com.basic.StackUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {

        if (expr == null) throw new RuntimeException("运算表达式为空!");

        /**
         (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
         (2) 从左至右扫描中缀表达式；
         (3) 遇到操作数时，将其压入S2；
         (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
         (4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
         (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
         (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
         (5) 遇到括号时：
         (5-1) 如果是左括号“(”，则直接压入S1；
         (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
         (6) 重复步骤(2)至(5)，直到表达式的最右边；
         (7) 将S1中剩余的运算符依次弹出并压入S2；
         (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。
         */
        Stack<Token> operatorStack = new Stack<>();
        Stack<Token> tmpStack = new Stack<>();
        List<Token> inFixTokens = new TokenParser().parse(expr);
        for (Token token : inFixTokens) {
            if (token.isNumber()) {
                tmpStack.push(token);
            } else if (token.isOperator()) {
                if (operatorStack.isEmpty() || "(".equals(operatorStack.peek().value)
                        || token.hasHigherPriority(operatorStack.peek())) {
                    operatorStack.push(token);
                } else {
                    do {
                        tmpStack.push(operatorStack.pop());
                    } while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek().value)
                            && !token.hasHigherPriority(operatorStack.peek()));
                    operatorStack.push(token);
                }
            } else {
                if ("(".equals(token.value)) {
                    operatorStack.push(token);
                } else {
                    while (!"(".equals(operatorStack.peek().value)) {
                        tmpStack.push(operatorStack.pop());
                    }
                    if ("(".equals(operatorStack.peek().value)) {
                        operatorStack.pop();
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) tmpStack.push(operatorStack.pop());
        Token[] tokens = new Token[tmpStack.size()];
        for (int i = 0; i < tmpStack.size(); i++) {
            tokens[i] = tmpStack.get(i);
        }

		return Arrays.asList(tokens);
	}
	
	

}
