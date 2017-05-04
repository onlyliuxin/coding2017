package com.donaldy.basic;

import java.util.*;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		ArrayList arrayList = new ArrayList();
		while (!s.isEmpty()) {
			Object element = s.pop();
			arrayList.add(element);
		}

		for (int i = 0; i < arrayList.size(); ++i) {
			s.push(arrayList.get(i));
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		//若stack的值为唯一的。
		Stack stack = new Stack();
		while (!s.isEmpty()) {
			Object element = s.pop();
			if (o == element) {
				break;
			}
			stack.push(element);
		}

		while (!stack.isEmpty()) {
			Object element = stack.pop();
			s.push(element);
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (len < 0 || len >= s.size())
			throw new IndexOutOfBoundsException("len : " + len);

		Object [] arr = new Object[len];

		ArrayList arrayList = new ArrayList();

		while (!s.isEmpty()) {
			arrayList.add(s.pop());
		}

		for (int i = arrayList.size() - 1; i >= 0; --i)
			s.push(arrayList.get(i));

		for (int i = 0 ; i < len; ++i)
			arr[i] = arrayList.get(i);

		return arr;
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
		char [] arr = s.toCharArray();
		Stack stack = new Stack();
		for (int i = 0; i < s.length(); ++i) {
			if (arr[i] == '(' )
				stack.push(')');
			if ( arr[i] == '{' )
				stack.push('}');
			if ( arr[i] == '[')
				stack.push(']');

			if (arr[i] == ')' ) {
				if (')' != (char)stack.peek())
					break;
				stack.pop();
			}

			if (arr[i] == '}' ) {
				if ('}' != (char)stack.peek())
					break;
				stack.pop();
			}

			if (arr[i] == ']' ) {
				if (']' != (char)stack.peek())
					break;
				stack.pop();
			}

		}

		if (stack.isEmpty())
			return true;

		return false;
	}
	
	
}
