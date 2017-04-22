package com.github.ipk2015.coding2017.basic.stack;


public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack tempStack=new Stack();
		int size=s.size();
		if(size<2){
			return;
		}
		for(int i=0;i<size;i++){
			tempStack.push(s.pop());
		}
		Stack tempStack1=new Stack();
		for(int i=0;i<size;i++){
			tempStack1.push(tempStack.pop());
		}
		for(int i=0;i<size;i++){
			s.push(tempStack1.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack tempStack=new Stack();
		Object  tempElement;
		while(!s.isEmpty()){
			tempElement=s.pop();
			if(tempElement.equals(o)){
				break;
			}
			tempStack.push(tempElement);
		}
		while(!tempStack.isEmpty()){
			s.push(tempStack.pop());
		}
		
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(len>s.size()){
			throw new RuntimeException("len超出范围");
		}
		Stack tempStack=new Stack();
		for(int i=0;i<len;i++){
			tempStack.push(s.pop());
		}
		Object[] array=new Object[len];
		Object tempObject;
		for(int i=0;i<len;i++){
			tempObject=tempStack.pop();
			array[i]=tempObject;
			s.push(tempObject);
		}
		return array;
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
		char[] charArray = s.toCharArray();
		Stack stack=new Stack();
		int flag;
		for(int i=0;i<charArray.length;i++){
			flag=switchChar(charArray[i]);
			if(flag>0){
				stack.push(flag);
			}else if(flag<0){
				if(stack.size()==0){
					return false;
				}
				Integer peek = (Integer)stack.peek();
				if(peek+flag==0){
					stack.pop();
				}else{
					return false;
				}
			}
		}
		if(stack.size()>0){
			return false;
		}
		return true;
	}
	private static int switchChar(char c){
		int result=0;
		switch(c){
			case '(':
				result=1;
				break;
			case ')':
				result=-1;
				break;
			case '[':
				result=2;
				break;
			case ']':
				result=-2;
				break;
			case '{':
				result=3;
				break;
			case '}':
				result=-3;
				break;
			default:
				break;
		}
		return result;
	}
	
}
