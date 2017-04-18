package com.github.HarryHook.coding2017.stack.expr;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

import com.github.HarryHook.coding2017.basic.MyStack;

public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
	this.expr = expr;
    }

    public float evaluate() {

	char[] ch = expr.toCharArray();
	MyStack stackOfOperator = new MyStack();
	MyStack stackOfNumber = new MyStack();
	ArrayList array = new ArrayList();
	for(int i=0; i<ch.length; i++) {
	    if(ch[i] == ' '){
		i++;
	    }
	    if (Character.isDigit(ch[i])) {
		System.out.println("-----"+ch[i]);
		int tmp = Integer.parseInt("" + ch[i]);
		while (i < ch.length - 1 && Character.isDigit(ch[++i])) {
		    tmp = tmp * 10 + Integer.parseInt("" + ch[i]);
		}
		if(i != ch.length-1) {
		    i--;
		}
		array.add(tmp);
	    } else {
		array.add(ch[i]);
	    }
	    
	}
	
	for (int i = 0; i < array.size(); i++) {
	  
	    char operator = (char) array.get(i);
	    if (operator == '+'|| operator == '-' || operator == '*' || operator == '/') {
		stackOfOperator.push(operator);
	    } else {
		stackOfNumber.push(array.get(i));
	    }
	    
	    operator = (char)stackOfOperator.peek();
	    //如果栈顶元素是'*','/'还需判断下个字符是不是'*','/'
	    if (!(stackOfOperator.isEmpty()) && operator == '*' || operator == '/') {
		operator = (char) array.get(++i);
		if(operator == '*') {
		    stackOfOperator.push(ch[i]);
		} else {
		    i--;
		}
		if(operator == '/') {
		    stackOfOperator.push(ch[i]);
		} else {
		    i--;
		}
		stackOfNumber.push(array.get(++i));
		stackOfNumber.push(array.get(++i));
		
		float tmp1 = Float.parseFloat("" + stackOfNumber.pop());
		float tmp2 = Float.parseFloat("" + stackOfNumber.pop());
		
		if((char)stackOfOperator.peek() == '*') {
		    stackOfNumber.push(tmp1 * tmp2);
		} 
		if((char)stackOfOperator.peek() == '/'){
		    stackOfNumber.push(tmp2 / tmp1);  
		}
		
		stackOfOperator.pop();
	    }
	    
	}
	// 将栈中的数字和运算法逆置，从左往右结合
	reverse(stackOfNumber);
	//reverse(stackOfOperator);

	while (!(stackOfOperator.isEmpty())) {
	    if ((char) stackOfOperator.peek() == '+') {
		float tmp1 = Float.parseFloat("" + stackOfNumber.pop());
		float tmp2 = Float.parseFloat("" + stackOfNumber.pop());
		stackOfNumber.push(tmp1 + tmp2);
	    }
	    
	    if ((char) stackOfOperator.peek() == '-') {
		float tmp1 = Float.parseFloat("" + stackOfNumber.pop());
		float tmp2 = Float.parseFloat("" + stackOfNumber.pop());
		stackOfNumber.push(tmp1 - tmp2);
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
	String expr = "-++6/*2 9 3 * 4 2 8";
	PrefixExpr prefixExpr = new PrefixExpr(expr);
	System.out.println(prefixExpr.evaluate());
    }

}