package com.datastructure.stack;
import java.util.Objects;
import java.util.Stack;

public class StackUtil {
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	//错误,面向对象错误
	/*public static void reverse(Stack s) {
		Stack temp = new Stack();
		while(!s.isEmpty()){
			temp.push(s.pop());
		}
		s = temp;
	}*/
	public static void reverse(Stack s) {
		if(s == null || s.isEmpty()){
			return;
		}
		
		Stack tmp = new Stack();
		while(!s.isEmpty()){
			tmp.push(s.pop());
		}
		while(!tmp.isEmpty()){
			Object top = tmp.pop();
			addToBottom(s,top);
		}	
		
		
	}
	public static void addToBottom(Stack s,  Object value){
		if(s.isEmpty()){
			s.push(value);
		} else{
			Object top = s.pop();
			addToBottom(s,value);
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
		while(!s.isEmpty()){
			Object result=s.pop();
			if(Objects.equals(result, o)){
				continue;
			}
			temp.push(result);
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
	public static Object[] getTop(Stack s,int len) {
		Object [] obj= new Object[len] ;
		Stack temp = new Stack();
		for (int i = 0; i <len; i++) {
			Object result=s.pop();
			obj[i] = result;
			temp.push(result);
		}
		while(!temp.isEmpty()){
			s.push(temp.pop());
		}
		return obj;
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
		Stack temp1 = new Stack();
		Stack temp2 = new Stack();
		char [] c = s.toCharArray();
		int clength = c.length;
		if(clength%2==0){
			for (int i = 0; i < clength; i++) {
				if(i<clength/2){
					temp1.push(c[i]);
				}else{
					temp2.push(c[i]);
				}
			}
		}else{
			char result=c[clength/2];
			if(contain(result)){
				return false;
			};
			for (int i = 0; i < clength; i++) {
				if(i<clength/2){
					temp1.push(c[i]);
				}else{
					if(i==clength/2){
						continue;
					}						
					temp2.push(c[i]);
				}
			}
		}
		StackUtil.reverse(temp2);
		while(!temp1.isEmpty()){
			char a =(char) temp1.pop();
			char b = (char) temp2.pop();
			if(Objects.equals(a, '(')){
				if(!Objects.equals(b, ')')){
					return false;
				}
			}else if(Objects.equals(a, '[')){
				if(!Objects.equals(b, ']')){
					return false;
				}
				
			}else if(Objects.equals(a, '{')){
				if(!Objects.equals(b, '}')){
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean contain(Object object){
		return Objects.equals(object, '(')||Objects.equals(object, ')')||
				   Objects.equals(object, '[')||Objects.equals(object, ']')||
				   Objects.equals(object, '{')||Objects.equals(object, '}');
	}
}
