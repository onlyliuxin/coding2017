package com.aaront.exercise.basic.expr;

import java.util.ArrayList;
import java.util.List;

public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<String> elements = toList(expr.split(" "));
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/")) {
                Double result = calc(element, Double.valueOf(elements.get(i - 2)), Double.valueOf(elements.get(i - 1)));
                elements.add(i - 2, result.toString());
                for (int j = 0; j < 3; j++) {
                    elements.remove(i - 1);
                }
                i = -1;
            }
        }
        return Float.valueOf(elements.get(0));
    }

    private List<String> toList(String[] elements) {
        List<String> list = new ArrayList<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        return list;
    }

    private Double calc(String operator, Double operand1, Double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("不支持的运算符");
        }
    }
}
