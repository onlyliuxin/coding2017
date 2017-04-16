package com.coding.basic.stack.expr;

import java.util.Stack;

public class InfixExpr {
	String expr = null;
	private Stack<Object> stack = new Stack<>();

	public InfixExpr(String expr) {
		this.expr = expr;
		init();
	}
	public void init(){
		splitOpraAndNum();
	}
	private void splitOpraAndNum() {
		char[] array = expr.toCharArray();
		int mark = 0;
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			int cc = c&0xffff;
			if(cc<48||cc>57){
				if((i-mark)>0){
					String num = expr.substring(mark, i);
					stack.push(Integer.parseInt(num));
				}
			}else{
				mark = i;
			}
		}
	}
	/**
	 * 转换成前序表达式进行计算
	 * @return
	 */
	public float evaluatePre(){
		
		return 0.0f;
	}

	/**
	 * 循环扫描，每次找到最高优先级的操作符，如果是*或者/那么计算这个操作符两边的数字，
	 * 然后利用返回值改变以前的表达式，再次循环
	 * 直到表达式变成只有一个数字的时候停止
	 * @return
	 */
	public float evaluate() {

		return 0.0f;
	}
	
}
