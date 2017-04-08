package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack tmpStack1 = new Stack();
		Stack tmpStack2 = new Stack();
		while (!s.isEmpty()) {
			tmpStack1.push(s.pop());
		}
		while (!tmpStack1.isEmpty()) {
			tmpStack2.push(tmpStack1.pop());
		}
		while (!tmpStack2.isEmpty()) {
			s.push(tmpStack2.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack tmpStack = new Stack();
		while (!s.isEmpty()) {
			if (o == s.peek() || o.equals(s.peek())) {
				s.pop();
				break;
			}
			tmpStack.push(s.pop());
		}
		while (!tmpStack.isEmpty()) {
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (len <= 0) {
			return new Object[0];
		}
		
		Stack tmpStack = new Stack();
		Object[] resultArray = new Object[len<s.size()?len:s.size()];
		for (int i = 0; i < len && !s.isEmpty(); i++) {
			resultArray[i] = s.pop();
			tmpStack.push(resultArray[i]);
		}
		
		while (!tmpStack.isEmpty()) {
			s.push(tmpStack.pop());
		}
		
		return resultArray;
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
		for (char item:s.toCharArray()) {
			switch (item) {
			case '(':
				stack.push(new Integer(1));
				break;
			case '[':
				stack.push(new Integer(2));
				break;
			case '{':
				stack.push(new Integer(3));
				break;
			case ')':
				if (!stack.peek().equals(new Integer(1))) {
					return false;
				}
				stack.pop();
				break;
			case ']':
				if (!stack.peek().equals(new Integer(2))) {
					return false;
				}
				stack.pop();
				break;
			case '}':
				if (!stack.peek().equals(new Integer(3))) {
					return false;
				}
				stack.pop();
				break;
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
}
