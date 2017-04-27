package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s.isEmpty()){
			return ;
		}
		int temp1 = (int) s.pop();
		reverse(s);
		if(s.isEmpty()){
			s.push(temp1);
			return ;
		}
		int temp2 = (int) s.pop();
		reverse(s);
		s.push(temp1);
		reverse(s);
		s.push(temp2);
	}
	

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s.isEmpty()){
			return ;
		}
		Stack stack = new Stack();
		while(!s.isEmpty()){
			Object pop = s.pop();
			if(!pop.equals(o)){
				stack.push(pop);
			}
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
		int i = 0;
		Stack stack = new Stack();
		Object[] objects = new Object[len];
		while(i < len){
			Object o = s.pop();
			stack.push(o);
			objects[i] = o;
			i++;
		}
		while(!stack.isEmpty()){
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
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '(':
			case '[':
			case '{':
				stack.push(c);
				break;
			case ')':
				 if((char)stack.pop()!='('){
			        return false;
			      }
			      break;
			case ']':
				 if((char)stack.pop()!='['){
			        return false;
			      }
			      break;
			case '}':
				 if((char)stack.pop()!='{'){
			        return false;
			      }
			      break;
			}
		}
		if(stack.isEmpty()){
			return true;
		}
		return false;
	}
	
	
}