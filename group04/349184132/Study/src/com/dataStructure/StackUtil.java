package com.dataStructure;

import java.util.Objects;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s==null){
			return ;
		}
		Queue temp = new Queue();
		while(!s.isEmpty()){
			Integer num = (Integer) s.pop();
			temp.enQueue(num);
		}
		while (!temp.isEmpty()){
			Integer num = (Integer) temp.deQueue();
			s.push(num);
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null){
			return ;
		}
		Stack temp = new Stack();
		while(!s.isEmpty()){
			Object elem = s.pop();
			if(!Objects.deepEquals(elem,o)){
				temp.push(elem);
			}
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
	public static Object[] getTop(Stack s,int len) {
		if(s == null){
			return null;
		}
		if(len <0 || len > s.size()){
			throw new IndexOutOfBoundsException();
		}
		Object[] result = new Object[len];

		for(int i=0; i<len; i++){
			result[i] = s.pop();
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
		if( s == null || s == ""){
			throw new IllegalArgumentException();
		}
		Stack brackets = new Stack(); // 括号栈
		char[] arr = s.toCharArray();
		for(int i=0;i<arr.length;i++){
			if(arr[i]== '(' || arr[i] == '{' || arr[i] == '['){  //左括号则直接入栈
				brackets.push(arr[i]);
			}else if(arr[i]== ')'){    //右括号
				if (!(compare(brackets,'('))) return false; // 不匹配则返回 false
			}else if(arr[i]==']'){
				if (!(compare(brackets,'['))) return false;
			}else if(arr[i] =='}'){
				if (!(compare(brackets,'{'))) return false;
			}else{

			}
		}

		return true;
	}

	// 是否匹配 比配则为true
	private static boolean compare(Stack brackets,char left) {
		char c =(Character) brackets.pop();
		if(c == left){
			return true;
        }
		return false;
	}


}
