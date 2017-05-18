package com.coding.basic.stack;
import java.util.Stack;
public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(isEmpty(s)){
			return ;
		}
		Stack help=new Stack();
		int size=0;
		while(!s.isEmpty()){
			Object o=s.pop();
			help.push(o);
			size++;
		}
		Object[] o=new Object[size];
		int i=0;
		while(!help.isEmpty()){
			o[i]=help.pop();
			i++;
		}
		for(int j=0;j<o.length;j++){
			s.push(o[o.length-1-j]);
		}
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(isEmpty(s)){
			return ;
		}
		Stack help=new Stack();
		while(!s.isEmpty()){
			if(s.peek().equals(o)){
				s.pop();
			}
			else{
				help.push(s.pop());
			}
		}
		while(!help.isEmpty()){
				s.push(help.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(isEmpty(s)){
			return null;
		}
		int size=0;
		Object[] o=new Object[len];
		while(!s.isEmpty()){
			Object en=s.pop();
			o[size++]=en;
			if(size==len){
				break;
			}
		}
		for(int i=0;i<len;i++){
			if(o[len-i-1]!=null)
				s.push(o[len-i-1]);
			
		}
		return o;
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
		char [] c=s.toCharArray();
		Stack stack=new Stack();
		for(int i=0;i<c.length;i++){
			if("(".equals(c[i]+"") || "[".equals(c[i]+"") || "{".equals(c[i]+"")){
				stack.push(c[i]+"");
			}
			else if(")".equals(c[i]+"") || "]".equals(c[i]+"") || "}".equals(c[i]+"")){
				if(!stack.isEmpty()){
					if(!stack.pop().equals(opposite(c[i]+""))){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static String opposite(String s){
		if(")".equals(s)){
			return "(";
		}
		else if("]".equals(s)){
			return "[";
		}
		else{
			return "{";
		}
	}
//	public static int getSize(Stack s){
//		int size=0;
//		while(!s.isEmpty()){
//			s.pop();
//			size++;
//		}
//		return size;
//	}
	
	public static boolean isEmpty(Stack s){
		if(s==null || s.isEmpty()){
			return true;
		}
		return false;
	}
	
}
