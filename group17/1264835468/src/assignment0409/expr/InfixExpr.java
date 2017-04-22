package assignment0409.expr;

import assignment.Stack;

public class InfixExpr {
    String expr = null;
    int curPos = 0;
    Stack<Character> operatorStack = new Stack<>();
    Stack<Float> numberStack = new Stack<>();

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        while (curPos < expr.length()) {
            char c = expr.charAt(curPos);
            if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (operatorStack.peek() != '(') {
                    popAndCompute();
                }
                operatorStack.pop();
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) && !Operator.of(c).hasHigherPriorityThan(Operator.of(operatorStack.peek()))) {
                    popAndCompute();
                }
                operatorStack.push(c);
            } else if (Character.isDigit(c)) {
                numberStack.push((float) nextNum());
            } else {
                throw new IllegalExpressionException();
            }
            curPos++;
        }
        while (!operatorStack.isEmpty()) {
            popAndCompute();
        }
        if (numberStack.size() == 1) {
            return numberStack.pop();
        }
        throw new IllegalExpressionException();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void popAndCompute() {
        Operator op = Operator.of(operatorStack.pop());
        float num2 = numberStack.pop();
        float num1 = numberStack.pop();
        numberStack.push(op.evaluate(num1, num2));
    }

    private int nextNum() {
        int startPos = curPos;
        int endPos = startPos + 1;
        while (endPos != expr.length() && Character.isDigit(expr.charAt(endPos))) {
            endPos++;
        }
        curPos = endPos - 1;
        return Integer.parseInt(expr.substring(startPos, endPos));
    }
}

