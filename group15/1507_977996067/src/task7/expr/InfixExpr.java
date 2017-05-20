package task7.expr;

import org.junit.Assert;
import task5.stack.Stack;

import java.util.Arrays;

public class InfixExpr {

    private String expr;

    private Stack<Float> numberStack = new Stack<>();

    private Stack<Float> resultStack = new Stack<>();

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        Assert.assertNotNull(expr);

        String[] operators = expr.split("[\\d]+");

        int length = operators.length;

        Arrays.stream(expr.split("[+\\-*/]+"))
                .map(Float::parseFloat)
                .forEach(numberStack::push);

        numberStack = reverse(numberStack);

        resultStack.push(numberStack.pop());

        for (int i = 1; i < length; i++) {
            String currentOperator = operators[i];
//            先做乘除,结果放resultStack里面
            switch (currentOperator) {
                case "*":
                    resultStack.push(resultStack.pop() * numberStack.pop());
                    break;
                case "/":
                    resultStack.push(resultStack.pop() / numberStack.pop());
                    break;
                case "+":
                    resultStack.push(numberStack.pop());
                    break;
                case "-":
                    resultStack.push(numberStack.pop());
                    break;
            }
        }

        resultStack = reverse(resultStack);

//        做加减
        for (int i = 1; i < length; i++) {
            String currentOperator = operators[i];
            if ("+".equals(currentOperator)) {
                Float num1 = resultStack.pop();
                Float num2 = resultStack.pop();
                resultStack.push(num1 + num2);
            } else if ("-".equals(currentOperator)) {
                Float num1 = resultStack.pop();
                Float num2 = resultStack.pop();
                resultStack.push(num1 - num2);
            }
        }
        return resultStack.peek();
    }

    private Stack<Float> reverse(Stack<Float> stackToReverse) {
        Stack<Float> temp = new Stack<>();
        while (!stackToReverse.isEmpty())
            temp.push(stackToReverse.pop());
        return temp;
    }
}