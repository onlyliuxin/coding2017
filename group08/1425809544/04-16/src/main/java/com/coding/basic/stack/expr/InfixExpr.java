package com.coding.basic.stack.expr;

import java.util.Stack;

/**
 *@Description 参考他人
 * @author xyy
 * @create 2017-04-14 17:44
 **/
public class InfixExpr {

    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        constructStacks();

        calculateWithStacks();

        return 0.0f;
    }


    private void calculateWithStacks() {

        while (!operatorStack.isEmpty()) {
            operandStack.push(processCalc((Float) operandStack.pop()));
        }


    }

    private float processCalc(Float arg2) {
        if (operandStack.isEmpty() || operatorStack.isEmpty()) {
            throw new RuntimeException();
        }

        Float arg1 = (Float) operandStack.pop();
        char operator = ((Character) (operatorStack.pop())).charValue();
        switch (operator) {
            case '+':
                return arg1 + arg2;
            case '-':
                return arg1 - arg2;
            case '*':
                return arg1 * arg2;
            case '/':
                return arg1 / arg2;
            default:
                throw new RuntimeException();

        }

    }
    private void constructStacks() {

        int operandStart = 0;
        int len = expr.length();
        for (int i = 0; i < len; i++) {
            char exprChar = expr.charAt(i);//取字符
            if (isOperator(exprChar)) {
                onNewOperandFound(exprChar, Float.parseFloat(expr.substring(operandStart, i)));
                operatorStack.push(exprChar);
                operandStart = i + 1;
            }
        }
        onNewOperandFound(null, Float.parseFloat(expr.substring(operandStart)));


    }

    private void onNewOperandFound(Character exprChar, Float newOperand) {
        newOperand = shiftOperator(newOperand);
        while (needToProcessCalcWithHigherPriority(exprChar)) {
            newOperand = processCalc(newOperand);
            operandStack.push(newOperand);
            newOperand = null;
        }
        if (newOperand != null) {
            operandStack.push(newOperand);
        }

    }

    private boolean needToProcessCalcWithHigherPriority(Character operator) {
        if (operator == null || operatorStack.isEmpty()) {
            return false;
        }
        Character lastOperatorInStack = (Character) (operatorStack.peek());
        if ((operator == '+' || operator == '-') &&
                (lastOperatorInStack == '*' || lastOperatorInStack == '/')) {
            return true;
        } else {
            return false;
        }


    }

    private float shiftOperator(float newOperand) {

        if (operatorStack.isEmpty()) {
            return newOperand;
        }
        Character lastOperatorInStack = (Character) (operatorStack.pop());

        switch (lastOperatorInStack.charValue()) {
            case '-':
                operatorStack.push('+');
                return newOperand * (-1);
            case '/':
                operatorStack.push('*');
                return 1 / newOperand;
            default:
                operatorStack.push(lastOperatorInStack);
                return newOperand;
        }


    }


    Stack operatorStack = new Stack();
    Stack operandStack = new Stack();


    private boolean isOperator(char exprChar) {

        if ('+' == exprChar || '-' == exprChar || '*' == exprChar || '/' == exprChar) {
            return true;
        } else {
            return false;
        }

    }


}
