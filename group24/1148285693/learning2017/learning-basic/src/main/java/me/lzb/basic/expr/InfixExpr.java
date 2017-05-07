package me.lzb.basic.expr;

import java.util.List;
import java.util.Stack;

/**
 * 中序表达式
 * Created by LZB on 2017/4/15.
 */
public class InfixExpr {


    private String expr;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        List<Node> list = CalUtil.processInfixExpr(expr);

        Stack<String> symbolStack = new Stack<>();
        Stack<Float> numberStack = new Stack<>();

        boolean calLevel2 = false;
        for (Node n : list) {
            if (n.isNumber()) {
                numberStack.push(n.number);
                if (calLevel2) {
                    CalUtil.calculate(symbolStack, numberStack, false);
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
            CalUtil.calculate(symbolStack, numberStack, true);
        }


        return numberStack.pop();
    }


}
