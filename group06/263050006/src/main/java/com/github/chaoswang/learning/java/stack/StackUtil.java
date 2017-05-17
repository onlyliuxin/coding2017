package com.github.chaoswang.learning.java.stack;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * ����ջ�е�Ԫ����Integer, ��ջ����ջ���� : 5,4,3,2,1 ���ø÷����� Ԫ�ش����Ϊ: 1,2,3,4,5
	 * ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
	 */
	public static void reverse(Stack<Integer> s) {
		Stack<Integer> tmpStack = new Stack<Integer>();
		while(!s.empty()){
			tmpStack.push(s.pop());
		}
		while(!tmpStack.empty()){
			StackUtil.addToBottom(s, tmpStack.pop());
		}
	}

	/**
	 * ɾ��ջ�е�ĳ��Ԫ�� ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
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
	 * ��ջ��ȡ��len��Ԫ��, ԭ����ջ��Ԫ�ر��ֲ��� ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty��
	 * ����ʹ������һ��ջ������
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		int stackSize = s.size();
		if(len > stackSize || len <= 0){
			throw new IllegalArgumentException("parameter len illegal");
		}
		List ret = new ArrayList();
		Stack tmpStack = new Stack();
		for(int i=0;i<len;i++){
			Object poped = s.pop();
			ret.add(poped);
			tmpStack.push(poped);
		}
		while(!tmpStack.empty()){
			s.push(tmpStack.pop());
		}
		return ret.toArray(new Object[0]);
	}

	/**
	 * �ַ���s ���ܰ�����Щ�ַ��� ( ) [ ] { }, a,b,c... x,yz ʹ�ö�ջ����ַ���s�е������ǲ��ǳɶԳ��ֵġ� ����s =
	 * "([e{d}f])" , ����ַ����е������ǳɶԳ��֣� �÷�������true ��� s = "([b{x]y})",
	 * ����ַ����е����Ų��ǳɶԳ��ֵģ� �÷�������false;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		List bracketList = Arrays.asList('(', ')','[', ']','{', '}'); 
		Stack<Character> tmpStack = new Stack<Character>();
		for(char c : s.toCharArray()){
			if(bracketList.contains(c)){
				tmpStack.push(c);
			}
		}
		//���ŵĸ�������ż�����϶����ɶ�
		if(tmpStack.size()%2 != 0){
			return false;
		}
		Stack<Character> tmpStack2 = new Stack<Character>();
		int tmpSize = tmpStack.size();
		for(int i=0;i<tmpSize/2;i++){
			Character poped = tmpStack.pop();
			Character value = map.get(poped);
			tmpStack2.push(value);
		}
		
		for(int i=0;i<tmpSize/2;i++){
			if(!tmpStack.pop().equals(tmpStack2.pop())){
				return false;
			}
		}
		return true;
	}

}