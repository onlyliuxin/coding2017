package stack;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		if (s.isEmpty()) {
			return;
		}
		Stack s2 = new Stack();
		while (s.peek() != null) {
			if (!Objects.equals(o, s.peek())) {
				s2.push(s.pop());
			} else {
				s.pop();
				break;
			}
		}
		while (!s2.isEmpty()) {
			s.push(s2.pop());
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
		if (s.isEmpty()) {
			return new Object[0];
		}

		int len1 = (s.size() < len) ? s.size() : len;

		Object[] result = new Object[len1];
		Stack s2 = new Stack();
		Object o = null;
		for (int i = 0; i < len1; i++) {
			o = s.pop();
			result[i] = o;
			s2.push(o);
		}
		while (!s2.isEmpty()) {
			s.push(s2.pop());
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
		if (s.length() == 0) {
			return false;
		}

		Stack<Character> s2 = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			if (c1 != ')' && c1 != ']' && c1 != '}') {
				s2.push(c1);
			} else {
				char c2 = getOpChar(s2);
				if (!isPair(c1, c2)) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean isPair(char c1, char c2) {
		if (c1 == ')' && c2 == '(') {
			return true;
		}

		if (c1 == ']' && c2 == '[') {
			return true;
		}

		if (c1 == '}' && c2 == '{') {
			return true;
		}

		return false;
	}

	private static char getOpChar(Stack<Character> s2) {
		char c2 = s2.pop();
		while (c2 != ')' && c2 != ']' && c2 != '}' && 
				c2 != '(' && c2 != '[' && c2 != '{' && !s2.isEmpty()) {
			c2 = s2.pop();
		}
		return c2;
	}

}
