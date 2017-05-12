package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrefixExpr {
    String expr = null;
    int index;
    ArrayList operator = new ArrayList();
    Stack numStack = new Stack();

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        operator.add('+');
        operator.add('-');
        operator.add('*');
        operator.add('/');
        char[] exprs = this.expr.toCharArray();
        this.index = exprs.length - 1;

        while (index >= 0) {
            if (operator.contains(exprs[index])) {
                switch (exprs[index]) {
                    case '+':
                        add();
                        break;
                    case '-':
                        subtract();
                        break;
                    case '*':
                        multiply();
                        break;
                    case '/':
                        divide();
                        break;

                }
                this.index--;
            } else if (exprs[index] == ' ') {
                this.index--;
            } else {
                int num = getNum(exprs);
                numStack.push(num);
            }

        }
        int result = (int) numStack.pop();
        return result;

    }

    private int getNum(char[] exprs) {

        int num = exprs[index] - '0';
        this.index--;
        int i = 10;
        while (exprs[index] >= '0' && exprs[index] <= '9') {

            int temp = exprs[index] - '0';
            temp *= i;
            num += temp;
            i *= 10;
            this.index--;
        }
        return num;
    }

    private void add() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(a + b);
    }

    private void subtract() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(a - b);
    }

    private void multiply() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(b * a);
    }

    private void divide() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(a / b);
    }


}
