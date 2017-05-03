package expr;

import stack.MyStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongxun on 2017/4/22.
 * 遇到数组直接放入集合等待输出
 * 遇到运算符入栈，如果栈顶元素的优先级较待入栈运算符的优先级高，将栈顶元素取出放入集合等待输出，否则直接入栈
 */
public class InfixToPostfix {
    public static List<Token> convert(String expr) {
        List<Token> result = new ArrayList<Token>();
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(expr);
        MyStack<Token> stack = new MyStack<Token>();
        if (tokens != null) {
            for (Token token : tokens) {
                if (token.isNumber())
                    result.add(token);
                else {
                    while (!stack.isEmpty()) {
                        Token prevOper = stack.peek();
                        if (token.hasHigherPriority(prevOper)) {
                            stack.push(token);
                            break;
                        } else {
                            result.add(stack.pop());
                        }
                    }
                    //和+或者-同优先级或者更高优先级的都已经弹栈，只剩空栈
                    if (stack.isEmpty()) stack.push(token);
                }
            }
        }
        //将剩余的所有符号出栈
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }
}
