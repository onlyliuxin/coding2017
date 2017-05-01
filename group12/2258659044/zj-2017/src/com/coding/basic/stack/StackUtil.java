package com.coding.basic.stack;

import java.util.HashMap;
import java.util.Map;

public class StackUtil {

	private static Map<Character, Character> markMap;
	
	static {
		//初始化括号对
		markMap = new HashMap<Character, Character>();
		markMap.put('(', ')');
		markMap.put('[', ']');
		markMap.put('{', '}');		
	}
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param <E>
	 */
	public static <E> void reverse(Stack<E> s) {

		if(s == null||s.isEmpty()){
			return;
		}				
		E last = getBottom(s,s.pop());
		reverse(s);
		s.push(last);	
	}
	
	/**
	 * 获取栈底部数据
	 * @param <E>
	 * @param s
	 * @return
	 */
	private static <E> E getBottom(Stack<E> s,E val){
		
		if(s.isEmpty()){
			return val;
		}		
		E lst = getBottom(s,s.pop());
		s.push(val);
		return lst;
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param <E>
	 * 
	 * @param o
	 */
	public static <E> void remove(Stack<E> s,E o) {
		
		if(s == null||s.isEmpty()){
			return;
		}			
		E res = s.pop();
		if(res.equals(o)){
			return;
		}
		remove(s,o);
		s.push(res);		
	}
		
	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param <E>
	 * @param len
	 * @return
	 */

	public static <E> Object[] getTop(Stack<E> s,int len) {
		
		if(s == null||s.isEmpty()||len <= 0){
			return null;
		}
		
		//当len的长度大于栈s的长度时返回栈s全部数据
		int size  = s.size()>=len? len:s.size();
		
		Object[] objs = new Object[size];
		
		putValueToArray(s,objs,0);
		
		return objs;
	}
	
	/**
	 * 将获取的元素放到数组中
	 * @param <E>
	 * @param s
	 * @param objs
	 * @param count
	 */
	public static <E> void putValueToArray(Stack<E> s,Object[] objs,int count){
		
		E res = s.pop();
		count++;
		objs[count-1] = res;
		if(count== objs.length){
			s.push(res);
			return;
		}
		putValueToArray(s,objs,count);		
		s.push(res);			
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
		
		if(!validation(s)){
			return false;
		}
		Stack<Character> markStack = new Stack<>();		
		char[] charArr = s.toCharArray();	
		for (int i = 0; i < charArr.length; i++) {
			if(markMap.containsKey(charArr[i])){
				markStack.push(charArr[i]);
			}
			if(markMap.containsValue(charArr[i])){				
				if(markMap.get(markStack.pop()).equals(charArr[i])){
					continue;
				}else{
					return false;
				}
			}
		}
		return markStack.size()==0;
	}
	
	private static boolean validation(String s){
		
		if(s!=null&&s.length()>0){
			if(s.contains("(")&&s.contains(")")){
				return true;
			}
			if(s.contains("[")&&s.contains("]")){
				return true;
			}
			if(s.contains("{")&&s.contains("}")){
				return true;
			}
		}		
		return false;
	}
	
}
