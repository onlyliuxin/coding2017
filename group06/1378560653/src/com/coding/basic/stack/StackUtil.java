package com.coding.basic.stack;

import java.util.Stack;

public class StackUtil {
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		if(s.isEmpty()){
			return;
		}
		
		Stack<Integer> temp1 = new Stack<>();
		Stack<Integer> temp2 = new Stack<>();
		
		while(!s.isEmpty()){
			temp1.push(s.pop());
		}
		
		while(!temp1.isEmpty()){
			temp2.push(temp1.pop());
		}
		
		while(!temp2.isEmpty()){
			s.push(temp2.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Object> s,Object o) {
		if(s.isEmpty()){
			return;
		}
		
		Stack<Object> temp = new Stack<>();
		
		while(!s.isEmpty()){
			Object data = s.pop();
			if(o.equals(data)){
				continue;
			}
			temp.push(data);
		}
		
		while(!temp.isEmpty()){
			s.push(temp.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<Object> s,int len) {
		if(s.isEmpty()){
			return new Object[0];
		}
		
		if(len >= s.size()){
			len = s.size();
		}
		
		Object[] result = new Object[len];
		
		Stack<Object> temp = new Stack<>();
		int count = 0;
		while(!s.isEmpty()){
			Object data = s.pop();
			temp.push(data);
			result[count++] = data;
			if(count == len){
				break;
			}
		}
		
		while(!temp.isEmpty()){
			s.push(temp.pop());
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
		if(s.length() == 0){
			return false;
		}
		
		Stack<Character> temp1 = new Stack<>();
		Stack<Character> temp2 = new Stack<>();
		
		for(int i = 0, j = s.length()-1; i <= j; i++,j--){
			temp1.push(s.charAt(i));
			temp2.push(s.charAt(j));
		}
		
		while(!temp1.isEmpty()&&!temp2.isEmpty()){
			char a = temp1.pop();
			char b = temp2.pop();
			if(a == '('){
				if(b != ')'){
					return false;
				}
			} 
			if(a == '[') {
				if(b != ']'){
					return false;
				}
			}
			if(a == '{'){
				if(b != '}'){
					return false;
				}
			}
		}
		return true;
	}
	
	public static String toString(Stack<?> s) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		while(!s.isEmpty()){
			buffer.append(s.pop());
			if(s.size()!=0){
				buffer.append(",");		
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
}