package datastructure.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
	    if (s == null || s.isEmpty()) {
	        return;
	    }
	    
	    Stack temp1 = new Stack();
	    while (!s.isEmpty()) {
	        temp1.push(s.pop());
	    }
	    Stack temp2 = new Stack();
	    while (!temp1.isEmpty()) {
	        temp2.push(temp1.pop());
	    }
	    while (!temp2.isEmpty()) {
	        s.push(temp2.pop());
	    }
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
	    if (s == null || s.isEmpty()) {
	        return;
	    }
	    
	    Stack temp = new Stack();
	    while (!s.isEmpty()) {
	        int num = (int) s.pop();
	        if (num != (int)o) {
	            temp.push(num);
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
	public static Object[] getTop(Stack s,int len) {
	    if (s == null || s.isEmpty() || len < 0) {
            return null;
        }
	    
	    Stack temp = new Stack();
	    int i = 0;
	    Object[] obj = new Object[len];
	    while(!s.isEmpty() && i < len) {
	        temp.push(s.peek());
	        obj[i++] = s.pop();
	    }
	    while (!temp.isEmpty()) {
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
	public static boolean isValidPairs(String s) {
	    if (s == null) {
	        return false;
	    }
	    
	    Stack stack = new Stack();
	    char[] charArray = s.toCharArray();
	    for (int i = 0; i < charArray.length; i++) {
	        char c = charArray[i];
	        if (c == '(' || c == '[' || c == '{') {
	            stack.push(c);
	        } else if (c == ')') {
	            if ('(' != (char)stack.pop()) {
	                return false;
	            }
	        } else if (c == ']') {
                if ('[' != (char)stack.pop()) {
                    return false;
                }
	        } else if (c == '}') {
                if ('{' != (char)stack.pop()) {
                    return false;
                }
	        }
	    }
	    return true;
	}
	
	
}
