package week05.basic;

import java.util.Stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		Stack<Integer> reverseStack = new Stack<>();
		Stack<Integer> sequenceStack = new Stack<>();
		while(!s.isEmpty()){
			reverseStack.push(s.pop());
		}
		
		while(!reverseStack.isEmpty()){
			sequenceStack.push(reverseStack.pop());
		}
		
		while(!sequenceStack.isEmpty()){
			s.push(sequenceStack.pop());
		}
	}
	
	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void remove(Stack s,Object o) {
		Stack tmpStack = new Stack<>();
		while(!s.isEmpty()){
			if(s.peek().equals(o)){
				s.pop();
			}else{
				tmpStack.push(s.pop());
			}
		}
		
		while(!tmpStack.isEmpty()){
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] getTop(Stack s,int len) {
		if(len < 0 ||len > s.size()){
			throw new IllegalArgumentException("len is invalid argument.");
		}
		
		Object[] objects = new Object[len];
		Stack tmpStack = new Stack<>();
		int index = 0;
		while(!s.isEmpty() && index < len){
			Object o = s.pop();
			tmpStack.push(o);
			objects[index++] = o;
		}
		
		while(!tmpStack.isEmpty()){
			s.push(tmpStack.pop());
		}
		return objects;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的，该方法返回false;
	 *  
	 * @param s
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isValidPairs(String s){
		String symStr = "";
		Stack symStack = new Stack<>();
		for(int i = 0; i < s.length(); i++){
			if(!Character.isLetter(s.charAt(i))){
				symStack.push(s.charAt(i));
				symStr += s.charAt(i);
			}
		}
		
		for(int i=0; i< symStr.length();i++){
			char s1 = symStr.charAt(i);
			char s2 = (char) symStack.pop();
			if(s1 == '(' && s2 != ')'){
				return false;
			}
			if(s1 == '[' && s2 != ']'){
				return false;
			}
			if(s1 == '{' && s2 != '}'){
				return false;
			}
		}
		
		return true;
	} 
}
