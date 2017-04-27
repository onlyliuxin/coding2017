package com.coding2017.basic.stack.expr;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;

public class InfixExpr {
    private static final CharMatcher CHAR_MATCHER = CharMatcher.anyOf(Operator.allOperator());

    private Stack<String> operatorStack = new Stack<>();
    private Stack<Float> numberStack = new Stack<>();

    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<String> list = splitByOperator(expr);
        for (String s : list) {
            if (Operator.isOperator(s)) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(s);
                } else {
                    while (true) {
                        String op = operatorStack.peek();
                        if (Operator.opOf(op).getPriority() >= Operator.opOf(s).getPriority()) {
                            calculateOnce();
                        } else {
                            break;
                        }
                        if (operatorStack.isEmpty()) {
                            break;
                        }
                    }
                    operatorStack.push(s);
                }
            } else {
                numberStack.push(Float.parseFloat(s));
            }
        }
        while (!operatorStack.isEmpty()) {
            calculateOnce();
        }
        if (numberStack.isEmpty() || numberStack.size() != 1) {
            throw new RuntimeException("expr error");
        }
        return numberStack.pop();
    }

    private void calculateOnce() {
        String operator = operatorStack.pop();
        Float secondNumber = numberStack.pop();
        Float firstNumber = numberStack.pop();
        Float calculate = calculate(firstNumber, secondNumber, operator);
        numberStack.push(calculate);
    }

    private Float calculate(Float firstNumber, Float seconfNumber, String operator) {
        if (Operator.ADD.getOp().equals(operator)) {
            return firstNumber + seconfNumber;
        } else if (Operator.MINUTE.getOp().equals(operator)) {
            return firstNumber - seconfNumber;
        } else if (Operator.MULTIPLY.getOp().equals(operator)) {
            return firstNumber * seconfNumber;
        } else if (Operator.DIVIDE.getOp().equals(operator)) {
            return firstNumber / seconfNumber;
        }
        return null;
    }

    private List<String> splitByOperator(String expr) {
        int pos = 0;
        List<String> list = Lists.newArrayList();
        while (pos < expr.length()) {
            int index = CHAR_MATCHER.indexIn(expr, pos);
            if (index < 0) {
                list.add(expr.substring(pos).trim());
                pos = expr.length();
            } else {
                list.add(expr.substring(pos, index).trim());
                list.add(expr.substring(index, index + 1));
                pos = index + 1;
            }
        }
        return list;
    }

    enum Operator {
        ADD("+", 1), MINUTE("-", 1), MULTIPLY("*", 2), DIVIDE("/", 2);

        private String op;
        private int priority;

        Operator(String op, int priority) {
            this.op = op;
            this.priority = priority;
        }

        public static Operator opOf(String op) {
            for (Operator operator : values()) {
                if (operator.getOp().equals(op)) {
                    return operator;
                }
            }
            return null;
        }

        public static boolean isOperator(String op) {
            for (Operator operator : values()) {
                if (operator.getOp().equals(op)) {
                    return true;
                }
            }
            return false;
        }

        public static String allOperator() {
            return Arrays.stream(values()).map(Operator::getOp).collect(Collectors.joining());
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

    }

}
