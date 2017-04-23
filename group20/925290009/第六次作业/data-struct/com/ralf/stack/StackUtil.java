package com.ralf.stack;

import java.util.NoSuchElementException;

public class StackUtil {

	private static MyStack myStack = new MyStack<>();

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param <T>
	 */
	public static <T> void reverse(MyStack<T> stack) {

		if (stack.isEmpty()) {
			System.out.println("该栈为空栈！");
			return;
		}
		@SuppressWarnings("unchecked")
		T[] elements = (T[]) new Object[stack.size()];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = stack.pop();
		}
		for (int i = 0; i < elements.length; i++) {
			stack.push(elements[i]);
		}

	}
	
	public static void bad_reverse(MyStack<Integer> s) {
		if(s == null || s.isEmpty()){
			return;
		}
		MyStack<Integer> tmpStack = new MyStack<>();
		while(!s.isEmpty()){
			tmpStack.push(s.pop());
		}
		
		s = tmpStack;
		
	}
	

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static <T> void remove(MyStack<T> s, T o) {
		if (s.isEmpty()) {
			System.out.println("该栈为空！");
			return;
		}
		MyStack<T> stack = new MyStack<>();

		while (!s.isEmpty()) {
			T t = s.pop();
			if (t.equals(o)) {
				PopAndPush(s, stack);
				return;
			}
			stack.push(t);
		}
		throw new NoSuchElementException("该栈没有该元素！");

	}

	private static <T> void PopAndPush(MyStack<T> s, MyStack<T> stack) {
		while (!stack.isEmpty()) {
			T t = stack.pop();
			s.push(t);
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getTop(MyStack<T> s, int len) {

		if (s.isEmpty() || len > s.size()) {
			return null;
		}
		MyStack<T> oldStack = s;
		T[] elements = (T[]) new Object[len];
		for (int i = 0; i < len; i++) {
			elements[i] = s.pop();
		}
		s = oldStack;
		return elements;
	}

	/**
	 * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
	 * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
	 * 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * @param s
	 * @return
	 */
	public static <T> boolean isValidPairs(String s) {

		char[] ch = s.toCharArray();
		if (ch.length < 1) {
			return false;
		}

		MyStack<String> leftStack = new MyStack<>();
		MyStack<String> rightStack = new MyStack<>();

		for (int i = 0; i < ch.length; i++) {

			switch (ch[i]) {
			case '(':
				leftStack.push(String.valueOf(ch[i]));
				break;

			case '[':
				leftStack.push(String.valueOf(ch[i]));
				break;

			case '{':
				leftStack.push(String.valueOf(ch[i]));
				break;

			case ')':
				rightStack.push(String.valueOf(ch[i]));
				break;

			case ']':
				rightStack.push(String.valueOf(ch[i]));
				break;

			case '}':
				rightStack.push(String.valueOf(ch[i]));
				break;

			default:
				break;
			}
		}
		return isPair(leftStack, rightStack);

	}

	private static boolean isPair(MyStack<String> leftStack,
			MyStack<String> rightStack) {

		if (leftStack.size() != rightStack.size()) {
			return false;
		}

		reverse(rightStack);
		while (!leftStack.isEmpty()) {

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(leftStack.pop()).append(rightStack.pop());

			String pair = stringBuilder.toString();
			if (!pair.equals("()") && !pair.equals("[]") && !pair.equals("{}")) {
				return false;
			}
		}
		return true;
	}

}
