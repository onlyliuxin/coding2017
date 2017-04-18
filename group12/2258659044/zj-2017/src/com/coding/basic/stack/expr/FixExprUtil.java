package com.coding.basic.stack.expr;

import com.coding.basic.List;
import com.coding.basic.array.ArrayList;

public class FixExprUtil {

	/**
	 * 运算
	 * @param a 数字
	 * @param oper 运算符
	 * @param b 数字
	 * @return
	 */
	public static  float calculate(float a,Operator oper,float b) {

		String operFlag = oper.getFlag();

		float res = 0f;
		if (Operator.ADD.getFlag().equals(operFlag)) {
			res = a + b;
		} else if (Operator.SUB.getFlag().equals(operFlag)) {
			res = a - b;
		} else if (Operator.MULTY.getFlag().equals(operFlag)) {
			res = a * b;
		} else if (Operator.DIVIDE.getFlag().equals(operFlag)) {
			res = a / b;
		}
		return res;
	} 
	
	/**
	 * 将字符串顺序逆置
	 * @param str
	 * @return
	 */
	public static String reverse(String expr){
		
		return new StringBuffer(expr).reverse().toString();		
	}
	
	/**
	 * 将表达式字符串转换为List
	 * @param expr
	 * @return
	 */
	public static List<String> FixExprToArray(String expr){
		
		List<String> ls = new ArrayList<>();
			
		String[] strArr = expr.split(" ");
		for (String str : strArr) {
			parse(str,ls);
		}
		
		return  ls;
	}
	
	public static void parse(String str,List<String> ls){
		
		char[] chr = str.toCharArray();		
		StringBuilder token = new StringBuilder();

		for (char c : chr) {
			if(Operator.contains(c+"")){
				if(!"".equals(token.toString())){
					ls.add(token.toString());
					token = new StringBuilder();
				}
				ls.add(c+"");								
			}else{
				token.append(c);
			}
		}
		if(!"".equals(token.toString())){
			ls.add(token.toString());
		}

	}
	
}
