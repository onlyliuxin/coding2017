package me.lzb.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by LZB on 2017/4/15.
 */
public class InfixExpr {


    private String expr;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        List<Node> list = processExpr();

        Stack<String> symbolStack = new Stack<>();
        Stack<Float> numberStack = new Stack<>();

        boolean calLevel2 = false;
        for (Node n : list) {
            if (n.isNumber) {
                numberStack.push(n.number);
                if (calLevel2) {
                    calculate(symbolStack, numberStack, false);
                    calLevel2 = false;
                }
            } else {
                symbolStack.push(n.symbol);

                if (n.isLevel2()) {
                    calLevel2 = true;
                }
            }
        }


        Stack<Float> tn = new Stack<>();
        int nsize = numberStack.size();
        for (int i = 0; i < nsize; i++) {
            tn.push(numberStack.pop());
        }

        numberStack = tn;


        Stack<String> ts = new Stack<>();
        int ssize = symbolStack.size();
        for (int i = 0; i < ssize; i++) {
            ts.push(symbolStack.pop());
        }

        symbolStack = ts;


        while (!symbolStack.isEmpty()) {
            calculate(symbolStack, numberStack, true);
        }


        return numberStack.pop();
    }



    private List<Node> processExpr() {
        List<Node> list = new ArrayList<>();
        char[] array = this.expr.toCharArray();
        String number = "";
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                number = number + String.valueOf(array[i]);
            } else {
                Node num = new Node(Float.valueOf(number), null, true, -1);
                number = "";
                int calLevel = "+-".indexOf(array[i]) >= 0 ? 1 : 2;
                Node sym = new Node(0, String.valueOf(array[i]), false, calLevel);
                list.add(num);
                list.add(sym);
            }
        }

        Node num = new Node(Float.valueOf(number), null, true, -1);
        list.add(num);
        return list;
    }


    private void calculate(Stack<String> symbolStack, Stack<Float> numberStack, boolean isRe) {
        if (symbolStack.isEmpty()) {
            return;
        }


        String symbole = symbolStack.pop();

        float right;
        float left;

        if(isRe){
            left = numberStack.pop();
            right = numberStack.pop();
        }else {
            right = numberStack.pop();
            left = numberStack.pop();
        }



        float r = calculate(symbole, left, right);

        numberStack.push(r);
    }


    private float calculate(String symbol, float left, float right) {
        if ("+".equals(symbol)) {
            return left + right;
        }

        if ("-".equals(symbol)) {
            return left - right;
        }

        if ("*".equals(symbol)) {
            return left * right;
        }

        if ("/".equals(symbol)) {
            return left / right;
        }

        return 0;
    }


    private class Node {
        float number;
        String symbol;
        boolean isNumber;
        int calLevel;//加减1，乘除2

        public Node(float number, String symbol, boolean isNumber, int calLevel) {
            this.number = number;
            this.symbol = symbol;
            this.isNumber = isNumber;
            this.calLevel = calLevel;
        }

        private boolean isLevel2() {
            return calLevel == 2;
        }
    }

}
