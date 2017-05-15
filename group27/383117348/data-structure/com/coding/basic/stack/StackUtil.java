package com.coding.basic.stack;

import org.junit.Test;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	
	public static void reverse(Stack s) {
		if (s != null && !s.isEmpty()) {
			Object [] obj = new Object[s.size()];
			for(int i =0 ;i < obj.length; i++){
				obj[i] = s.pop();
			}
			for(int i =0 ;i < obj.length; i++){
				s.push(obj[i]);
			}
		}else{
			
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		if(s!=null && s.size()>0){
			Stack newStack = new Stack();
			while(s.size()>0){
				newStack.push(s.pop());
				if(newStack.peek().equals(o)){
					newStack.pop();
				}
			}
			while(newStack.size()>0){
				s.push(newStack.pop());
			}
		}else{
			
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
		if(s!=null && s.size()>0 && len>0 && len<=s.size()){
			Object[] objs = new Object[len];
			Stack newStack = new Stack();
			for(int i = 0;i<len;i++){
				objs[i] = s.pop();
				newStack.push(objs[i]);
			}
			while(newStack.size()>0){
				s.push(newStack.pop());
			}
			return objs;
		}
		return null;
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
		char[] chars = s.toCharArray();
		Stack special = new Stack();
		for(int i =0; i <chars.length ; i++){
			char cha =  chars[i];
			if(cha == '('  || cha == '{' ||cha=='[' ){
				special.push(cha);
			}else if(cha == ')'){
				if(special.peek().equals('(')){
					special.pop();
				}
			}else if(cha == '}'){
				if(special.peek().equals('{')){
					special.pop();
				}
			}else if(cha == ']'){
				if(special.peek().equals('[')){
					special.pop();
				}
			}
			
		}
		if(special.size()==0){
			return true;
		}
		return false;
	}

}
