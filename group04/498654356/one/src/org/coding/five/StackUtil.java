package org.coding.five;

import java.util.Arrays;

import org.coding.one.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 *  *** 使用中间变量和长度来完成
	 */
	public static void reverse(Stack s) {
		if(isEmpty(s)) {
			return;
		}
		Stack temp = new Stack();
		int size = s.size();
		int count = 0;
		while(count < size) {
			Object pop = s.pop();
			for(int i = count + 1; i < size; i++) {
				temp.push(s.pop());
			}
			s.push(pop);
			move(temp, s);
			count++;
		}
	}

	// ***递归,通过每一个函数栈存放一个元素
	public static void reverse2(Stack s) {
		if(isEmpty(s)) {
			return;
		}
		Stack temp = new Stack();
		move(s, temp);
		addTop(temp.pop(), temp, s);
	}
	

	private static void addTop(Object pop, Stack temp, Stack s) {
		if(!temp.isEmpty()) {
			addTop(temp.pop(), temp, s);
		}
		s.push(pop);
	}

	private static boolean isEmpty(Stack s) {
		return s == null || s.isEmpty();
	}

	private static void move(Stack source, Stack dest) {
		while(!source.isEmpty()) {
			dest.push(source.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(isEmpty(s) || o == null) {
			return;
		}
		Stack temp = new Stack();
		while(!s.isEmpty()) {
			Object dest = s.pop();
			if(!o.equals(dest)) {
				temp.push(dest);
			}
		}
		move(temp, s);
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(isEmpty(s) || len < 1) {
			return null;
		}
		Object[] result = new Object[len];
		int count = 0;
		Stack temp = new Stack();
		while(!s.isEmpty() && count < len) {
			Object v = s.pop();
			result[count] = v;
			temp.push(v);
			count++;
		}
		move(temp, s);
		return Arrays.copyOf(result, count);
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
		if(s== null || s.isEmpty()) {
			return false;
		}
		char a = '(';
		char b = '[';
		char c = '{';
		char a2 = ')';
		char b2 = ']';
		char c2 = '}';
		Stack stack = new Stack();
		for(int i = 0, length = s.length(); i < length; i++) {
			char charAt = s.charAt(i);
			if(charAt == a) {
				stack.push(a);
			} else if(charAt == b){
				stack.push(b);
			} else if(charAt == c){
				stack.push(c);
			} else if(charAt == a2){
				if(!isEqualStackTopValue(a, stack)) {
					return false;
				}
			} else if(charAt == b2){
				if(!isEqualStackTopValue(b, stack)) {
					return false;
				}
			} else if(charAt == c2){
				if(!isEqualStackTopValue(c, stack)) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static boolean isEqualStackTopValue(char a2, Stack stack) {
		return (char) stack.pop() == a2;
	}
	
	
}
