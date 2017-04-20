package com.coding.basic.stack;

import java.util.NoSuchElementException;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack a = new Stack();

		while (!s.isEmpty()){
			Object o = s.pop();
			int count =0;
			while (!a.isEmpty()){
				s.push(a.pop());
				count++;
			}
			a.push(o);
			for (int i = 0; i < count; i++) {
				a.push(s.pop());
			}
		}
		while (!a.isEmpty()){
			s.push(a.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack stack = new Stack();
		boolean flag = false;
		while (!s.isEmpty()){
			Object t = s.pop();
			if (t.equals(o)){
				flag=true;
				break;
			}
			stack.push(t);
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
		}
		if (!flag){
			throw new NoSuchElementException(o.toString());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (s.size()<len){
			throw new IndexOutOfBoundsException(len+"");
		}
		Object[] objects = new Object[len];
		Stack stack = new Stack();
		for (int i = 0; i < len; i++) {
			Object t = s.pop();
			stack.push(t);
			objects[i]=t;
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
		}
		return objects;
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
		String string = "([{}])";
		String stringLeft = "([{";
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (string.indexOf(ch)>=0){
				if(stringLeft.indexOf(ch)>=0){
					stack.push(Character.valueOf(ch));
				}else {
					Character top = (Character) stack.peek();
					switch (top){
						case '{':
							if (ch == '}'){
								stack.pop();
							}else {
								return false;
							}
							break;
						case '[':
							if (ch==']'){
								stack.pop();
							}else {
								return false;
							}
							break;
						case '(':
							if (ch==')'){
								stack.pop();
							}else {
								return false;
							}
							break;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		Stack stack = new Stack();
		for (int i = 0; i < 5; i++) {
			stack.push(i+1);
		}
		StackUtil.reverse(stack);
	}
}
