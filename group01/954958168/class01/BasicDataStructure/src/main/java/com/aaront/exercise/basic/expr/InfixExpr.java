package com.aaront.exercise.basic.expr;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixExpr {
    private String expr = null;
    private Map<Character, Integer> map = new HashMap<>(4);

    public InfixExpr(String expr) {
        this.expr = expr;
        this.map.put('+', 1);
        this.map.put('-', 1);
        this.map.put('*', 2);
        this.map.put('/', 2);
        this.map.put('#', 0);
    }

    public float evaluate() {
        Stack<Double> operands = new Stack<>();
        // 要保证操作符栈中的操作符是按照操作符优先级递增的(不能相等)
        Stack<Character> operators = new Stack<>();
        operators.push('#');
        for (int i = 0, len = expr.length(); i < len; i++) {
            char c = expr.charAt(i);
            Integer level = map.get(c);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                Character operator = operators.peek();
                Integer value = map.get(operator);
                while (level <= value) {
                    Double operand2 = operands.pop();
                    Double operand1 = operands.pop();
                    Double result = calc(operators.pop(), operand1, operand2);
                    operands.push(result);
                    operator = operators.peek();
                    value = map.get(operator);
                }

                operators.push(c);
            } else {
                int j = i;
                for (; j < len && expr.charAt(j) >= '0' && expr.charAt(j) <= '9'; j++) ;
                operands.push(Double.parseDouble(expr.substring(i, j)));
                i = j - 1;
            }
        }

        while (operators.size() != 1) {
            Double operand2 = operands.pop();
            Double operand1 = operands.pop();
            Double result = calc(operators.pop(), operand1, operand2);
            operands.push(result);
        }

        return operands.pop().floatValue();
    }

    private Double calc(Character operator, Double operand1, Double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("不支持的运算符");
        }
    }
}
