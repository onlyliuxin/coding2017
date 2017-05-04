package test05.stack;



public class StackUtil {
	/**
	 * 假设栈中的元素是T, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param <T>
	 */
	public static <T> void reverse(Stack<T> s) {
			Stack<T> temp=new Stack<T>();
			while (s.peek()!=null) {
				temp.push(s.pop());
			}
			Stack<T> temp2=new Stack<T>();
			while (temp.peek()!=null) {
				temp2.push(temp.pop());
			}
			while (temp2.peek()!=null) {
				s.push(temp2.pop());
			}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack temp=new Stack();
		while (s.peek()!=null) {
			Object obj=s.pop();
			if (!obj.equals(o)) {
				temp.push(obj);	
			}
		}
		while (temp.peek()!=null) {
			s.push(temp.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 * @throws Exception 
	 */
	public static Object[] getTop(Stack s,int len) {
		if (len<=s.size()) {
			Stack temp=new Stack();
			Object[] result=new Object[len];
			for (int i = 0; i < s.size(); i++) {
				Object o=s.pop();
				temp.push(o);
				if (i<len) {
					result[i]=o;
				}
			}
			while (temp.peek()!=null) {
				s.push(temp.pop());
			}
			return result;
		} else {
			throw new IndexOutOfBoundsException("len 大于实际长度！");
		}
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
//	public static boolean isValidPairs(String s) {
//		Stack s1 = new Stack();
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
//				s1.push(c);
//			}
//		}
//
//		Stack s2 = new Stack();
//		for (int i = s.length() - 1; i >= 0; i--) {
//			char c = s.charAt(i);
//			if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
//				s2.push(c);
//			}
//		}
//
//		for (int i = 0; i < s1.size() / 2; i++) {
//			char a = (char) s1.pop();
//			char b = (char) s2.pop();
//			if (a == '(' && b != ')') {
//				return false;
//			} else if (a == ')' && b != '(') {
//				return false;
//			} else if (a == '[' && b != ']') {
//				return false;
//			} else if (a == ']' && b != '[') {
//				return false;
//			} else if (a == '{' && b != '}') {
//				return false;
//			} else if (a == '}' && b != '{') {
//				return false;
//			}
//		}
//
//		return true;
//	}
	
public static boolean isValidPairs(String s){
		
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			
			if(c == '(' || c =='[' || c == '{'){
				
				stack.push(c);
				
			} else if( c == ')'){
				
				char topChar = stack.pop();
				if(topChar != '('){
					return false;
				}
				
			} else if( c == ']'){
				
				char topChar = stack.pop();
				if(topChar != '['){
					return false;
				}
					
			} else if( c == '}'){
				
				char topChar = stack.pop();
				if(topChar != '{'){
					return false;
				}
				
			}
		}
		return stack.size() == 0;
	}

}
