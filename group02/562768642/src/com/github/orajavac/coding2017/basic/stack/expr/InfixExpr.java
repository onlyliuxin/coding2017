package com.github.orajavac.coding2017.basic.stack.expr;

import java.util.HashMap;
import java.util.Map;
import com.github.orajavac.coding2017.basic.stack.Stack;
import com.github.orajavac.coding2017.jvm.util.Util;

public class InfixExpr {
	
	String expr = null;
	
	private final String ADD = "+";
	
	private final String SUB = "-";
	
	private final String MUL = "*";
	
	private final String DIV = "/";
	
	private Map<String,Integer> operator = new HashMap<String,Integer>();
	
	private Stack oper = new Stack();
	
	private Stack num = new Stack();
	
	public InfixExpr(String expr) {
		this.expr = expr;
		this.operator.put(this.ADD,1);
		this.operator.put(this.SUB,1);
		this.operator.put(this.MUL,2);
		this.operator.put(this.DIV,2);
	}

	public float evaluate() {
		Object[] obj = Util.parseOperNumToArray(this.expr);
		String key = null;
		for (int i=0;i<obj.length;i++){
			key = obj[i].toString();
			if (this.operator.containsKey(key)){
				pushOper(key);
			}else{
				num.push(key);
			}
		}
		//处理栈里剩余运算符和数字
		int len = oper.length();
		while (len!=0){
			operation(oper.pop(),num.pop(),num.pop());
			len--;
		}
		//System.out.println("运算式结果: "+Float.parseFloat(num.pop().toString()));
		return Float.parseFloat(num.pop().toString());
	}
	
	public void pushOper(Object key){
		//即将放入运算符与栈顶运算符比较		3-4*5+2
		int y = oper.length();
		for (int i=0;i<y;i++){
			Object op = oper.pop();
			int level1 = this.operator.get(op);
			int level2 = this.operator.get(key);
			if (level1 > level2){	//2+3*4+5	栈顶运算符大于即将入压运算符
				operation(op,num.pop(),num.pop());
			}else if (level1 == level2){	//3-20+2	栈顶运算符等于即将入压运算符
				operation(op,num.pop(),num.pop());
			}else{
				oper.push(op);	//把刚刚弹出，再压入栈
				oper.push(key);
			}
		}
		if (oper.length() == 0){
			oper.push(key);
		}
	}
	
	public void operation(Object oper,Object num1,Object num2){
		Integer result = null;
		if(oper.equals("*")){
			result = Integer.parseInt(num1.toString()) * Integer.parseInt(num2.toString());
			num.push(result);
		}
		if(oper.equals("/")){
			result = Integer.parseInt(num2.toString()) / Integer.parseInt(num1.toString());
			num.push(result);
		}
		if(oper.equals("+")){
			result = Integer.parseInt(num1.toString()) + Integer.parseInt(num2.toString());
			num.push(result);
		}
		if(oper.equals("-")){
			result = Integer.parseInt(num2.toString()) - Integer.parseInt(num1.toString());
			num.push(result);
		}
	}
}
