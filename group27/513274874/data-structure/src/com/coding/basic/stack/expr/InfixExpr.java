package com.coding.basic.stack.expr;

import com.coding.basic.stack.StackUtil;

import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		

		char[] chars = expr.toCharArray();
		System.out.println(chars);

		Stack numStack = new Stack();
		Stack operStack = new Stack();
		//当前数，有可能是操作数，有可能是运算符
		String curr = "";
		OperIterator operIterator = new OperIterator(chars);
		int num = operIterator.nextNumber();
		numStack.push(num);
		while(operIterator.hasNext()){
			int numB = 0;
			char oper = operIterator.nextOperator();
			switch (oper){
				case '+':
				case '-':
					operStack.push(oper);
					numStack.push(operIterator.nextNumber());
					continue;
				case '*':
					numB = operIterator.nextNumber();
					numStack.push(Double.parseDouble(numStack.pop() + "") * numB);
					continue;
				case '/':
					numB = operIterator.nextNumber();
					numStack.push(Double.parseDouble(numStack.pop()+"") / numB);
					continue;
			}
		}

		//清算站内数据
		if(operStack.isEmpty()) return Float.parseFloat(numStack.pop() + "");

		StackUtil.reverse(operStack);
		StackUtil.reverse(numStack);
		while(!operStack.isEmpty()){
			char oper = (char)operStack.pop();

			numStack.push(operate(Float.parseFloat(numStack.pop()+""),Float.parseFloat(numStack.pop() +""),oper));
		}

		return (float) numStack.pop();
	}
	
	private float operate(float a,float b,char oper){
		switch (oper){
			case '-':
				return a-b;
			case '+':
				return a+b;
			case '*':
				return a*b;
			case '/':
				return a/b;
		}
		return 0.00f;
	}

	class OperIterator {
		private char[] expr ;
		private int pos = 0;

		public OperIterator(char[] expr) {
			this.expr = expr;
		}

		public char nextOperator(){
			return expr[pos++];
		}
		public int nextNumber(){
			StringBuffer num = new StringBuffer("");
			while(pos <= expr.length-1 && expr[pos] != '+' && expr[pos] != '-' && expr[pos] != '*' && expr[pos] != '/'){
				num.append(expr[pos++]);
			}
			return Integer.parseInt(num.toString());
		}

		public boolean hasNext(){
			return pos < expr.length-1;
		}
	}
	
}
