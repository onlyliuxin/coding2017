package com.basic;



public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {

		if (s.isEmpty()) return;
		Object top = s.pop();
		reverse(s); //递归处理子栈
		moveTopToButtom(s, top);
	}

	/**
	 * 将栈顶元素移至栈底
	 */
	private static void moveTopToButtom(Stack stack, Object element) {

		if (stack.isEmpty()) {
			stack.push(element);
		} else {
			Object top = stack.pop();
			moveTopToButtom(stack, element);
			stack.push(top);
		}
	}


	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	@SuppressWarnings("unchecked")
	public static void remove(Stack s, Object o) {

		Stack tmpStack = new Stack();
		while (!s.isEmpty()) {
			Object element = s.peek();
			if (element.equals(o)) {
				s.pop();
			}
			tmpStack.push(s.pop());
		}
		while (!tmpStack.isEmpty()) {
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getTop(Stack s, int len) {

		Object[] result = new Object[len];
		Stack tmpStack = new Stack();
		int i = 0;
		while (!s.isEmpty() && i < len) {
			result[i] = s.pop();
			tmpStack.push(result[i++]);
		}
		while (!tmpStack.isEmpty()) {
			s.push(tmpStack.pop());
		}
		return result;
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

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				stack.push(s.charAt(i));
				continue;
			}
			if (s.charAt(i) == ']' && stack.peek() == '[') {
				stack.pop();
				continue;
			}
			if (s.charAt(i) == ')' && stack.peek() == '(') {
				stack.pop();
				continue;
			}
			if (s.charAt(i) == '}' && stack.peek() == '{') {
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
	
	
}
