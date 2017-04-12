package com.coding.basic.homework_05.stack;

import java.util.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void  reverse(Stack s) {
		if(s.isEmpty()){
			return;
		}
		Object object = s.pop();
		reverse(s);
		addToBottom(s, object);
	}
	/**
	 * 将Object放入到当前栈的底部
	 * @param s
	 * @param object
	 */
	public static void addToBottom(Stack s, Object object){
		if(s.isEmpty()){
			s.push(object);
			return;
		}
		Object tem = s.pop();
		addToBottom(s, object);
		s.push(tem);
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack ss = new Stack();
		while(!s.isEmpty()){
			Object object = s.peek();
			if(object.equals(o)){
				s.pop();
			}else{
				ss.push(s.pop());
			}
		}
		while(!ss.isEmpty()){
			s.push(ss.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Object[] result;
		Stack ss = new Stack();
		if(s.size() <= len){
			result = new Object[s.size() + 1];
			for(int i = 0; i < s.size(); i++){
				result[i] = s.peek();
				ss.push(s.pop());
			}
		}else{
			result = new Object[len + 1];
			for(int i = 0; i < s.size(); i++){
				if(i < len){
					result[i] = s.peek();
				}
				ss.push(s.pop());
			}
		}
		while(!ss.isEmpty()){
			s.push(ss.pop());
		}
		return result;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){
		Stack stack = new Stack();
		for(int i = 0; i<s.length(); i++ ){
			char c = s.charAt(i);
			if(c == '(' || c == '[' || c == '{'){
				stack.push(c);
			}else if(c == ')'){
				char c2 = (char) stack.pop();
				if(c2 != '('){
					return false;
				}
			}else if(c == ']'){
				char c2 = (char) stack.pop();
				if(c2 != '['){
					return false;
				}
			}else if(c == '}'){
				char c2 = (char) stack.pop();
				if(c2 != '{'){
					return false;
				}
			}
		}
		return stack.size() == 0;
	}
	
	
}