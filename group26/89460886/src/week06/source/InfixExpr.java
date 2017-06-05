package list.expr;

import list.Stack;
import org.junit.Assert;

/**
 * @author jiaxun
 */
public class InfixExpr {


    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        if (expr == null || expr.length() == 0) return 0.0f;
        Stack numberStack = new Stack();
        Stack symbolStack = new Stack();
        for (int i = 0, len = expr.length(); i < len; i++) {
            char c = expr.charAt(i);
            float num = parseNumber(String.valueOf(c));
            if (num != -1.0f) {
                int nextSymbol = dealNum(numberStack, i);
                i = i + nextSymbol - 1;
                if (nextSymbol == -1) break;
            } else if (expr.charAt(i) == '+') {
                dealAddSub(symbolStack, numberStack);
                symbolStack.push('+');
            } else if (expr.charAt(i) == '-') {
                dealAddSub(symbolStack, numberStack);
                symbolStack.push('-');
            } else if (expr.charAt(i) == '*') {
                int nextSymbol = dealMul(numberStack, i);
                i = i + nextSymbol;
                if (nextSymbol == -1) break;
            } else if (expr.charAt(i) == '/') {
                int nextSymbol = dealDiv(numberStack, i);
                i = i + nextSymbol;
                if (nextSymbol == -1) break;
            } else {
                Assert.assertTrue("no implement", false);
            }
        }
        dealAddSub(symbolStack, numberStack);
        return (float) numberStack.pop();
    }

    private int dealNum(Stack numberStack, int i) {
        String expression = expr.substring(i);
        int nextSymbol = nextSymbol(expression);
        float thisNum = getNumber(nextSymbol, expression);
        numberStack.push(thisNum);
        return nextSymbol;
    }

    private void dealAddSub(Stack symbolStack, Stack numberStack) {
        if (!symbolStack.isEmpty()) {
            char expression = (char) symbolStack.pop();
            float num1 = (float) numberStack.pop();
            float num2 = (float) numberStack.pop();
            float result = 0;
            if (expression == '+') {
                result = num2 + num1;
            } else if (expression == '-') {
                result = num2 - num1;
            } else {
                Assert.assertTrue("no implement", false);
            }
            numberStack.push(result);
        }
    }

    private int dealMul(Stack numberStack, int i) {
        float prev = (float) numberStack.pop();
        String expression = expr.substring(i + 1);
        int nextSymbol = nextSymbol(expression);
        float next = getNumber(nextSymbol, expression);
        float result = prev * next;
        numberStack.push(result);
        return nextSymbol;
    }

    private int dealDiv(Stack numberStack, int i) {
        float prev = (float) numberStack.pop();
        String expression = expr.substring(i + 1);
        int nextSymbol = nextSymbol(expression);
        float next = getNumber(nextSymbol, expression);
        float result = prev / next;
        numberStack.push(result);
        return nextSymbol;
    }

    private float getNumber(int nextSymbol, String expression) {
        float result;
        if (nextSymbol == -1) {
            result = parseNumber(expression);
        } else {
            result = parseNumber(expression.substring(0, nextSymbol));
        }
        return result;
    }

    private float parseNumber(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return -1.0f;
        }
    }

    private int nextSymbol(String expression) {
        int add = expression.indexOf("+");
        int sub = expression.indexOf("-");
        int mul = expression.indexOf("*");
        int div = expression.indexOf("/");
        int result = Math.min(add == -1 ? Integer.MAX_VALUE : add, Math.min(sub == -1 ? Integer.MAX_VALUE : sub,
                Math.min(mul == -1 ? Integer.MAX_VALUE : mul, div == -1 ? Integer.MAX_VALUE : div)));
        return result == Integer.MAX_VALUE ? -1 : result;
    }

}
