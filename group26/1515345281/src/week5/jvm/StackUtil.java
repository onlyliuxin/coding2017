package week5.jvm;

import java.util.Stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {

		nullCheck(s);

		int size = s.size();
		Stack<Integer> tempStack = new Stack<Integer>();

		for(int i=0;i<size;i++){
			Integer top=s.pop();
			while(s.size() > i){
				tempStack.push(s.pop());
			}
			s.push(top);
			while(tempStack.size() > 0){
				s.push(tempStack.pop());
			}
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
         nullCheck(s);
         
         Stack tempStack=new Stack();
         
         while(!s.isEmpty()){
        	 Object top=s.pop();
        	 if(!o.equals(top)){
        		 tempStack.push(top);
        	 }
         }
         
         while(!tempStack.isEmpty()){
        	 s.push(tempStack.pop());
         }
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		nullCheck(s);
		
		if(s.size() < len || len <=0){
			return null;
		}
		
		Object[] objects=new Object[len];
		
	    for(int i=0;i<len;i++){
	    	objects[i]=s.pop();
	    }
	    
		for(int i=len-1;i >= 0;i--){
			s.push(objects[i]);
		}
		
		return objects;
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
		Stack<Character> stack=new Stack<Character>();
		
		for(int i=0;i<s.length();i++){
			
			boolean flag=true;
			Character ch=s.charAt(i);
			
			switch(ch){
			case '(':
			case '[':
			case '{':
				stack.push(ch);
				break;
			case ')':
				if( !(!stack.isEmpty() && stack.pop() == '(') ){
					flag=false;
				}
				break;
			case ']':
				if( !(!stack.isEmpty() && stack.pop() == '[') ){
					flag=false;
				}
				break;
			case '}':
				if( !(!stack.isEmpty() && stack.pop() == '{') ){
					flag=false;
				}
				break;
			}
			
			if(!flag){
				return false;
			}
		}
		return true;
	}

	private static void nullCheck(Stack<Integer> s) {
		if (s == null || s.isEmpty()) {
			return;
		}
	}
}
