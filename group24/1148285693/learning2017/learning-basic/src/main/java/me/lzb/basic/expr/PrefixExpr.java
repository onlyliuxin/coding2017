package me.lzb.basic.expr;

import me.lzb.common.utils.StringUtils;

import java.util.Stack;

/**
 * 前缀表达式
 * Created by LZB on 2017/4/20.
 */
public class PrefixExpr {

    private String expr;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        Stack<String> symbolStack = new Stack<>();
        Stack<Float> numberStack = new Stack<>();

        char[] array = this.expr.toCharArray();
        String number = "";
        for (int i = array.length - 1; i >= 0; i--) {
            if (Character.isDigit(array[i])) {
                number = number + String.valueOf(array[i]);
            } else {
                if (StringUtils.isNotBlank(number)) {
                    numberStack.push(Float.valueOf(number));
                    number = "";
                }
                if (Character.isSpaceChar(array[i])) {


                } else {
                    symbolStack.push(String.valueOf(array[i]));
                    CalUtil.calculate(symbolStack, numberStack, true);
                }
            }
        }
        return numberStack.pop();
    }

}
