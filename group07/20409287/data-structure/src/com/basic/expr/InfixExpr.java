package com.basic.expr;

import com.basic.Stack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudanxia on 2017/4/11.
 */
public class InfixExpr {

    String expr = null;

    // 优先级
    private Map<String, Integer> operatorMap;

    public InfixExpr(String expr) {
        this.expr = expr;
        operatorMap = new HashMap<>();
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
    }

    private Stack<String> operatorStack = new Stack<>();

    private Stack<String> numStack = new Stack<>();

    private int index = 0;

    public float evaluate() {

        if (expr == null || expr.length() < 3) {
            throw new RuntimeException("运算表达式不合法!");
        }

        Stack<String> operatorStack = new Stack<>();
        Stack<String> numStack = new Stack<>();
        numStack.push(next());
        operatorStack.push(next());

        while (index < expr.length()) {

            String token = next();
            // 如果是数字, 则直接进数字栈
            // 如果是符号, 则判断与符号栈顶之符号的优先级: 栈顶符号优先级小则继续进栈，否则出栈运算并将结果入栈
            if (operatorMap.containsKey(token)) {
                if (operatorMap.get(operatorStack.peek()) < operatorMap.get(token)) {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.isEmpty()) {
                        if (operatorMap.get(operatorStack.peek()) >= operatorMap.get(token))
                            numStack.push(operate(operatorStack.pop(), numStack.pop(), numStack.pop()).toString());
                        else {
                            operatorStack.push(token);
                            break;
                        }
                    }
                    operatorStack.push(token);
                }
            } else {
                numStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            numStack.push(operate(operatorStack.pop(), numStack.pop(), numStack.pop()).toString());
        }
        return Float.valueOf(numStack.pop());
    }

    private Float operate(String operator, String num2, String num1) {

        switch (operator.charAt(0)) {
            case '+':
                return Float.valueOf(num1) + Float.valueOf(num2);
            case '-':
                return Float.valueOf(num1) - Float.valueOf(num2);
            case '*':
                return Float.valueOf(num1) * Float.valueOf(num2);
            case '/':
                return Float.valueOf(num1) / Float.valueOf(num2);
            default:
                throw new RuntimeException("不支持的运算符: " + operator);
        }
    }

    private boolean isNumber(char c) {
        return c >= 48 && c <= 57;
    }

    private String next() {

        if (index == expr.length()) return null;
        if (operatorMap.containsKey(String.valueOf(expr.charAt(index)))) {
            return String.valueOf(expr.charAt(index++));
        }
        StringBuilder num = new StringBuilder();
        while (index < expr.length() && isNumber(expr.charAt(index))) {
            num.append(expr.charAt(index++));
        }
        return num.toString();
    }


}
