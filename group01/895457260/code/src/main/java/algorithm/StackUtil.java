package algorithm;

import datastructure.basic.Stack;

import java.util.Arrays;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack reverse = new Stack();
		while (!s.isEmpty()) {
			reverse.push(s.pop());
		}
		Stack reverseAgain = new Stack();
		while (!reverse.isEmpty()) {
			reverseAgain.push(reverse.pop());
		}
		while (!reverseAgain.isEmpty()) {
			s.push(reverseAgain.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		Stack temp = new Stack();
		while (!s.isEmpty()) {
			Object pop = s.pop();
			if (!pop.equals(o)) {
				temp.push(pop);
			}
		}
		while (!temp.isEmpty()) {
			s.push(temp.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		Stack temp = new Stack();
		for (int i = 0; i < len && !s.isEmpty(); ++i) {
			temp.push(s.pop());
		}
		Object[] result = new Object[temp.size()];
		for (int i = 0; i < result.length; ++i) {
			result[i] = temp.pop();
			s.push(result[i]);
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
	public static boolean isValidPairs(String s) {
		Stack brackets = new Stack();
		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (isBracket(c)) {
				if (!brackets.isEmpty() && isPair((Character) brackets.peek(), c)) {
					brackets.pop();
				} else {
					brackets.push(c);
				}
			}
		}
		return brackets.isEmpty();
	}

	private static boolean isBracket(char c) {
		return c == '(' || c == ')'
				|| c == '[' || c == ']'
				|| c == '{' || c == '}';
	}

	private static boolean isPair(char left, char right) {
		return (left == '(' && right == ')')
				|| (left == '[' && right == ']')
				|| (left == '{' && right == '}');
	}
}
