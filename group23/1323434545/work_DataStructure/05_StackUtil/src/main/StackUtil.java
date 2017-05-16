package main;

import java.util.Arrays;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack stack1 = new Stack();
		while (!s.isEmpty()) {
			stack1.push(s.pop());
		}
		Stack stack2 = new Stack();
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			s.push(stack2.pop());
		}

	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		Stack stack = new Stack();
		while (!s.isEmpty()) {
			stack.push(s.pop());
		}
		while (!stack.isEmpty()) {
			Object temp = stack.pop();
			if (!o.equals(temp)) {
				s.push(temp);
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
		if (len < 0) {
			return new Object[0];
		}
		Object[] objArray = new Object[len];
		int i = 0;
		for (; i < len; i++) {
			if (s.isEmpty()) {
				break;
			}
			objArray[i] = s.pop();
		}
		objArray = Arrays.copyOf(objArray, i);
		for (int j = objArray.length - 1; j >= 0; j--) {
			s.push(objArray[j]);
		}
		return objArray;
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
		String[] array = s.split("");
		Stack stack = new Stack();
		for (String s1 : array) {
			if ("{[()]}".contains(s1)) {
				if (!stack.isEmpty()) {
					if (")]}".contains(s1)) {
						if (s1.replace(")", "(").equals(stack.peek().toString())
								|| s1.replace("]", "[").equals(stack.peek().toString())
								|| s1.replace("}", "{").equals(stack.peek().toString())) {
							stack.pop();
						} else {
							return false;
						}
					} else {
						stack.push(s1);
					}
				} else {
					if (")]}".contains(s1)) {
						return false;
					}
					stack.push(s1);
				}
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

}
