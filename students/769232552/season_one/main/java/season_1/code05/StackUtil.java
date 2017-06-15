package code05;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */

	/**
	 * 此处是传值，将原有栈的地址 复制给 变量 s， s在函数内相当于局部变量，因此 s 重新赋值并没有什么用
	 * @param s
	 */
	public static void bad_reverse(Stack s) {
		if(s == null){
			return;
		}
		// need a new stack
		Stack newStack = new Stack();
		while (s.peek() != null){
			newStack.push(s.pop());
		}
		s = newStack;
	}


	public static void reverse(Stack s) {
		if(s == null || s.isEmpty()){
			return;
		}

		Stack tmp = new Stack();
		while(!s.isEmpty()){
			tmp.push(s.pop());
		}
		while(!tmp.isEmpty()){
			int top = (Integer) tmp.pop();
			addToBottom(s,top);//加入到原来栈的栈底
		}


	}

	public static void addToBottom(Stack s,  int value){
		if(s.isEmpty()){
			s.push(value);
		} else{
			int top = (Integer)  s.pop();
			addToBottom(s,value);
			s.push(top);
		}

	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null || o == null){
			return;
		}
		Stack newStack = new Stack();
		while (s.peek() != null){
			Object e = s.pop();
			if(!e.equals(o)){
				newStack.push(e);
			}
		}
		while (newStack.peek() != null){
			s.push(newStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) throws Exception {
		if(s == null || s.isEmpty() || s.size() < len || len <= 0){
			return null;
		}
		Stack newStack = new Stack();
		for (int i = 0; i < len; i++) {
			Object o = s.pop();
			newStack.push(o);
		}
		Object[] objects = new Object[len];
		for (int i = len - 1; i >= 0; i--) {
			Object o = newStack.pop();
			s.push(o);
			objects[i] = o;
		}
		return objects;
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
		char tag_1_start = '{';
		char tag_1_end = '}';
		char tag_2_start = '[';
		char tag_2_end = ']';
		char tag_3_start = '(';
		char tag_3_end = ')';

		int length = s.length();
		Stack stack = new Stack();
		for (int i = 0; i < length; i++) {
			char c  = s.charAt(i);
			if(c == tag_1_start || c == tag_2_start || c == tag_3_start){
				stack.push(c);
			}
			else if(c == tag_1_end){
				//pop all element after tag_1_start
				Object t= stack.pop();
				if(!t.equals(tag_1_start)){
					return false;
				}
			}
			else if(c == tag_2_end){
				//pop all element after tag_1_start
				Object t=  stack.pop();
				if(!t.equals(tag_2_start)){
					return false;
				}
			}
			else if(c == tag_3_end){
				//pop all element after tag_1_start
				Object t= stack.pop();
				if(!t.equals(tag_3_start)){
					return false;
				}
			}
		}
		return stack.size() == 0;
	}
	
	
}
