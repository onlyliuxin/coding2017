package com.coding.basic.stack.expr.util;

import java.util.regex.Pattern;

import com.coding.basic.List;
import com.coding.basic.array.ArrayList;
import com.coding.basic.stack.Stack;

public class FixExprUtil {
	
    private static String LEFTBRACKRT= "(";	
	private static String RIGHTBRACKET= ")";
	
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
	 * 判断字符串是否为数字
	 * 注意：不包括小数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();    
	}
	
	/**
	 * 将中缀表达式装换为后缀表达式
	 * @param expr
	 * @return
	 */
	public static String InfixCovertToPostfix(String expr){
		
    	ExprIterator it = new ExprIterator(expr);   	
		Stack<String> stack = new Stack<>();
		List<String> postFixList = new ArrayList<String>();
		
		while(it.hasNext()){
			
			String element = it.next();
			//数字直接输出
			if(FixExprUtil.isNumeric(element)){
				postFixList.add(element);
			}else if(RIGHTBRACKET.equals(element)){//去除左右括号
				do {
					postFixList.add(stack.pop());
				} while (!LEFTBRACKRT.equals(stack.pop()));
			}else{
				int preLevel = 0;
				int thisLevel = Operator.getLevelByFlag(element);	
				//当栈顶运算符优先级大于本次运算优先级时（左括号除外）出栈至栈顶优先级小于本次运算优先级
				while(preLevel>thisLevel&&preLevel!=3&&!stack.isEmpty()){
					String oprFlag = stack.pop();
					preLevel = Operator.getLevelByFlag(oprFlag);
					postFixList.add(oprFlag);
				}
				stack.push(element);
			}
			
		}
		//将栈中剩余元素出栈
		while(!stack.isEmpty()){
			postFixList.add(stack.pop());
		}
		//格式化输出
		StringBuffer postFix = new StringBuffer();
		for (int i = 0; i < postFixList.size(); i++) {
			postFix.append(postFixList.get(i)+" ");
		}
		return postFix.toString();		
	}
	
	/**
	 * 中缀表达式转前缀表达式
	 * @param expr
	 * @return
	 */
	public static String InfixCovertToPrefix(String expr){
		
		String post = InfixCovertToPostfix(expr);
		return reverse(post);
	}
	
	/**
	 * 后缀表达式转前缀表达式
	 * @param expr
	 * @return
	 */
	public static String postfixCovertToPrefix(String expr){
		return reverse(expr);
	}
	
	/**
	 * 前缀表达式转后缀表达式
	 * @param expr
	 * @return
	 */
	public static String prefixCovertToPostfix(String expr){
		return reverse(expr);
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
			String element = String.valueOf(c);
			if(Operator.contains(element)){
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
