package DataStructure_5_Stack;

import java.util.Stack;

public class StackUtil {
	/*
	 * 假设栈中的元素是Integer，从栈顶到栈底是： 5,4,3,2,1 调用该方法后，从栈顶到栈底会变成 1,2,3,4,5
	 * 注意： 只能使用 Stack的基本操作，即push,pop,peek,isEmpty
	 */
	public static void reverse(Stack s){
		if(s.isEmpty()){ return; }
		int length = s.size();
		Object []temp = new Object[length];
		for(int i = 0; i < length; i++){
			temp[i] = s.pop();
		}
		for(int i = 0; i < length; i++){
			s.push(temp[i]);
		}
		return;
	}
	
	/*
	 * 删除栈中指定元素，注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty
	 * 
	 */
	public static void remove(Stack s, Object o){
		int length = s.size();
		Object elementTemp;
		System.out.println(length);
		int count = 0;
		Object []temp = new Object[length];
		if(s.isEmpty()){ return; }
		for(int i = 0; i < length;i++){
			elementTemp = s.pop();
			if(!o.equals(elementTemp)){
				temp[count++] = elementTemp;
				System.out.println(temp[i]);
			}
		}

		for(int i = count-1; i >= 0; i--){
			s.push(temp[i]);
		}
		return;
	}
	
	/*
	 * 从栈顶取得len个元素，原来栈中的元素保持不变
	 * @param len
	 * @return
	 */
	public static Object[]  getTop(Stack s , int len){
		if(s.isEmpty() || len > s.size() || len < 0){
			return null;
		}
		Object []result = new Object[len];
		for(int i = 0; i < len; i++){
			result[i] = s.pop();
		}
		return result;
	}
	
	/*
	 * 字符串 s 可能包含这些字符： () [] {}, a, b, c ... x, y, z
	 * 使用堆栈检查字符串 s 中的括号是不是成对出现的
	 * 例如： s = “([e{d}f])”,则该字符串中的括号是成对出现的，该方法返回 true
	 * 如果： s = "([b{x]})",则该字符串中的括号不是成对出现的，该方法 返回 false
	 * @param s 
	 * @return
	 */
	public static boolean isValidPairs(String s){
		Stack stack = new Stack();
		System.out.println(stack.isEmpty());
		char elementTemp;
		boolean tag = false;
		char [] a = s.toCharArray();
		for(int i = 0; i < a.length; i++){
			if((!tag) && (a[i] == '(' || a[i] == ')' || a[i] == '[' || a[i] == ']' || a[i] == '{' || a[i] == '}')){
				stack.push(a[i]);
				tag = true;
			}else{
				if(a[i] == '(' || a[i] == ')' || a[i] == '[' || a[i] == ']' || a[i] == '{' || a[i] == '}'){
					elementTemp = (char) stack.pop();
					switch(elementTemp){
						case '(': if(a[i]==')'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break; 
						case '[': if(a[i]==']'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break;
						case '{': if(a[i]=='}'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break;
						
					}	
				}
			}
			
		}
		if(stack.isEmpty()){
			return true;
		}
		return false;
	}
}






