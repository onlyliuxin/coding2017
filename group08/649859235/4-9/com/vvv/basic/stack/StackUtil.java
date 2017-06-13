package com.vvv.basic.stack;

public class StackUtil {
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s.isEmpty()){
			return;
		}
		
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        while(!s.isEmpty()){
        	Object obj = s.pop();
        	stack1.push(obj);
        } 
        
        while(!stack1.isEmpty()){
        	stack2.push(stack1.pop());
        }
        
        while(!stack2.isEmpty()){
        	s.push(stack2.pop());
        }
        
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s.isEmpty() || o ==null){
			return;
		}
		
		Stack stack = new Stack();
		while (!s.isEmpty()) {
			Object obj = s.pop();
			if (!obj.equals(o)) {
				stack.push(obj);
			}
		}
		
		while(!stack.isEmpty()){
			s.push(stack.pop());
		}
        
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		if (s.isEmpty() || len <= 0) {
			return null;
		}

		if (len > s.size()) {
			len = s.size();
		}

		Stack stack = new Stack();
		Object[] objArr = new Object[len];
		for(int i=0;i<len;i++){
			Object o = s.pop();
			objArr[i] = o;
			stack.push(o);
		}

		while (!stack.isEmpty()) {
			s.push(stack.pop());
		}

		return objArr;
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
		if(s.length()<2){
			return false;
		}
		
		Stack stack = new Stack();
		String[] arr = toStringArray(s);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("(") || arr[i].equals("[") || arr[i].equals("{")) {
				stack.push(arr[i]);
			} else if (arr[i].equals(")") && !stack.isEmpty()) {
				    if( !stack.peek().equals("(")){
					    return false;
				    }
				    stack.pop();
			}else if (arr[i].equals("]") && !stack.isEmpty()) {
				  if( !stack.peek().equals("[")){
					    return false;
				    }
				    stack.pop();
			}else if (arr[i].equals("}") && !stack.isEmpty()) {
				  if( !stack.peek().equals("{")){
					    return false;
				    }
				    stack.pop();
			}else if((arr[i].equals(")") || arr[i].equals("]") || arr[i].equals("}"))&& stack.isEmpty()){
				return false;
			}
		}
		
		if(stack.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	public static String[] toStringArray(String str) {
		int n = str.length();
		String[] arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = str.substring(i, i + 1);
		}

		return arr;
	}
	 
}
