package com.github.wdn.coding2017.basic.stack.expr;

import com.github.wdn.coding2017.basic.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class InfixExpr {
    private String expr;
    private static Map<String,Integer> priorityMap = new HashMap();
    static{
        priorityMap.put("+",1);
        priorityMap.put("-",1);
        priorityMap.put("*",2);
        priorityMap.put("/",2);
    }
    public InfixExpr(String expr) {
        this.expr = expr;
    }
    public float calculate(float a,float b,String operator) throws IllegalAccessException {
        float result;
        switch (operator) {
            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "*":
                result = a*b;
                break;
            case "/":
                result = a/b;
                break;
            default:
                throw new IllegalAccessException();
        }
        return result;
    }
    public float evaluate() {
        try {
            String[] numArr = expr.split("[+|\\-|*|/]");
            String[] operatorArr = expr.split("\\d+\\d*");
            Object[] operators = Arrays.stream(operatorArr).filter(x -> !"".equals(x.trim())).toArray();
            Stack numStack = new Stack();
            Stack operatorStack = new Stack();
            numStack.push(numArr[0]);
            for (int i = 0; i < operators.length; i++) {
                int number = Integer.parseInt(numArr[i + 1]);
                String operator = operators[i].toString();
                if (!operatorStack.isEmpty() && priorityMap.get(operatorStack.peek()) < priorityMap.get(operator)) {
                    float currentResult = calculate(Integer.parseInt(numStack.pop().toString()), number, operator);
                    numStack.push(currentResult);
                } else if(!operatorStack.isEmpty() && priorityMap.get(operatorStack.peek()) >= priorityMap.get(operator)){
                    float b = Float.parseFloat(numStack.pop().toString());
                    float a = Float.parseFloat(numStack.pop().toString());
                    String currentOperator = operatorStack.pop().toString();
                    float result = calculate(a, b, currentOperator);
                    numStack.push(result);
                    numStack.push(number);
                    operatorStack.push(operator);
                }else {
                    numStack.push(number);
                    operatorStack.push(operator);
                }
            }
            while (!operatorStack.isEmpty()) {
                float b = Float.parseFloat(numStack.pop().toString());
                float a = Float.parseFloat(numStack.pop().toString());
                String operator = operatorStack.pop().toString();
                float result = calculate(a, b, operator);
                numStack.push(result);
            }
            return Float.parseFloat(numStack.pop().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        InfixExpr infixExpr = new InfixExpr("2+3*4+5");
        float r = infixExpr.evaluate();
        System.out.println(r);
    }
}
