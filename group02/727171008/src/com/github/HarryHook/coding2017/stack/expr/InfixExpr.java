package com.github.HarryHook.coding2017.stack.expr;

import com.github.HarryHook.coding2017.basic.MyStack;

public class InfixExpr {

    String expr = null;

    public InfixExpr(String expr) {
	this.expr = expr;
    }

    public float evaluate() {

	char[] ch = expr.toCharArray();
	MyStack stackOfOperator = new MyStack();
	MyStack stackOfNumber = new MyStack();

	for (int i = 0; i < ch.length; i++) {

	    if (Character.isDigit(ch[i])) {
		float tmp = Float.parseFloat("" + ch[i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Float.parseFloat("" + ch[i]);
		}

		stackOfNumber.push(tmp);

	    }
	    if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/') {
		stackOfOperator.push(ch[i]);
	    }

	    char operator = (char) stackOfOperator.peek();
	    if (operator == '*' || operator == '/') {
		float tmp = Float.parseFloat("" + ch[++i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Float.parseFloat("" + ch[i]);
		}
		if (i != ch.length - 1) {
		    i--;
		}
		stackOfNumber.push(tmp);

		float tmp1 = Float.parseFloat("" + stackOfNumber.pop());
		float tmp2 = Float.parseFloat("" + stackOfNumber.pop());
		if (operator == '*') {
		    stackOfNumber.push(tmp1 * tmp2);
		} else {
		    stackOfNumber.push(tmp2 / tmp1);
		}

		stackOfOperator.pop();
	    }

	}
	// 将栈中的数字和运算符逆置，从左往右结合
	reverse(stackOfNumber);
	reverse(stackOfOperator);

	while (!(stackOfOperator.isEmpty())) {
	    char operator = (char) stackOfOperator.peek();
	    if (operator == '+' || operator == '-') {
		float tmp1 = Float.parseFloat("" + stackOfNumber.pop());
		float tmp2 = Float.parseFloat("" + stackOfNumber.pop());
		if (operator == '+') {
		    stackOfNumber.push(tmp1 + tmp2);
		} else {
		    stackOfNumber.push(tmp1 - tmp2);
		}
	    }

	    stackOfOperator.pop();
	}

	return Float.parseFloat("" + stackOfNumber.pop());
    }

    private void reverse(MyStack s) {

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

    public static void main(String[] args) {
	InfixExpr expr = new InfixExpr("2+3*4+5");
	System.out.println(expr.evaluate());
    }
}