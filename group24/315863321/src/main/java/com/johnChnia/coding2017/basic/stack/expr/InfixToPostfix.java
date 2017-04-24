package com.johnChnia.coding2017.basic.stack.expr;


import com.johnChnia.coding2017.basic.ArrayList;
import com.johnChnia.coding2017.basic.List;
import com.johnChnia.coding2017.basic.stack.Stack;


/***
 * Rule:
 *      1、如果栈不为空且操作符的优先级比栈顶高且不是（、【、{，则弹出栈顶，并且放到Expr中
 *      2、如果为（、[、{ 则压入栈顶
 *      3、如果为）、]、}且栈不为空，且栈顶不为（、[、{，则弹出栈顶，并且放到Expr中,最后弹出（、[、{
 *      4、遍历剩下的operator，加入到Expr中
 */
public class InfixToPostfix {

    public static List<Token> convert(String expr) {
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(expr);
        List<Token> list = new ArrayList<>();
        Stack<Token> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.isNumber()) {
                list.add(token);
            } else if (token.isOperator()) {
                while (!stack.empty()
                        && !token.isOpeningParentheses()
                        && !token.hasHigherPriority(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.push(token);
            } else if (token.isOpeningParentheses()) {
                stack.push(token);
            } else if (token.isClosingParentheses()) {
                while (!stack.empty() && !stack.peek().isOpeningParentheses()) {
                    list.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

}
