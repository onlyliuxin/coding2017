package com.coding.basic.stack.expr;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	Map<Character, Integer> priority = null;

	public InfixExpr(String expr) {
		this.expr = expr;
		this.priority = new HashMap<>();
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
	}

	public float evaluate() {
		char[] exprs = expr.toCharArray();
		Stack<Float> num = new Stack<>();
		Stack<Character> oper = new Stack<>();
		float sum = 0;
		int len = exprs.length;
		String number="";
		for (int i = 0; i < len;) {
			char tmp = exprs[i];
			if (tmp <= '9' && tmp >= '0'){
				number+=tmp;
				if(i==len-1||exprs[i+1]<'0'||exprs[i+1]>'9'){
					num.add(Float.parseFloat(number));
					number="";
				}
				i++;
			}
			else if (tmp == '+' || tmp == '-' || tmp == '*' || tmp == '/') {
				int pri = 1;
				if (!oper.isEmpty()) {
					pri = priority.get(oper.peek());
				}
				int curpri = priority.get(tmp);
				if (curpri <= pri){
					oper.add(tmp);
					i++;
				}
				else {
					float num1;
					float num2;
					num1 = num.pop();
					number="";
					i++;
					while(i<len&&exprs[i]>='0'&&exprs[i]<='9'){
						number+=exprs[i];
						i++;
					}
					num2 =(float)Integer.parseInt(number);
					number="";
					num.add(compute(tmp, num1, num2));
				}
			} else
				throw new IllegalArgumentException("Not supported operater");
		}
		Stack<Float>numtmp=new Stack<>();
		while(!num.isEmpty()){
			numtmp.add(num.pop());
		}
		Stack<Character>opertmp=new Stack<>();
		while (!oper.isEmpty()) {
			opertmp.add(oper.pop());
			
		}
		while(!opertmp.isEmpty()){
			char tmp=opertmp.pop();
			float num1=numtmp.pop();
			float num2=numtmp.pop();
			numtmp.push(compute(tmp, num1, num2));
			}
		return numtmp.peek();
	}
	private float compute(char op,float num1,float num2){
		switch(op){
		case '+':return num1+num2;
		case '-':return num1-num2;
		case '*':return num1*num2;
		case '/':return num1/num2;
		default:throw new IllegalArgumentException("Not supported operater");
		}
	}

}
