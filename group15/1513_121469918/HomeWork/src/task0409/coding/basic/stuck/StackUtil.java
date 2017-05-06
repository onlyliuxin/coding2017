package task0409.coding.basic.stuck;

import java.util.Stack;

public class StackUtil {
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(null == s){
			return;
		}
		Stack temp1 = new Stack();
		Stack temp2 = new Stack();
		while(!s.isEmpty()){
			temp1.push(s.pop());
		}
		while(!temp1.isEmpty()){
			temp2.push(temp1.pop());
		}
		while(!temp2.isEmpty()){
			s.push(temp2.pop());
		}
		
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		if(null == o|| null == s){
			return;
		}
		if(!s.isEmpty()){
			Object top = s.pop();
			remove(s,o);
			if(top.equals(o)){
				return;
			}else{
				s.push(top);
			}
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
		if(len<=0){
			return null;
		}
		Object[] result = new Object[len];
		Stack temp = new Stack();
		while(len>0){
			temp.push(s.pop());
			len--;
			result[len] = temp.peek();
		}
		while(!temp.isEmpty()){
			s.push(temp.pop());
		}
		return result;
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
		char[] chs = s.toCharArray();
		Stack left = new Stack();
		Stack right = new Stack();
		
		for(int i=0;i<chs.length;i++){
			char temp = chs[i];
			if(temp==')'){				
				left.push('(');
			}else if (temp=='}'){
				left.push('{');
			}else if(temp==']'){
				left.push('[');
			}
			if(temp=='('||temp=='['||temp=='{'){
				right.push(temp);
			}
		}
		
		reverse(left);
		if(right.toString().equals(left.toString())){
			return true;
		}
		return false;
	}
}
