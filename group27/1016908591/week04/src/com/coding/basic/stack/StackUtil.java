package com.coding.basic.stack;

import java.util.ArrayList;



public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack newStack = new Stack();
		while(!s.isEmpty()){
			newStack.push(s.peek());
			s.pop();
		}
		s = newStack;
		

	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack newStack = new Stack();
		while(s.isEmpty()){
			if(o == s.peek()){
				s.pop();
				break;
			}else {
				newStack.push(s.pop());
				
			}
			
		}
		while(!newStack.isEmpty()){
			s.push(newStack.pop());
		}

	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Stack newStack= new Stack();
		Object[] arr = (Object[]) new Object();
		for(int count = 0;count<len;count++){
			arr[count] = s.peek();
			newStack.push(s.pop());
			
		}
		while(newStack.isEmpty()){
			s.push(newStack.pop());
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
		Stack newstack = new Stack();
		String str = "{[(";
		String shr;
		int len = 0;
		for(int count = 0;count<s.length();count++){
			if(str.contains(s.substring(count, count++))  ){
				newstack.push(s.substring(count, count++));
				len++;
			}
			if(len/2!=0){
			return false;
		}
		
		}
		
		
		
			
			
			
		}
		
	}
	
	

