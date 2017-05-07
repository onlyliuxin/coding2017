package me.lzb.basic.expr;

import me.lzb.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by LZB on 2017/4/20.
 */
public class CalUtil {


    public static void calculate(Stack<String> symbolStack, Stack<Float> numberStack, boolean isRe) {
        if (symbolStack.isEmpty()) {
            return;
        }


        String symbole = symbolStack.pop();

        float right;
        float left;

        if (isRe) {
            left = numberStack.pop();
            right = numberStack.pop();
        } else {
            right = numberStack.pop();
            left = numberStack.pop();
        }

        float r = calculate(symbole, left, right);

        numberStack.push(r);
    }


    public static float calculate(String symbol, float left, float right) {
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


    public static List<Node> processInfixExpr(String expr) {
        List<Node> list = new ArrayList<>();
        char[] array = expr.toCharArray();
        String number = "";
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                number = number + String.valueOf(array[i]);
            } else {
                if (StringUtils.isNotBlank(number)) {
                    Node num = new Node(Float.valueOf(number), null, -1);
                    number = "";
                    list.add(num);
                }

                int calLevel = 1;

                if ("*/".indexOf(array[i]) >= 0) {
                    calLevel = 2;
                }

                if ("()".indexOf(array[i]) >= 0) {
                    calLevel = 3;
                }

                Node sym = new Node(0, String.valueOf(array[i]), calLevel);

                list.add(sym);
            }
        }
        if (StringUtils.isNotBlank(number)) {
            Node num = new Node(Float.valueOf(number), null, -1);
            list.add(num);
        }

        return list;
    }


    public static boolean isLowLevel(Node stackTop, Node next) {
        return stackTop.calLevel < next.calLevel;
    }

    public static void main(String[] args) {
        Node n = new Node(0, "*", 2);
        Node m = new Node(0, "-", 1);
        System.out.println(isLowLevel(n, m));
    }
}
