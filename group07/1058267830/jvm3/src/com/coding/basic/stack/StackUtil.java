package com.coding.basic.stack;

import java.util.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		if(s.size() == 0)
			return;
		Object obj = s.pop();
		reverse(s);
		addToButtom(s, obj);
	}

	private static void addToButtom(Stack s, Object obj) {
		if(s.size() == 0) {
			s.push(obj);
			return;
		}
		Object result = s.pop();
		addToButtom(s, result);
		s.push(result);
			
	}


	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack stack = new Stack();
		while(!s.isEmpty()){
			Object obj = stack.pop();
			if(!obj.equals(o)){
				stack.push(obj);
			}else
				break;
		}
		while(!stack.isEmpty()){
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
		Object[] result = new Object[len];
		int i = 0;
		while(i<len && !s.isEmpty()) {
			result[i++] = s.pop();
		}
		for(int j=result.length-1; j>=0; j--){
			s.push(result[j]);
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
		Stack stack1 = new Stack();
		Stack stack2 = new Stack();
		for(int i=0; i<s.length(); i++) {
			stack1.push(s.charAt(i));
		}
		for(int i=s.length()-1; i>=0; i--) {
			stack2.push(s.charAt(i));
		}
		
		for(int i=0; i<s.length(); i++) {
			if(!stack1.pop().equals(stack2.pop()))
				return false;
		}
		return true;
	}
	
	
}
