package com.github.HarryHook.coding2017.stack;

import com.github.HarryHook.coding2017.basic.MyStack;

public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */

    public void reverse(MyStack s) {

	if (s.isEmpty()) {
	    return;
	}
	// 如果s里面只有一个元素，就返回。具体实现是先pop出来一个，判断剩下的是不是空栈。
	Object tmp1 = s.pop();
	reverse(s);
	if (s.isEmpty()) {
	    s.push(tmp1);
	    return;
	}
	Object temp2 = s.pop();
	reverse(s);
	s.push(tmp1);
	reverse(s);
	s.push(temp2);

    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * 
     * @param o
     */
    public void remove(MyStack s, Object o) {
	if (s.isEmpty()) {
	    return;
	}
	MyStack stack = new MyStack();
	while (!(s.isEmpty())) {
	    if (s.peek() != o) {
		stack.push(s.pop());
	    } else {
		s.pop();
	    }
	}
	while (!(stack.isEmpty())) {
	    s.push(stack.pop());
	}

    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
     * 可以使用另外一个栈来辅助
     * 
     * @param len
     * @return
     */
    public Object[] getTop(MyStack s, int len) {
	if (s.isEmpty() || len <= 0) {
	    return null;
	}
	if (len > s.size()) {
	    len = s.size();
	}
	Object[] array = new Object[len];

	int i = 0;
	while (i < len) {
	    array[i++] = s.pop();
	}
	while (i != 0) {
	    s.push(array[--i]);
	}
	return array;
    }

    /**
     * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
     * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
     * 则该字符串中的括号不是成对出现的， 该方法返回false;
     * 
     * @param s
     * @return
     */
    public boolean isValidPairs(String s) {
	if (s == null || s == "") {
	    return false;
	}
	MyStack stack = new MyStack();
	for (int i = 0; i < s.length(); i++) {
	    if (s.charAt(i) == '(') {
		stack.push(s.charAt(i));
	    } else if (s.charAt(i) == ')') {
		if (stack.isEmpty()) {
		    return false;
		} else {
		    char outOfStackChar = (char) stack.pop();
		    if (outOfStackChar != '(') {
			return false;
		    }
		}
	    }

	    if (s.charAt(i) == '{') {
		stack.push(s.charAt(i));
	    } else if (s.charAt(i) == '}') {
		if (stack.isEmpty()) {
		    return false;
		} else {
		    char outOfStackChar = (char) stack.pop();
		    if (outOfStackChar != '{') {
			return false;
		    }
		}
	    }

	    if (s.charAt(i) == '[') {
		stack.push(s.charAt(i));
	    } else if (s.charAt(i) == ']') {
		if (stack.isEmpty()) {
		    return false;
		} else {
		    char outOfStackChar = (char) stack.pop();
		    if (outOfStackChar != '[') {
			return false;
		    }
		}
	    }

	}
	return true;
    }

}
