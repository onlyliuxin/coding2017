package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixExpr {
    String expr = null;
    int count;
    ArrayList operator = new ArrayList();
    Stack numStack = new Stack();

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        operator.add('+');
        operator.add('-');
        operator.add('*');
        operator.add('/');
        this.count = 0;
        char[] exprs = this.expr.toCharArray();



        while (count < exprs.length) {
            if (operator.contains(exprs[count])) {
                switch (exprs[count]) {
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
                this.count++;
            } else if (exprs[count] == ' ') {
                this.count++;
            } else {
                int num = getNum(exprs);
                numStack.push(num);
            }

        }
        int result = (int)numStack.pop();
        return result;
    }

    private int getNum(char[] exprs) {

        int num = exprs[count]-'0';
        this.count++;

        while (exprs[count] >= '0' && exprs[count] <= '9') {

            num *= 10;
            num += exprs[count]-'0';
            this.count++;
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
        numStack.push(b - a);
    }

    private void multiply() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(b * a);
    }

    private void divide() {
        int a = (int) numStack.pop();
        int b = (int) numStack.pop();
        numStack.push(b / a);
    }

}
