package com.coding.basic.stack;

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
		Integer top = s.pop();
		reverse(s);
		addToBottom(s,top);
		
		
	}
	public static void addToBottom(Stack<Integer> s,  Integer value){
		if(s.isEmpty()){
			s.push(value);
		} else{
			Integer top = s.pop();
			addToBottom(s,value);
			s.push(top);
		}
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		Stack tmp = new Stack();
		while (!s.isEmpty()) {
			Object object = s.pop();
			if (object.equals(o))
				break;
			tmp.push(object);
		}
		while (!tmp.isEmpty()) {
			Object object = tmp.pop();
			s.push(object);
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		if(len<0)return new Object[0];
		if (len > s.size())
			len = s.size();
		Stack tmpStack=new Stack();
		Object[] os = new Object[len];
		int count=0;
		for(;count<len;count++) {
			os[count] = s.pop();
			tmpStack.push(os[count]);
		}
		while(!tmpStack.isEmpty()){
			s.push(tmpStack.pop());
		}
		return os;
	}

	/**
	 * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
	 * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
	 * 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		Stack st = new Stack();
		char[] chr = s.toCharArray();
		int len = chr.length;
		for (int index = 0; index < len; index++) {
			char tmp = chr[index];
			if (tmp == '(' || tmp == '{' || tmp == '[')
				st.push(tmp);
			else if (tmp == ')') {
				if(st.isEmpty())return false;
				char pairs = (char) st.pop();
				if (pairs != '(')
					return false;
			} else if (tmp == ']') {
				if(st.isEmpty())return false;
				char pairs = (char) st.pop();
				if (pairs != '[')
					return false;
			} else if (tmp == '}') {
				if(st.isEmpty())return false;
				char pairs = (char) st.pop();
				if (pairs != '{')
					return false;
			}
		}
		if (!st.isEmpty())
			return false;
		return true;
	}

}
