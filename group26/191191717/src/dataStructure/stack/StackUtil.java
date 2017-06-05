package dataStructure.stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		if (s == null || s.isEmpty()) {
			return;
		}

		Stack<Integer> tmp = new Stack<Integer>();
		while (!s.isEmpty()) {
			Integer top = (Integer) tmp.pop();
			addToBottom(s, top);
			// tmp.push(s.pop());
		}
		// while (!tmp.isEmpty())
		// {
		// Integer top = (Integer)tmp.pop();
		// addToBottom(s, top);
		// }

	}

	public static void addToBottom(Stack<Integer> s, Integer value) {
		if (s.isEmpty()) {
			s.push(value);
		} else {
			Integer top = (Integer) s.pop();
			addToBottom(s, value);
			s.push(top);
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		reverse(stack);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Integer> s, Object o) {
		if (s.isEmpty() || s == null) {
			return;
		}
		Stack<Integer> temp = new Stack<Integer>();
		while (!s.isEmpty()) {
			Object obj = s.pop();
			if (obj != o) {
				temp.push(obj);
			}
		}
		while (!temp.isEmpty()) {
			s.push(temp.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<Integer> s, int len) {
		if (s.isEmpty() || s == null || s.size() < len || len <= 0) {
			return null;
		}
		Stack<Integer> temp = new Stack<Integer>();
		Object[] objs = new Object[s.size()];
		int i = 0;
		while (!s.isEmpty()) {
			Object o = s.pop();
			temp.push(o);
			objs[i] = o;
			i++;
			if (i == len) {
				break;
			}
		}
		// 再次将数据压入栈
		while (!temp.isEmpty()) {
			s.push(temp.pop());
		}
		return objs;
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
		Stack temp = new Stack();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);// 字符串分解
			if (c == '(' || c == '[' || c == '{') {
				temp.push(c);
			} else if (c == '}') {
				char top = (char) temp.pop();
				if (top != '{') {
					return false;
				}
			} else if (c == ']') {
				char top = (char) temp.pop();
				if (top != '[') {
					return false;
				}
			} else if (c == ')') {
				char top = (char) temp.pop();
				if (top != '(') {
					return false;
				}
			}
		}
		return true;
	}
}
