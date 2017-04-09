package com.coding.basic.stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s == null || s.isEmpty()) {
			return;
		}
		
		Stack retStack = new Stack();
		Object o = null;
		do
		{
			o = s.pop();
			retStack.push(o);
		}
		while(o != null);
		
		s = retStack;
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null || s.isEmpty()) {
			return;
		}
		
		Stack tempStack = new Stack();
		Object o1 = null;
		do
		{
			o1 = s.pop();
			if(o1 == o) {
				break;
			}
			else {
				tempStack.push(o1);	
			}
		}
		while(o1 != null);
		
		do
		{
			o1 = tempStack.pop();
			s.push(o1);	
		}
		while(o1 != null);
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(s == null || s.isEmpty() ||len <= 0) {
			return null;
		}
		
		Object[] arr = new Object[len];
		if(s.size() < len){
			for (int i = 0; i < s.size(); i++) {
				arr[i] = s.pop();
			}
			for (int i = s.size(); i >= 0; i--) {
				s.push(arr[i]);
			}
		}
		else
		{
			for (int i = 0; i < len; i++) {
				arr[i] = s.pop();
			}
			for (int i = len; i >= 0; i--) {
				s.push(arr[i]);
			}
		}
		
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
		boolean isValid = true;
		Stack stack = new Stack();
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) =='(' || s.charAt(i) =='[' || s.charAt(i) =='{') {
				stack.push(s.charAt(i)); 
			}
			else if(s.charAt(i) ==')' || s.charAt(i) ==']' || s.charAt(i) =='}') {
				char c = ' ';
				switch(s.charAt(i))
				{
					case ')':
						c = (char) stack.pop();
						if(c != '(')
							isValid = false;
						break;
					case ']':
						c = (char) stack.pop();
						if(c != '[')
							isValid = false;
						break;
					case '}':
						c = (char) stack.pop();
						if(c != '{')
							isValid = false;
						break;
				
				}
				if(!isValid) {
					break;	
				}	
			}
		}
		return isValid;
	}
}
