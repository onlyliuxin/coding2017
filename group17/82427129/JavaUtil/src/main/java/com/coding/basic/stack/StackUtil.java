package com.coding.basic.stack;

public class StackUtil {
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse_useTwoStack(Stack<Integer> s) {
		if (s.size() < 2) {
			return;
		}
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int length = s.size();
		for (int i = 0; i < length; i++) {
			stack1.push(s.pop());
		}
		for (int i = 0; i < length; i++) {
			stack2.push(stack1.pop());
		}
		for (int i = 0; i < length; i++) {
			s.push(stack2.pop());
		}
	}

	public static void reverse(Stack<Integer> s) {
		if (s.size() < 2) {
			return;
		}
		Stack<Integer> tmp = new Stack<>();
		int length = s.size();
		for (int i = 0; i < length; i++) {
			tmp.push(s.pop());
		}
		for (int i = 0; i < length; i++) {
			addToBottom(s, tmp.pop());
		}
	}

	/**
	 * 添加元素到栈底部
	 * 
	 * @param s
	 * @param value
	 */
	public static void addToBottom(Stack<Integer> s, Integer value) {
		/*
		 * Stack<Integer> stack = new Stack<>(); for (int i = 0; i < s.size();
		 * i++) { stack.push(s.pop()); } s.push(value); for (int i = 0; i <
		 * s.size(); i++) { s.push(stack.pop()); }
		 */
		if (s.isEmpty()) {
			s.push(value);
		} else {
			Integer top = s.pop();
			addToBottom(s, value);
			s.push(top);
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static boolean remove(Stack<?> s, Object o) {
		if (s.isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		boolean flag = false;
		Stack<Integer> tmp = new Stack<>();
		while (!s.isEmpty()) {
			tmp.push(s.pop());
			if (tmp.peek().equals(o)) {
				flag = true;

				tmp.pop();
				for (int i = 0; i < tmp.size(); i++) {
					s.push(tmp.pop());
				}
				break;
			}
		}
		return flag;
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<?> s, int len) {
		Object[] result = new Object[len];
		Stack<?> tmp = new Stack<>();
		for (int i = 0; i < len; i++) {
			result[i] = s.peek();
			tmp.push(s.pop());
		}
		for (int i = 0; i < len; i++) {
			s.push(tmp.pop());
		}
		return result;
	}

	/**
	 * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
	 * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
	 * 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		Stack<Integer> stack = new Stack<>();
		char[] strCharArr = s.toCharArray();
		for (int i = 0; i < strCharArr.length; i++) {
			char cc = strCharArr[i];
			int c = cc&0xffff;
			if (isBracket(c)) {
				if (c == 40 || c == 91 || c == 123) {
					stack.push(c);
				}else{
					int left = stack.pop();
					if(left+1!=c&&left+2!=c){
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * judge is Bracket or not
	 * @param c
	 * @return
	 */
	private static boolean isBracket(int c) {
		if (c == 40 || c == 41 || c == 91 || c == 93 || c == 123 || c == 125) {
			return true;
		} else {
			return false;
		}
	}
}
