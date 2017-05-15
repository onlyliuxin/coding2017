package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class InfixExpr {

    private static List<Character> signs = Lists.newArrayList('+', '-', '*', '%', '/', '(', ')');

    private String expr;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        char[] chars = expr.toCharArray();
        return calculate(parse(chars));
    }

    private List<String> parse(char[] chars) {
        List<String> orderList = Lists.newArrayList();
        StringBuilder tmp = new StringBuilder();
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                tmp.append(aChar);
                continue;
            }
            if (tmp.length() > 0) {
                orderList.add(tmp.toString());
                tmp = new StringBuilder();
            }
            if (signs.contains(aChar)) {
                orderList.add(aChar + "");
            }
        }
        orderList.add(tmp.toString());
        return reconfiguration(orderList);
    }

    private List<String> reconfiguration(List<String> orderList) {
        List<String> result = Lists.newArrayList();
        Stack<String> stack = new Stack<String>();
        for (String exp : orderList) {
            if (StringUtils.isNumeric(exp)) {
                result.add(exp);
                continue;
            }
            switch (exp) {
            case "(":
                stack.push(exp);
                break;
            case ")":
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                while (!stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                stack.pop();
                break;
            default:
                // 前面的符号是否可以跟后面的符号互换位置
                while (!stack.isEmpty() && compare(stack.peek(), exp)) {
                    result.add(stack.pop());
                }
                stack.push(exp);
                break;
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static boolean compare(String preExp, String exp) {
        switch (preExp) {
        case "+":
            return "+".equals(exp) || "-".equals(exp);
        case "-":
            return "+".equals(exp) || "-".equals(exp);
        case "*":
            return "/".equals(exp) || "*".equals(exp) || "+".equals(exp) || "-".equals(exp);
        case "/":
            return "/".equals(exp) || "*".equals(exp) || "+".equals(exp) || "-".equals(exp);
        default:
            throw new IllegalArgumentException();
        }
    }

    private Float calculate(List<String> orderList) {
        Stack<Float> stack = new Stack<Float>();
        for (String order : orderList) {
            if (StringUtils.isNumeric(order)) {
                stack.push(Float.parseFloat(order));
            } else {
                Float back = (Float) stack.pop();
                Float front = (Float) stack.pop();
                Float res = 0.0f;
                switch (order.charAt(0)) {
                case '+':
                    res = front + back;
                    break;
                case '-':
                    res = front - back;
                    break;
                case '*':
                    res = front * back;
                    break;
                case '/':
                    res = front / back;
                    break;
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

}
