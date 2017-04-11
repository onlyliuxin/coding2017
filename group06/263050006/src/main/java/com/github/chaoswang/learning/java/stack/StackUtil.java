package com.github.chaoswang.learning.java.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class StackUtil {
	
	public static void addToBottom(Stack<Integer> s, Integer value) {
		Stack<Integer> tmpStack = new Stack<Integer>();
		while(!s.empty()){
			tmpStack.push(s.pop());
		}
		s.push(value);
		while(!tmpStack.empty()){
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		Stack<Integer> tmpStack = new Stack<Integer>();
		
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Integer> s, Object o) {
		if(o == null){
			return;
		}
		Stack<Integer> tmpStack = new Stack<Integer>();
		while(!s.empty()){
			Integer poped = s.pop();
			if(o.equals(poped)){
				break;
			}
			tmpStack.push(poped);
		}
		while(!tmpStack.empty()){
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<Integer> s, int len) {
		int stackSize = s.size();
		if(len > stackSize || len <= 0){
			throw new IllegalArgumentException("parameter len illegal");
		}
		List ret = new ArrayList();
		Stack<Integer> tmpStack = new Stack<Integer>();
		for(int i=0;i<len;i++){
			Integer poped = s.pop();
			ret.add(poped);
			tmpStack.push(poped);
		}
		while(!tmpStack.empty()){
			s.push(tmpStack.pop());
		}
		return ret.toArray(new Object[0]);
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
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		Stack<Character> tmpStack = new Stack<Character>();
		for(char c : s.toCharArray()){
			Character value = map.get(c);
			if(value != null){
				tmpStack.push(value);
			}
		}
		StringBuffer sb = new StringBuffer();
		while(!tmpStack.empty()){
			Character poped = tmpStack.pop();
			Character value = map.get(poped);
			if(value != null)
			sb.append(value);
		}
		
		List<Character> ret = new ArrayList<Character>();
		for(char c : s.toCharArray()){
			Character value = map.get(c);
			if(value != null){
				ret.add(value);
			}
		}
		StringBuffer sb2 = new StringBuffer();
		for(Character c : ret){
			Character value = map.get(c);
			if(value != null)
				sb2.append(c);
		}
		
		if(sb.toString().equals(sb2.toString())){
			return true;
		}
		return false;
	}

}