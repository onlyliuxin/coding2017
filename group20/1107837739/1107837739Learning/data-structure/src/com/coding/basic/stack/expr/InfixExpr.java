package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;
import java.util.Arrays;

public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public static void main(String[] args) {
        String[] ss = new InfixExpr("3*20+12*5-40/2").getOperatorsAndNumbers("3*20+12*5-40/2");
        System.out.println(Arrays.toString(ss));
    }

    /**
     * 根据输入的表达式，计算结果
     * 只支持 +、-、*、/，不支持括号
     * 数值只支持整数
     *
     * @return 计算结果
     */
    public float evaluate() {
        if (expr == null) {
            throw new NullPointerException("expr can't be null!");
        }

        Stack<String> operators = new Stack<>();
        Stack<Number> nums = new Stack<>();

        String[] operatorsAndNumbers = getOperatorsAndNumbers(expr);

        for (int i = 0; i < operatorsAndNumbers.length; i++) {
            String data = operatorsAndNumbers[i];
            if ("+".equals(data) || "-".equals(data)) {
                operators.push(data);
            }
            // "*" 的优先级最高，遇到 "*" 直接进行计算
            else if ("*".equals(data)) {
                nums.push(nums.pop().floatValue() * Integer.valueOf(operatorsAndNumbers[++i]));
            }
            // "/" 的优先级最高，遇到 "/" 直接进行计算
            else if ("/".equals(data)) {
                nums.push(nums.pop().floatValue() / Integer.valueOf(operatorsAndNumbers[++i]));
            }
            // 如果是数值，判断数值下一位的操作符，如果是 "+" 或 "-"，
            // 则优先级不高于堆栈中存储的操作符，取出堆栈中的进行计算
            else {
                if ((i + 1 < operatorsAndNumbers.length && !nums.isEmpty())
                        &&
                        ("+".equals(operatorsAndNumbers[i + 1]) || "-".equals(operatorsAndNumbers[i + 1]))
                        ) {
                    String operator = operators.pop();
                    float rightValue = Integer.valueOf(data);
                    float leftValue = nums.pop().floatValue();

                    if ("+".equals(operator)) {
                        nums.push(leftValue + rightValue);
                    } else {
                        nums.push(leftValue - rightValue);
                    }
                } else {
                    try {
                        nums.push(Integer.valueOf(data));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("表达式不合法！");
                    }
                }
            }
        }

        if (!operators.isEmpty()) {
            String operator = operators.pop();
            float rightValue = nums.pop().floatValue();
            float leftValue = nums.pop().floatValue();

            if ("+".equals(operator)) {
                nums.push(leftValue + rightValue);
            } else {
                nums.push(leftValue - rightValue);
            }
        }

        return nums.pop().floatValue();
    }

    private String[] getOperatorsAndNumbers(String expr) {
        String[] numberArray = expr.split("\\+|-|\\*|/");
        String[] operatorsArray = expr.split("[0-9]+");

        String[] operatorsAndNumbers = new String[operatorsArray.length + numberArray.length - 1];
        for (int i = 1; i < operatorsArray.length; i++) {
            operatorsAndNumbers[2 * i - 1] = operatorsArray[i];
        }
        for (int i = 0; i < numberArray.length; i++) {
            operatorsAndNumbers[2 * i] = numberArray[i];
        }

        return operatorsAndNumbers;
    }
}
