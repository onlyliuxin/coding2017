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
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		
		reverseTo(s, s1);
		reverseTo(s1, s2);
		reverseTo(s2, s);
	}

	private static void reverseTo(Stack s, Stack s1) {
		while(!s.isEmpty()) {
			s1.push(s.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if (o == null || s == null || s.size() == 0) {
			return;
		}
		Stack temp = new Stack();
		reverseTo(s,temp);
		while(!temp.isEmpty()) {
			if (temp.peek().equals(o)) {
				temp.pop();
				continue;
			}
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
		if (len <= 0 || s == null || s.size() < len) {
			return null;
		}
		Object[] objs = new Object[len];
		for(int i = 0; i < len; i++) {
			objs[i] = s.pop();
		}
		for(int i = len-1; i >= 0; i--) {
			s.push(objs[i]);
		}
		return objs;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		char[] c = s.toCharArray();
		Stack tag = new Stack();
		for (int i = 0; i < c.length; i++) {
			switch(c[i]) {
				case '(':
				case '[':
				case '{':
					tag.push(c[i]);
					break;
				case ')':
					if (tag.isEmpty() || '(' != (char)tag.pop()) {
						return false;
					}
					break;
				case '}':
					if (tag.isEmpty() || '{' != (char)tag.pop()) {
						return false;
					}
					break;
				case ']':
					if (tag.isEmpty() || '[' != (char)tag.pop()) {
						return false;
					}
					break;
			}
		}
		if (!tag.isEmpty()) {
			return false;
		}
		return true;
	}
	
}
