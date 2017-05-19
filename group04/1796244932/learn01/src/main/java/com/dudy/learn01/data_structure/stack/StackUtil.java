package com.dudy.learn01.data_structure.stack;

import java.util.Collections;
import java.util.Stack;

public class StackUtil {
	
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		
		if(s == null || s.isEmpty()){
			return;
		}
		
		//Collections.reverse(s);
		Stack<Integer> stack = new Stack<>();
		while(!s.isEmpty()){
			stack.push(s.pop());
		}
		System.out.println(stack.toString());
		while(!stack.isEmpty()){
			Integer pop = stack.pop();
			addToBottom(s,pop);
		}
		
		
	}
	
	private static void addToBottom(Stack<Integer> s, Integer pop) {
		// TODO Auto-generated method stub
		if(s.isEmpty()){
			s.push(pop);
		} else {
			Integer temp = s.pop();
			addToBottom(s, pop);
			s.push(temp);
		}
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Integer> s,Integer o) {
		if(s == null || s.isEmpty() ){
			return;
		}
		
		Stack<Integer> tempStack = new Stack<>();
		while(!s.isEmpty()){
			Integer value = s.pop();
			if(o.equals(value)){
				break;
			}
			tempStack.push(value);
		}
		
		while(!tempStack.isEmpty()){
			s.push(tempStack.pop());
		}
		
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Integer[] getTop(Stack<Integer> s,int len) {
		Integer[] o = new Integer[len];
		for (int i = 0; i < o.length; i++) {
			o[i] = s.pop();
		}
		
		for (int i = o.length-1; i >= 0; i--) {
			s.push(o[i]);
		}
		
		return o;
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
		char[] c = s.toCharArray();

		Stack<Character> stack = new Stack();
		for (int i = 0; i< c.length; i++){
			char c1 = c[i];
			if (c1 == '(' || c1 == '[' || c1 == '{'){
				stack.push(c1);
			}

			if (!stack.isEmpty()){
				if ( (c1 == ')' && stack.peek() == '(')
						|| (c1 == ']' && stack.peek() == '[')
						|| (c1 == '}' && stack.peek() == '{' )){
					stack.pop();
				}
			}
		}

		return stack.isEmpty();
	}
	
	
}