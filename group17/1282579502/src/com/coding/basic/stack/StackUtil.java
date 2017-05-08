package com.coding.basic.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class StackUtil {
	
	public static void main(String[] args){
		Stack<Integer> newStack = new Stack<>();
		newStack.push(1);
		newStack.push(2);
		newStack.push(3);
		newStack.push(4);
		newStack.push(5);
		dumpStack(newStack);
		//reverse(newStack);
		//remove(newStack, 4);
		dumpStack(newStack);
		
		StackUtil.isValidPairs("([e{d}f])");
	}
	
	public static void dumpStack(Stack s){
		Object[] sarray = new Object[s.size()];
		s.copyInto(sarray);
		System.out.println(Arrays.toString(sarray));
	}
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		if(s == null) return;
		int n = s.size();
		Stack<Integer> tmp = new Stack<>();
		for(int i = 0; i<n; i++){
			Integer top = s.pop();
			while(s.size() > i){
				tmp.push(s.pop());
			}
			s.push(top);
			while(!tmp.isEmpty()){
				s.push(tmp.pop());
			}
		}
		
	
		
		//s = newStack;
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null || o == null) return;
		Stack newStack = new Stack();
		while(!s.isEmpty()){
			Object tmp = s.pop();
			if( Objects.equals(o, tmp) ){
				break;
			}
			newStack.push(tmp);
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
		Object[] ret = new Object[len];
		
		for(int i = 0; i<len; i++){
			ret[i] = s.pop();
		}
		
		return ret;
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
		if(s == null) return false;
		
		Stack bracketStack = new Stack();
		int i = 0;
		while(i<s.length()){
			if(s.charAt(i) == '('){
				bracketStack.push('(');
			}
			else if(s.charAt(i) == ')'){
				if((char)bracketStack.peek() == '('){
					bracketStack.pop();
				}
				else{
					System.out.println("current bracket stack. found char: " + s.charAt(i));
					dumpStack(bracketStack);
					return false;
				}
			}
			else if(s.charAt(i) == '['){
				bracketStack.push('[');
			}
			else if(s.charAt(i) == ']'){
				if((char)bracketStack.peek() == '['){
					bracketStack.pop();
				}
				else{
					System.out.println("current bracket stack. found char: " + s.charAt(i));
					dumpStack(bracketStack);
					return false;
				}
			}
			else if(s.charAt(i) == '{'){
				bracketStack.push('{');
			}
			else if(s.charAt(i) == '}'){
				if((char)bracketStack.peek() == '{'){
					bracketStack.pop();
				}
				else{
					System.out.println("current bracket stack. found char: " + s.charAt(i));
					dumpStack(bracketStack);
					return false;
				}
			}
			else {
				
			}
			i++;
		}
		dumpStack(bracketStack);
		if(bracketStack.isEmpty()){
			return true;
		}
		return false;
	}
	
	
}
