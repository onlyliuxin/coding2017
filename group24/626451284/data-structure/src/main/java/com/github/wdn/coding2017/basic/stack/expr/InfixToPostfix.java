package com.github.wdn.coding2017.basic.stack.expr;

import com.github.wdn.coding2017.basic.Stack;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/1 0001.
 */
public class InfixToPostfix {
    public static String convert(String expr) {
        //"3*20+12*5-40/2"==100
        //3 20*12 5*+ 40 2/-
        try {
            String[] numArr = expr.split("[+|\\-|*|/]");
            String[] operatorArr = expr.split("\\d+\\d*");
            Object[] operators = Arrays.stream(operatorArr).filter(x -> !"".equals(x.trim())).toArray();
            Stack numStack = new Stack();
            Stack operatorStack = new Stack();
            numStack.push(numArr[0]);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < operators.length; i++) {
                int number = Integer.parseInt(numArr[i + 1]);
                String operator = operators[i].toString();
                if (!operatorStack.isEmpty() && Operator.compare(operatorStack.peek().toString(),operator)<0) {
                    String currentResult = numStack.pop()+" "+number+operator;
                    numStack.push(currentResult);
                } else if(!operatorStack.isEmpty() && Operator.compare(operatorStack.peek().toString(),operator)>=0){
                    Object b = numStack.pop();
                    Object a = numStack.pop();
                    String currentOperator = operatorStack.pop().toString();
                    String result = a+" "+b+currentOperator;
                    numStack.push(result);
                    numStack.push(number);
                    operatorStack.push(operator);
                }else {
                    numStack.push(number);
                    operatorStack.push(operator);
                }
            }
            while (!operatorStack.isEmpty()) {
                Object b = numStack.pop();
                Object a = numStack.pop();
                String operator = operatorStack.pop().toString();
                String result = a+" "+b+operator;
                numStack.push(result);
            }
            return numStack.pop().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String s = InfixToPostfix.convert("3*20+12*5-40/2");
        System.out.println(s);
    }
}
