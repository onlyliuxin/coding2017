package com.github.ipk2015.coding2017.basic.stack.expr;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		int len = expr.length();
		char c,temp;
		Stack stack = new Stack();
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i<len;i++){
			c = expr.charAt(i);
			if(Character.isDigit(c)){
				buffer.append(c);   //数字字符直接放进buffer里
			}else{
				buffer.append(" ");
				if('(' == c){
					stack.push(c);  // ( 时先放进字符栈里
				}else if(')' == c){	// ) 时将栈里到上一个(之间的字符全部压出到buffer里
					temp = (Character)stack.pop();
					while(temp!='('){
						buffer.append(temp);
						temp = (Character)stack.pop();
					};
				}else{		//四个运算符时,
					while(!shouldPush(c,stack)){
						buffer.append((Character)stack.pop());
					}
					stack.push(c);
				}
			}
		}
		while(!stack.isEmpty()){
			buffer.append((Character)stack.pop());
		}
		TokenParser parser = new TokenParser();
		return parser.parse(buffer.toString());
	}
	/*
	 * 比较该运算符与临时栈栈顶指针的运算符的优先级，如果临时栈栈顶指针的优先级大于等于该运算符的优先级，
	 * 弹出并添加到后缀表达式中，反复执行前面的比较工作，直到遇到一个栈顶指针的优先级低于该运算符的优先级，
	 * 停止弹出添加并把该运算符压入栈中。
	 * 此时的比较过程如果出现栈顶的指针为‘（’，则停止循环并把该运算符压入栈中
	 * 
	 */
	private static boolean shouldPush(char c,Stack stack){
		if(stack.isEmpty()){
			return true;
		}
		char peek = (Character)stack.peek();
		if(peek == '('){
			return true;
		}
		if((c == '*' || c == '/') && (peek == '+' || peek == '-')){
			return true;
		}
		return false;
	}
	

}
