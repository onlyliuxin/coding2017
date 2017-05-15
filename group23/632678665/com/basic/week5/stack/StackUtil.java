package com.coding.week5.stack;

import com.coding.weak1.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack stack = new Stack();
		Stack stack1 = new Stack();
		while (!s.isEmpty()) {
			stack.push(s.pop());
		}
		while (!stack.isEmpty()) {
			stack1.push(stack.pop());
		}
		while (!stack1.isEmpty()) {
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
		while (!s.isEmpty()){
			Object o1 = s.pop();
			if (!o.equals(o1)) {
				stack.push(o1);
			}
		}
		while (!stack.isEmpty()) {
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
		if (len > s.size() || len <= 0) {
			throw new IllegalArgumentException(len+"");
		}

		Object[] objects = new Object[len];
		for (int i = 0; i < len; i++) {
			objects[i] = s.pop();
		}
		for (int i = len - 1; i >= 0 ; i--) {
			s.push(objects[i]);
		}
		return objects;
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
		char[] chars = s.toCharArray();
		for (char c : chars) {
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (c == ')' || c == '}' || c == ']'){
				if (stack.isEmpty()) {
					return false;
				}
				if (!isPair((char)stack.pop(), c)){
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	private static boolean isPair(char left, char right) {
		switch (left) {
			case '{':
				return right == '}';
			case '[':
				return right == ']';
			case '(':
				return right == ')';
			default:
				return false;
		}
	}
	
}
