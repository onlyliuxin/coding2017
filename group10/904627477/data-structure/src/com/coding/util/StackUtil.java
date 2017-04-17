package com.coding.util;

import java.util.HashMap;
import java.util.Map;

import com.coding.basic.ArrayList;
import com.coding.basic.Stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s==null||s.size()==0){
			return ;
		}
		ArrayList temp = new ArrayList();
		while(!s.isEmpty()){
			temp.add(s.pop());
		}
		for(int i=0;i<temp.size();i++){
			s.push(temp.get(i));
		}
	}
	//非递归
	public static void reverse1(Stack s) {
		if(s==null||s.isEmpty()){
			return ;
		}
		Stack temp1 = new Stack();
		while(!s.isEmpty()){
			temp1.push(s.pop());
		}
		Stack temp2 = new Stack();
		while(!temp1.isEmpty()){
			temp2.push(temp1.pop());
		}
		while(!temp2.isEmpty()){
			s.push(temp2.pop());
		}
	}
	
	//递归
	public static void reverse2(Stack s) {
		if(s==null){
			return ;
		}
		if(s.isEmpty()){
			return ;
		}else{
			Object temp = s.pop();
			reverse2(s);
			addStackBottom(temp,s);
			return ;
		}
	}

	

	private static void addStackBottom(Object obj, Stack s) {
		Stack temp = new Stack();
		while(!s.isEmpty()){
			temp.push(s.pop());
		}
		s.push(obj);
		while(!temp.isEmpty()){
			s.push(temp.pop());
		}
	}
	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s==null||o==null||s.size()==0){
			return;
		}
		Stack temp = new Stack();
		while(!s.isEmpty()){
			Object obj = s.pop();
			if(o.equals(obj)){
				break;
			}else{
				temp.push(obj);
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
		if(s==null||len==0||s.size()==0){
			return null;
		}
		Object[] temp = new Object[len];
		int i=0;
		for(;i<len&&!s.isEmpty();i++){
			temp[i] = s.pop();
		}
		Object[] result = new Object[i];
		System.arraycopy(temp, 0, result, 0, i);
		for (Object obj : result) {
			s.push(obj);
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
		if(s==null){
			return false;
		}
		Map<Character, Character> model = new HashMap<Character, Character>();
		model.put('(', ')');
		model.put('[', ']');
		model.put('{', '}');
		Stack temp = new Stack();
		char[] cs = s.toCharArray();
		for (char c : cs) {
			if(c=='('||c=='{'||c=='['){
				temp.push(c);
			}else if(c==')'||c=='}'||c==']'){
				if(temp.isEmpty()){
					return false;
				}
				Object obj = temp.pop();
				if(!model.get(obj).equals(c)){
					return false;
				}
			}
		}
		return true;
	}
	
	
}
