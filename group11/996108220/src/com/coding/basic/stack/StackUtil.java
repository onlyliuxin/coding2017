package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		
		reverse(s,s.size());
	}

	private static void reverse(Stack s, int length) {
		if (length==1) {
			return ;
		}
		Stack s1=new Stack();
		Object o=s.pop();
		for (int i = 1; i < length; i++) {
			s1.push(s.pop());
		}
		s.push(o);
		for (int i = 1; i < length; i++) {
			s.push(s1.pop());
		}
		reverse(s, length-1);
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if (s.size()==0) {
			return;
		}
		Stack s1=new Stack();
		while(s.size()!=0) {
			if (!s.peek().equals(o)) {
				s1.push(s.pop());
				
			}
			else {
				s.pop();
				break;
			}
		}
		while(s1.size()!=0) {
			s.push(s1.pop());
		}
		

	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (len>s.size()||len<=0) {
			return null;
		}
		Stack s1=new Stack();
		Object[] array=new Object[len];
		for (int i = 0; i < len; i++) {
			Object object=s.pop();
			array[i]=object;
			s1.push(object);
		}
		while(s1.size()!=0) {
			s.push(s1.pop());
		}
		return array;
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
		Stack stack=new Stack();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				stack.push(s.charAt(i));
				break;
			case '[':
				stack.push(s.charAt(i));
				break;
			case '{':
				stack.push(s.charAt(i));
				break;
			case ')':
				if (stack.size()==0||(!stack.pop().equals('('))) {
					return false;
				}
				break;
			case ']':
				if (stack.size()==0||(!stack.pop().equals('['))) {
					return false;
				}
				break;
			case '}':
				if (stack.size()==0||(!stack.pop().equals('{'))) {
					return false;
				}
				break;
			default:
				break;
			}
		}
		if (stack.size()!=0) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	
}
