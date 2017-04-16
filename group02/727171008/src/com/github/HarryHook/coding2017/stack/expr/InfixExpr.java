package com.github.HarryHook.coding2017.stack.expr;

import com.github.HarryHook.coding2017.basic.MyStack;

public class InfixExpr {

    String expr = null;

    public InfixExpr(String expr) {
	this.expr = expr;
    }

    public float evaluate() {

	char[] ch = expr.toCharArray();
	MyStack stackOfTocken = new MyStack();
	MyStack stackOfNumber = new MyStack();

	for (int i = 0; i < ch.length; i++) {

	    if (Character.isDigit(ch[i])) {
		int tmp = Integer.parseInt("" + ch[i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
		}
	
		stackOfNumber.push(tmp);

	    }
	    if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/') {
		stackOfTocken.push(ch[i]);
	    }

	    if (!(stackOfTocken.isEmpty()) && (char) stackOfTocken.peek() == '*') {
		int tmp = Integer.parseInt("" + ch[++i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
		}
		if (i != ch.length - 1) {
		    i--;
		}
		stackOfNumber.push(tmp);

		int tmp1 = Integer.parseInt("" + stackOfNumber.pop());
		int tmp2 = Integer.parseInt("" + stackOfNumber.pop());
		stackOfNumber.push(tmp1 * tmp2);
		stackOfTocken.pop();

	    }
	    if (!(stackOfTocken.isEmpty()) && (char) stackOfTocken.peek() == '/') {
		int tmp = Integer.parseInt("" + ch[++i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
		}
		if (i != ch.length - 1) {
		    i--;
		}
		stackOfNumber.push(tmp);

		int tmp1 = Integer.parseInt("" + stackOfNumber.pop());
		int tmp2 = Integer.parseInt("" + stackOfNumber.pop());
		stackOfNumber.push(tmp2 / tmp1);
		stackOfTocken.pop();
	    }
	}
	// 将栈中的数字和运算法逆置，便于计算
	reverse(stackOfNumber);
	reverse(stackOfTocken);

	while (!(stackOfTocken.isEmpty())) {
	    if ((char) stackOfTocken.peek() == '+') {
		int tmp1 = Integer.parseInt("" + stackOfNumber.pop());
		int tmp2 = Integer.parseInt("" + stackOfNumber.pop());
		stackOfNumber.push(tmp1 + tmp2);
	    }
	    
	    if ((char) stackOfTocken.peek() == '-') {
		int tmp1 = Integer.parseInt("" + stackOfNumber.pop());
		int tmp2 = Integer.parseInt("" + stackOfNumber.pop());
		stackOfNumber.push(tmp1 - tmp2);
	    }
	    stackOfTocken.pop();
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
}