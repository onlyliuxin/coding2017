package me.lzb.basic.expr;

import me.lzb.common.utils.StringUtils;

import java.util.Stack;

/**
 * 后缀表达式
 * Created by LZB on 2017/4/20.
 */
public class PostfixExpr {

    private String expr;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        Stack<String> symbolStack = new Stack<>();
        Stack<Float> numberStack = new Stack<>();

        char[] array = this.expr.toCharArray();
        String number = "";
        for (int i = 0; i < array.length; i++) {
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
                    CalUtil.calculate(symbolStack, numberStack, false);
                }
            }
        }
        return numberStack.pop();
    }

}
