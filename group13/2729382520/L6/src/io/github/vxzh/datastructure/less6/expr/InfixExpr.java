package io.github.vxzh.datastructure.less6.expr;

import java.util.Stack;

public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        check();

        Stack<Character> operateStack = new Stack<Character>();
        Stack<Integer> numStack = new Stack<Integer>();

        char[] ch = expr.toCharArray();

        for (int i = 0; i < ch.length; i++) {

            if (Character.isDigit(ch[i])) {
                int tmp = Integer.parseInt("" + ch[i]);
                while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
                    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
                }
                numStack.push(tmp);

            }
            if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/') {
                operateStack.push(ch[i]);
            }

            if (!(operateStack.isEmpty()) && (char) operateStack.peek() == '*') {
                int tmp = Integer.parseInt("" + ch[++i]);
                while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
                    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
                }
                if (i != ch.length - 1) {
                    i--;
                }
                numStack.push(tmp);

                int tmp1 = Integer.parseInt("" + numStack.pop());
                int tmp2 = Integer.parseInt("" + numStack.pop());
                numStack.push(tmp1 * tmp2);
                operateStack.pop();

            }
            if (!(operateStack.isEmpty()) && (char) operateStack.peek() == '/') {
                int tmp = Integer.parseInt("" + ch[++i]);
                while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
                    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
                }
                if (i != ch.length - 1) {
                    i--;
                }
                numStack.push(tmp);

                int tmp1 = Integer.parseInt("" + numStack.pop());
                int tmp2 = Integer.parseInt("" + numStack.pop());
                numStack.push(tmp2 / tmp1);
                operateStack.pop();
            }
        }
        // 将栈中的数字和运算法逆置，便于计算
        reverse(numStack);
        reverse(operateStack);

        while (!(operateStack.isEmpty())) {
            if ((char) operateStack.peek() == '+') {
                int tmp1 = Integer.parseInt("" + numStack.pop());
                int tmp2 = Integer.parseInt("" + numStack.pop());
                numStack.push(tmp1 + tmp2);
            }

            if ((char) operateStack.peek() == '-') {
                int tmp1 = Integer.parseInt("" + numStack.pop());
                int tmp2 = Integer.parseInt("" + numStack.pop());
                numStack.push(tmp1 - tmp2);
            }
            operateStack.pop();
        }

        return Float.parseFloat("" + numStack.pop());
    }

    private void reverse(Stack s) {

        if (s.isEmpty()) {
            return;
        }
        // 如果s里面只有一个元素，就返回。具体实现是先pop出来一个，判断剩下的是不是空栈。
        Object tmp1 = s.pop();
        reverse(s);
        if (s.isEmpty()) {
            s.push(tmp1);
            return;
        }
        Object temp2 = s.pop();
        reverse(s);
        s.push(tmp1);
        reverse(s);
        s.push(temp2);

    }

    private boolean check() {
        if (expr.length() <= 0) {
            return false;
        } else if ('+' == expr.charAt(0) || '-' == expr.charAt(0) || '*' == expr.charAt(0) || '/' == expr.charAt(0)) {
            return false;
        } else if ('+' == expr.charAt(expr.length() - 1) || '-' == expr.charAt(expr.length() - 1) || '*' == expr.charAt(expr.length() - 1) || '/' == expr.charAt(expr.length() - 1)) {
            return false;
        }

        return true;
    }

}