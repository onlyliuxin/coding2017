package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if (s == null || s.size() == 0) {
			return;
		}

		int size = s.size();
		Integer[] temp = new Integer[size];
		for (int i=0; i<size; i++) {
			temp[i] = (Integer)s.pop();
		}

		for (int i=0; i<size; i++) {
			s.push(temp[i]);
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if (s == null || s.size() == 0 || o == null) {
			return;
		}
		Stack temp = new Stack();
		while (s.size() != 0){
			if (s.peek().equals(o)) {
				s.pop();
				break;
			}
			temp.push(s.pop());
		}
		while (temp.size() != 0) {
			s.push(temp.pop());
		}

	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (s.size() == 0 || s == null || s.size() < len || len <= 0) {
			return null;
		}
		Stack temp = new Stack();
		Object[] result = new Object[len];
		for (int i=0; i<len; i++) {
			result[i] = s.peek();
			temp.push(s.pop());
		}
		while (temp.size() != 0) {
			s.push(temp.pop());
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
		if (s.isEmpty()) {
			return false;
		}
		char[] chars = s.toCharArray();
		Stack stack = new Stack();

		for (char c: chars) {
			stack.push(c);
		}
		for (int i=0; i<chars.length / 2; i++) {
			switch (chars[i]) {
				case '(':
					if (')' != (char)stack.pop())
						return false;
					break;
				case '[':
					if (']' != (char)stack.pop())
						return false;
					break;
				case '{':
					if ('}' != (char)stack.pop())
						return false;
					break;
				default:
					stack.pop();
					break;
			}
		}
		return true;
	}
	
	
}
