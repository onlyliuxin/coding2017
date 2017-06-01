package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

/**
 * 中序转后序求值
 *
 * @author xyy
 * @create 2017-04-25 18:54
 **/
public class infixToPostfix {


    public static List<Token> convert(String expr) {
        return null;
    }


    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        String expr = "1+((2+#)*4)-5";

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);//根据索引截取字符串
            if (c == '+') {
                stack.push(c);
            } else if (c == '*') {
                stack.push(c);
            } else if (c == ')') {
                System.out.println(stack.pop() + " ");
            } else if (c == '(') {
                System.out.println("  ");
            } else {
                System.out.println(c + "  ");
            }
        }

        System.out.println("  ");
    }


}
