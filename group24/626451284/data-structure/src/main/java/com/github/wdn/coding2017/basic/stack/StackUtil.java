package com.github.wdn.coding2017.basic.stack;

import com.github.wdn.coding2017.basic.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack stack = new Stack();
		Stack stack1 = new Stack();
		while (!s.isEmpty()){
			stack.push(s.pop());
		}
		while (!stack.isEmpty()){
			stack1.push(stack.pop());
		}
		while (!stack1.isEmpty()){
			s.push(stack1.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack stack = new Stack();
		while (!s.isEmpty()) {
			Object popObject = s.pop();
			if(popObject.equals(o)){
				break;
			}
			stack.push(popObject);
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (len > s.size() || len < 0) {
			throw new IndexOutOfBoundsException();
		}
		Object[] result = new Object[len];
		Stack stack = new Stack();
		for (int i = 0; i < len; i++) {
			Object o = s.pop();
			result[i]=o;
			stack.push(o);
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
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
		if(s.length()<2){
			return false;
		}
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		char[] chars = s.toCharArray();
		int charsLength = chars.length;
		if(charsLength%2==1 && isBrackets(chars[charsLength / 2])){
			return false;
		}
		for (int i = 0; i < charsLength/2; i++) {
			char c = chars[i];
			if (isBrackets(c)) {
				s1.push(c);
			}
		}
		for (int i = charsLength-1; i > charsLength/2; i--) {
			char c = chars[i];
			if (isBrackets(c)) {
				s2.push(c);
			}
		}
		if (s1.size() != s2.size()) {
			return false;
		}
		for (int i = 0; i < s1.size(); i++) {
			if (!isPairing((Character) s1.pop(), (Character) s2.pop())) {
				return false;
			}
		}
		return true;
	}
	// parenthesis 圆括号
	// square brackets 方括号
	// braces 大括号
	// 这里用bracket表示统称
	private static boolean isBrackets(char c){
		if('['==c||']'==c||
			'('==c||')'==c||
			'{'==c||'}'==c){
			return true;
		}
		return false;
	}

	private static boolean isPairing(char left, char right) {
		if(left=='(' && right==')'){
			return true;
		}else if(left=='[' && right==']'){
			return true;
		}else if(left=='{' && right=='}'){
			return true;
		}else {
			return false;
		}
	}
}
