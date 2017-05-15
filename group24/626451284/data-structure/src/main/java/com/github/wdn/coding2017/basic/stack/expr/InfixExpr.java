package com.github.wdn.coding2017.basic.stack.expr;

import com.github.wdn.coding2017.basic.Stack;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class InfixExpr {
    private String expr;

    public InfixExpr(String expr) {
        this.expr = expr;
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
                if (!operatorStack.isEmpty() && Operator.compare(operatorStack.peek().toString(),operator)<0) {
                    float currentResult = Operator.calculate(Integer.parseInt(numStack.pop().toString()), number, operator);
                    numStack.push(currentResult);
                } else if(!operatorStack.isEmpty() && Operator.compare(operatorStack.peek().toString(),operator)>=0){
                    float b = Float.parseFloat(numStack.pop().toString());
                    float a = Float.parseFloat(numStack.pop().toString());
                    String currentOperator = operatorStack.pop().toString();
                    float result = Operator.calculate(a, b, currentOperator);
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
                float result = Operator.calculate(a, b, operator);
                numStack.push(result);
            }
            return Float.parseFloat(numStack.pop().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
