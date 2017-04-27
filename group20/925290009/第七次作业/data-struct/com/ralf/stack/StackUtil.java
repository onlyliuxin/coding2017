package com.ralf.stack;

import java.util.NoSuchElementException;

public class StackUtil {

	private static MyStack myStack = new MyStack<>();

	/**
	 * ����ջ�е�Ԫ����Integer, ��ջ����ջ���� : 5,4,3,2,1 ���ø÷����� Ԫ�ش����Ϊ: 1,2,3,4,5
	 * ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
	 * 
	 * @param <T>
	 */
	public static <T> void reverse(MyStack<T> stack) {

		if (stack.isEmpty()) {
			System.out.println("��ջΪ��ջ��");
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
	 * ɾ��ջ�е�ĳ��Ԫ�� ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
	 * 
	 * @param o
	 */
	public static <T> void remove(MyStack<T> s, T o) {
		if (s.isEmpty()) {
			System.out.println("��ջΪ�գ�");
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
		throw new NoSuchElementException("��ջû�и�Ԫ�أ�");

	}

	private static <T> void PopAndPush(MyStack<T> s, MyStack<T> stack) {
		while (!stack.isEmpty()) {
			T t = stack.pop();
			s.push(t);
		}
	}

	/**
	 * ��ջ��ȡ��len��Ԫ��, ԭ����ջ��Ԫ�ر��ֲ��� ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty��
	 * ����ʹ������һ��ջ������
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
	 * �ַ���s ���ܰ�����Щ�ַ��� ( ) [ ] { }, a,b,c... x,yz ʹ�ö�ջ����ַ���s�е������ǲ��ǳɶԳ��ֵġ� ����s =
	 * "([e{d}f])" , ����ַ����е������ǳɶԳ��֣� �÷�������true ��� s = "([b{x]y})",
	 * ����ַ����е����Ų��ǳɶԳ��ֵģ� �÷�������false;
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
