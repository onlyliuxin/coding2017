package com.coding.basic.stack;

public class StackUtil {
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {//s:[1,2,3,4,5]
		if(s == null || s.isEmpty()){
			return;
		}
		
		Stack reverseOne = new Stack();
		while(!s.isEmpty()){
			reverseOne.push(s.pop());
		}//s:[] reverseOne:[5,4,3,2,1]
		
		Stack reverseTwo = new Stack();
		while(!reverseOne.isEmpty()){
			reverseTwo.push(reverseOne.pop());
		}//s:[] reverseOne:[] reverseTwo:[1,2,3,4,5]
		
		while(!reverseTwo.isEmpty()){
			s.push(reverseTwo.pop());
		}//s:[5,4,3,2,1] reverseOne:[] reverseTwo:[]
		
	}
	
	//递归实现
	public static void reverse_V1(Stack s){
		if(s == null || s.isEmpty()){
			return;
		}
		
		Object top = s.pop();
		if(!s.isEmpty()){
			StackUtil.reverse_V1(s);
			
			Stack temp = new Stack();
			while(!s.isEmpty()){
				temp.push(s.pop());
			}
			
			s.push(top);
			
			while(!temp.isEmpty()){
				s.push(temp.pop());
			}
			
		}else{
			s.push(top);
		}
    }

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null || s.isEmpty()){
			return;
		}
		
		Stack temp = new Stack();
		Object top = null;
		while(!s.isEmpty()){
			top = s.pop();
			if(top.equals(o)){
				break;
			}
			temp.push(top);
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
	public static Object[] getTop(Stack s,int len) {//若len>s的size,则Object[size]~Object[len-1]为null
		if(s == null || s.isEmpty() || len <= 0){
			return null;
		}
		
		Object[] result = new Object[len];
		int i = 0;
		for(i = 0;i < len;i++){
			if(s.isEmpty()){
				break;
			}
			result[i] = s.pop();
		}
		
		for(int j = i - 1;j >= 0;j--){
			s.push(result[j]);
		}
		return result;
	}
	
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,y,z
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){
		if(s == null || s.length() == 0 || s.length() == 1){
			return true;
		}
		
		char[] sc = s.toCharArray();
		Stack scStack = new Stack();//用该栈保存参数字符串的字符数组
		for(int i = 0;i < sc.length;i++){
			scStack.push(sc[i]);
		}
	    
		Stack temp = new Stack();//保存')' ']' '}'
		while(!scStack.isEmpty()){
			char scStackTop = (char)scStack.pop();
			if(scStackTop == ')' || scStackTop == ']' || scStackTop == '}'){
				temp.push(scStackTop);
				continue;
			}
			
			if(scStackTop == '(' || scStackTop == '[' || scStackTop == '{'){
				if(temp.isEmpty()){
					return false;
				}
				char tempTop = (char)temp.pop();
				//scStackTop:'('or'['or'{'   tempTop:')'or']'or'}'
				if((scStackTop + 1 == tempTop) || (scStackTop + 2 == tempTop)){
					continue;
				}else{
					return false;
				}
			}
		}
		return true;
	}
	
}
