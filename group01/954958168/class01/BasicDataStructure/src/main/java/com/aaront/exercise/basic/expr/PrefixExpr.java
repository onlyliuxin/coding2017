package com.aaront.exercise.basic.expr;

import java.util.ArrayList;
import java.util.List;

public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<String> elements = toList(expr.split(" "));
        for (int i = elements.size() - 1; i >= 0; i++) {
            String element = elements.get(i);
            if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/")) {
                Double result = calc(element, Double.valueOf(elements.get(i + 1)), Double.valueOf(elements.get(i + 2)));
                elements.add(i, result.toString());
                for (int j = 0; j < 3; j++) {
                    elements.remove(i + 1);
                }
                i = elements.size() - 2;
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
