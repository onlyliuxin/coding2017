package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;

import java.util.HashMap;

public class InfixExpr {
    String expr = null;
    char[] chars;
    Stack opStack;
    Stack numStack;
    private HashMap<Character, Integer> level = new HashMap<>();

    int pos;

    public InfixExpr(String expr) {
        this.expr = expr;


        this.level.put('+', 0);
        this.level.put('-', 0);
        this.level.put('*', 1);
        this.level.put('/', 1);


        this.opStack = new Stack();
        this.numStack = new Stack();
        this.pos = 0;
    }

    public float evaluate() {
        chars = expr.toCharArray();
        while (this.pos < chars.length) {
            if (isOp(chars[pos])) {
                if (opStack.isEmpty()) {
                    opStack.push(chars[pos]);
                    pos++;
                } else {
                    //TODO analyze
                    char op = (char) opStack.pop();
                    if (level.get(chars[pos]) <= level.get(op)) {

                        int c = this.caculate(op);
                        numStack.push(c);
                        opStack.push(chars[pos]);
                        pos++;


                    }else {
                        opStack.push(op);
                        opStack.push(chars[pos]);
                        pos++;
                    }
                }
            } else {
                int num = this.getNumber();
                numStack.push(num);

            }
        }

        while(!opStack.isEmpty()){
            char op = (char) opStack.pop();

            int c = this.caculate(op);
            numStack.push(c);
       }

        return (int)numStack.pop();
    }

    private boolean isOp(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;

            default:
                return false;
        }
    }

    private int getNumber() {
        int num = Integer.parseInt("" + chars[pos]);
        pos++;
        while ((pos < chars.length)&&(!isOp(chars[pos])) ) {
            num *= 10;
            num += Integer.parseInt("" + chars[pos]);
            pos++;
        }
        return num;
    }

    private int caculate(char c) {
        int b = (int) numStack.pop();
        int a = (int) numStack.pop();
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }
}

