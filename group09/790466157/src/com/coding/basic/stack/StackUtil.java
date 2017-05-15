package com.coding.basic.stack;

public class StackUtil {
	
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {

		if(!s.isEmpty()){
			Object top = s.pop();
			reverse(s);
			toTop(s,top);
		}
	}
	
	private static void toTop(Stack s,Object t){
		if(s.isEmpty()){
			s.push(t);
			return;
		}
		Object top = s.pop();
		toTop(s,t);
		s.push(top);
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {

		if(s.isEmpty()){
			s.push(o);
			return;
		}
		Object top = s.peek();
		remove(s,o);
		s.push(top);
	}
	

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Stack getTop(Stack s,int len) {
		Stack top = new Stack();
		// 若当前为空栈，则返回null
			 		if (len == 0) {
			 			return null;
			 		}
			 while (!s.isEmpty()){
				 for(int i = 0;i < len;i++){
					 s.pop();
					 top.push(s);
				 }
			 	}
			 return top;
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
		Stack myStack = new Stack();  
	    char[] arr = s.toCharArray();  
	    for (char c : arr) {  
	        Character temp = (Character) myStack.pop();  
	        // 栈为空时只将c入栈  
	        if (temp == null) {  
	            myStack.push(c);  
	        }  
	        // 配对时c不入栈  
	        else if (temp == '[' && c == ']') {  
	        }   
	        // 配对时c不入栈  
	        else if (temp == '(' && c == ')') {  
	        }   
	        // 不配对时c入栈  
	        else {  
	            myStack.push(temp);  
	            myStack.push(c);  
	        }  
	    }  
	    return myStack.isEmpty();  
	}  
	
	
}