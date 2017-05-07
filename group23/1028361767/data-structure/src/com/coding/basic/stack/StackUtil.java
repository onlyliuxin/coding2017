package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack tmp = new Stack();
//		Stack tmp1 = new Stack();
//		while(!s.isEmpty())
//			tmp.push(s.pop());
//		while(!tmp.isEmpty())
//			tmp1.push(tmp.pop());
//		while(!tmp1.isEmpty())
//			s.push(tmp1.pop());
		/**
		 * 改正
		 */
		int len = s.size();
		for(int i=0;i<len;i++){
			Integer top = (Integer)s.pop();
			while(s.size() > i) {
				tmp.push(s.pop());
			}
			s.push(top);
			while(tmp.size() > 0){
				s.push(tmp.pop());
			}
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack tmp = new Stack();
		boolean found = false;
		while(!s.isEmpty() && !found){
			Object obj = s.pop();
			if(obj == o){
				found = true;
			}else{
				tmp.push(obj);
			}
		}
		while(!tmp.isEmpty())
			s.push(tmp.pop());
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Object[] objs = new Object[len];
		if(s.isEmpty() || len == 0)
			return objs;
		int i = 0;
		while(i < len && !s.isEmpty()){
			objs[i++] = s.pop();
		}
		for(i=objs.length-1;i>=0;i--)
			s.push(objs[i]);
		return objs;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * ( 40  ) 41 [ 91 ] 93 {123 }125
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){
		if(s == null || s.length() == 0)
			return true;
		Stack stack = new Stack();
		char[] chars = s.toCharArray();
		for(int i=0;i<chars.length;i++){
			char tmp = chars[i];
			if(tmp == 40 || tmp == 91 || tmp == 123){
				stack.push(tmp);
				continue;
			}else if(tmp == 41){
				if(tmp-1 == (char)stack.peek()){
					stack.pop();
				}else{
					return false;
				}
			}else if(tmp == 93 || tmp == 125){
				if(tmp-2 == (char)stack.peek()){
					stack.pop();
				}else{
					return false;
				}
			}
		}
		return stack.size() == 0;
	}
	
	
}
